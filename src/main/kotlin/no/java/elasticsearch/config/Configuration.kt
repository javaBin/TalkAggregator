package no.java.elasticsearch.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import com.auth0.AuthenticationController
import org.springframework.scheduling.annotation.EnableScheduling
import java.io.UnsupportedEncodingException



@Configuration
@EnableScheduling
class Configuration {

    @Value("\${resttemplate.timeout.connectionRequestMs}")
    lateinit var connectionRequestTimeoutMs: Integer
    @Value("\${resttemplate.timeout.connectionMs}")
    lateinit var connectionTimeoutMs: Integer
    @Value("\${resttemplate.timeout.readMs}")
    lateinit var readTimeoutMs: Integer

    @Value("\${com.auth0.domain}")
    lateinit var auth0Domain: String
    @Value("\${com.auth0.clientId}")
    lateinit var auth0ClientId:String
    @Value("\${com.auth0.clientSecret}")
    lateinit var auth0clientSecret:String

    @Bean
    fun restTemplate(): RestTemplate {
        var httpRequestFactory = HttpComponentsClientHttpRequestFactory()
        httpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeoutMs.toInt())
        httpRequestFactory.setConnectTimeout(connectionTimeoutMs.toInt())
        httpRequestFactory.setReadTimeout(readTimeoutMs.toInt())
        var restTemplate = RestTemplate(httpRequestFactory)
        return restTemplate
    }

    @Bean
    fun authenticationController(): AuthenticationController {
        return AuthenticationController.newBuilder(auth0Domain, auth0ClientId, auth0clientSecret)
                .build()
    }
}