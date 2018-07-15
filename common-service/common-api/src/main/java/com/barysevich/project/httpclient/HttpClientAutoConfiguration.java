//package com.barysevich.project.httpclient;
//
//
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.config.SocketConfig;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//@ConditionalOnClass({HttpClient.class})
//@EnableConfigurationProperties({HttpClientProperties.class})
//public class HttpClientAutoConfiguration
//{
//    private HttpClientProperties httpClientProperties;
//
//
//    public HttpClientAutoConfiguration(HttpClientProperties httpClientProperties)
//    {
//        this.httpClientProperties = httpClientProperties;
//    }
//
//
//    @Bean
//    @ConditionalOnMissingBean
//    public CloseableHttpClient httpClient()
//    {
//        RequestConfig requestConfig = RequestConfig.custom()
//            .setConnectTimeout(this.httpClientProperties.getConnectionTimeout())
//            .setConnectionRequestTimeout(this.httpClientProperties.getConnectionRequestTimeout())
//            .setSocketTimeout(this.httpClientProperties.getSocketTimeout())
//            .build();
//
//        SocketConfig socketConfig = SocketConfig.custom()
//            .setSoTimeout(this.httpClientProperties.getSocketTimeout())
//            .setSoKeepAlive(this.httpClientProperties.isSocketKeepAlive())
//            .setTcpNoDelay(this.httpClientProperties.isTcpNoDelay())
//            .build();
//
//        SSLConnectionSocketFactory systemSSLSocketFactory = SSLConnectionSocketFactory.getSystemSocketFactory();
//
//        Registry<ConnectionSocketFactory> registry = RegistryBuilder.create()
//            .register("http", PlainConnectionSocketFactory.getSocketFactory())
//            .register("https", systemSSLSocketFactory)
//            .build();
//
//        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
//        connectionManager.setDefaultSocketConfig(socketConfig);
//        connectionManager.setMaxTotal(this.httpClientProperties.getMaxTotal());
//        connectionManager.setDefaultMaxPerRoute(this.httpClientProperties.getDefaultMaxPerRoute());
//        connectionManager.setValidateAfterInactivity(this.httpClientProperties.getValidateAfterInactivity());
//
//        return HttpClientBuilder.create()
//            .setConnectionManager(connectionManager)
//            .setDefaultRequestConfig(requestConfig)
//            .setDefaultSocketConfig(socketConfig)
//            .setSSLSocketFactory(systemSSLSocketFactory)
//            .build();
//    }
//}
