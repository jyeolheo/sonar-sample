package com.sample.sonarsample.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class HttpClientConfig {

    @Bean
    public HttpClient httpClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        // @formatter:off
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, (certificate, authType) -> true)
                .build();

        return HttpClientBuilder
            .create()
            .disableAutomaticRetries()
            .disableCookieManagement()
            .disableConnectionState()
            .disableAuthCaching()
            .setMaxConnPerRoute(100)
            .setMaxConnTotal(200)
            .setUserAgent("sonar-sample")
            .setSSLContext(sslContext)
            .setSSLHostnameVerifier(new NoopHostnameVerifier())
            .setDefaultRequestConfig(RequestConfig.copy(RequestConfig.DEFAULT).setContentCompressionEnabled(false).build())
            .build();
        // @formatter:on
    }
}
