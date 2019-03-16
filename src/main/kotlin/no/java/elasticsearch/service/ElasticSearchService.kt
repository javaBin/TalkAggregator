package no.java.elasticsearch.service

import no.java.elasticsearch.domain.Talk
import org.apache.http.client.methods.HttpGet
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate;
import java.io.InputStream

@Service
class ElasticSearchService {
    @Value("\${elastic.search.url}")
    lateinit var elasticSearchUrl: String

    fun storeDataToIndex(indexName: String, talk: Talk) {
        var restTemplate = RestTemplate()
        val eUrl = "$elasticSearchUrl/$indexName/${talk.eventId}/talk/${talk.talkId}"
        LOG.debug("Sending rest-put to {}", eUrl)
        try {
            restTemplate.put(eUrl, talk)
        } catch (e:Exception) {
            LOG.error("Failed to put $talk", e)
        }
    }

    fun storeTalksToIndex(indexName: String, talks : List<Talk>) {
 //       RestTemplate().put("$elasticSearchUrl/$indexName", null)
        talks.forEach{t -> storeDataToIndex(indexName, t)}
    }


    companion object {
        var LOG = LoggerFactory.getLogger(ElasticSearchService.javaClass)
    }
}