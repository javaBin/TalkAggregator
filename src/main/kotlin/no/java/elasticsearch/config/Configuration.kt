package no.java.elasticsearch.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class Configuration() {

    @Value("\${resttemplate.timeout.connectionRequestMs}")
    lateinit var connectionRequestTimeoutMs: Integer
    @Value("\${resttemplate.timeout.connectionMs}")
    lateinit var connectionTimeoutMs: Integer
    @Value("\${resttemplate.timeout.readMs}")
    lateinit var readTimeoutMs: Integer

    @Bean
    fun restTemplate(): RestTemplate {
        var httpRequestFactory = HttpComponentsClientHttpRequestFactory()
        httpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeoutMs.toInt())
        httpRequestFactory.setConnectTimeout(connectionTimeoutMs.toInt())
        httpRequestFactory.setReadTimeout(readTimeoutMs.toInt())
        var restTemplate = RestTemplate(httpRequestFactory)
        return restTemplate
    }
}