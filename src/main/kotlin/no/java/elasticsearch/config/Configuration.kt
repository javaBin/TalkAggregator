package no.java.elasticsearch.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration() {

    @Value("\${elastic.search.url}")
    lateinit var elasticSearchUrl: String


}