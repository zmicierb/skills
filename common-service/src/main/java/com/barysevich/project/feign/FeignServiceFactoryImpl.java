package com.barysevich.project.feign;


import java.util.Collections;
import java.util.List;

import com.barysevich.project.authorizationheader.AuthorizationHeaderSupplier;
import feign.Feign;
import feign.RequestTemplate;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.HttpHeaders;
//import org.apache.http.client.HttpClient;
import org.slf4j.MDC;


/**
 * Компонента, позволяющая по интерфейсу сервиса и его адресу собрать Feign-интерфейс.
 */
public class FeignServiceFactoryImpl implements FeignServiceFactory
{
    private final static List<String> content = Collections.singletonList("application/json;charset=UTF-8");

    private final ApacheHttpClient apacheHttpClient;

    private final Encoder encoder;

    private final Decoder decoder;

    private final AuthorizationHeaderSupplier headerSupplier;

    private final ErrorDecoder errorDecoder;


    FeignServiceFactoryImpl(
//        final HttpClient httpClient,
                            final AuthorizationHeaderSupplier headerSupplier,
                            final ErrorDecoder errorDecoder,
                            final Decoder decoder,
                            final Encoder encoder)
    {
        this.errorDecoder = errorDecoder;
        this.apacheHttpClient = new ApacheHttpClient();
        this.decoder = decoder;
        this.encoder = encoder;
        this.headerSupplier = headerSupplier;
    }


    @Override
    public <T> T build(final Class<T> clazz, final String url)
    {
        return createBuilder()
            .requestInterceptor(this::authorizationHeader)
            .requestInterceptor(this::jsonUTF8Header)
            .requestInterceptor(this::logHeader)
            .target(clazz, url);
    }


    @Override
    public <T> T buildWithoutAuth(final Class<T> clazz, final String url)
    {
        return createBuilder()
            .requestInterceptor(this::jsonUTF8Header)
            .requestInterceptor(this::logHeader)
            .target(clazz, url);
    }


    private Feign.Builder createBuilder()
    {
        return Feign.builder()
            .client((apacheHttpClient))
            .encoder(encoder)
            .decoder(decoder)
            .retryer(Retryer.NEVER_RETRY)
            .errorDecoder(errorDecoder);
    }


    private void jsonUTF8Header(final RequestTemplate requestTemplate)
    {
        requestTemplate.header(HttpHeaders.CONTENT_TYPE, content);
    }


    private void authorizationHeader(final RequestTemplate template)
    {
        template.header(HttpHeaders.AUTHORIZATION, "Bearer " + headerSupplier.authorizationHeader());
    }


    private void logHeader(final RequestTemplate template)
    {
        template.header("trace_id", MDC.get("trace_id"));
        template.header("user_id", MDC.get("user_id"));
    }
}
