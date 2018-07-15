package com.barysevich.project.feign;


import java.io.IOException;

import com.barysevich.project.authorizationheader.AuthorizationHeaderSupplier;
//import com.barysevich.project.httpclient.HttpClientAutoConfiguration;
import com.barysevich.project.utils.SerializationUtils;
import feign.Client;
import feign.Request;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.http.client.HttpClient;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//@AutoConfigureAfter(HttpClientAutoConfiguration.class)
public class FeignConfigAutoConfiguration
{
    @Bean
    @ConditionalOnMissingBean(AuthorizationHeaderSupplier.class)
    public AuthorizationHeaderSupplier authorizationHeaderSupplier()
    {
        return new AuthorizationHeaderSupplierDefault();
    }


    @Bean
    @ConditionalOnMissingBean(Decoder.class)
    public Decoder decoder()
    {
        return new JacksonDecoder(SerializationUtils.MAPPER);
    }


    @Bean
    @ConditionalOnMissingBean(Encoder.class)
    public Encoder encoder()
    {
        return new JacksonEncoder(SerializationUtils.MAPPER);
    }


    @Bean
    @ConditionalOnMissingBean(ErrorDecoder.class)
    public ErrorDecoder errorDecoder()
    {
        return new CustomErrorDecoder();
    }


    /**
     * Обертка вокруг ApacheHttpClient, чтобы Spring Cloud Sleuth смог навесить аспект
     */
//    @Bean TODO TraceFeignClientAutoConfiguration
    public Client client(final HttpClient httpClient)
    {
        return new Client()
        {
            private Client delegate = new ApacheHttpClient(httpClient);


            @Override
            public Response execute(final Request request, final Request.Options options) throws IOException
            {
                return delegate.execute(request, options);
            }
        };
    }


    @Bean
    public FeignServiceFactory feignServiceFactory(
//        final HttpClient httpClient,
        final AuthorizationHeaderSupplier headerSupplier,
        final ErrorDecoder errorDecoder,
        final Decoder decoder,
        final Encoder encoder)
    {
        return new FeignServiceFactoryImpl(headerSupplier, errorDecoder, decoder, encoder);
    }
}