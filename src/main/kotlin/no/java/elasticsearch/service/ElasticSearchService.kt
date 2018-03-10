package no.java.elasticsearch.service

import no.java.elasticsearch.domain.Talk
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate;

@Service
class ElasticSearchService {
    @Value("\${elastic.search.url}")
    lateinit var elasticSearchUrl: String

    fun storeDataToIndex(indexName: String, talk: Talk) {
        var restTemplate = RestTemplate()
        val eUrl = elasticSearchUrl + "/" + talk.eventId + "/talk/" + talk.talkId
        LOG.debug("Sending rest-put to {}", eUrl);
        restTemplate.put(eUrl, talk);
    }

    fun storeTalksToIndex(indexName: String, talks : List<Talk>) {
        talks.forEach{t -> storeDataToIndex(indexName, t)}
    }

    companion object {
        var LOG = LoggerFactory.getLogger(ElasticSearchService.javaClass)
    }
}