//package com.barysevich.project.httpclient;
//
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//
//@ConfigurationProperties("http-client")
//public class HttpClientProperties {
//    private int connectionTimeout = 0;
//    private int connectionRequestTimeout = 0;
//    private int socketTimeout = 0;
//    private boolean socketKeepAlive = false;
//    private boolean tcpNoDelay = false;
//    private Integer maxTotal = 100;
//    private int defaultMaxPerRoute = 100;
//    private int validateAfterInactivity = 1000;
//
//    public HttpClientProperties() {
//    }
//
//    public int getConnectionTimeout() {
//        return this.connectionTimeout;
//    }
//
//    public int getConnectionRequestTimeout() {
//        return this.connectionRequestTimeout;
//    }
//
//    public int getSocketTimeout() {
//        return this.socketTimeout;
//    }
//
//    public boolean isSocketKeepAlive() {
//        return this.socketKeepAlive;
//    }
//
//    public boolean isTcpNoDelay() {
//        return this.tcpNoDelay;
//    }
//
//    public int getMaxTotal() {
//        return this.maxTotal;
//    }
//
//    public int getDefaultMaxPerRoute() {
//        return this.defaultMaxPerRoute;
//    }
//
//    public int getValidateAfterInactivity() {
//        return this.validateAfterInactivity;
//    }
//
//    public void setConnectionTimeout(int connectionTimeout) {
//        this.connectionTimeout = connectionTimeout;
//    }
//
//    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
//        this.connectionRequestTimeout = connectionRequestTimeout;
//    }
//
//    public void setSocketTimeout(int socketTimeout) {
//        this.socketTimeout = socketTimeout;
//    }
//
//    public void setSocketKeepAlive(boolean socketKeepAlive) {
//        this.socketKeepAlive = socketKeepAlive;
//    }
//
//    public void setTcpNoDelay(boolean tcpNoDelay) {
//        this.tcpNoDelay = tcpNoDelay;
//    }
//
//    public void setMaxTotal(int maxTotal) {
//        this.maxTotal = maxTotal;
//    }
//
//    public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
//        this.defaultMaxPerRoute = defaultMaxPerRoute;
//    }
//
//    public void setValidateAfterInactivity(int validateAfterInactivity) {
//        this.validateAfterInactivity = validateAfterInactivity;
//    }
//}
//
