package no.java.elasticsearch.controller

import no.java.elasticsearch.domain.Speaker
import no.java.elasticsearch.domain.Talk
import no.java.elasticsearch.service.ElasticSearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
class HelloController {

    @Resource
    lateinit var elasticSearchService: ElasticSearchService

    @GetMapping("/hello")
    fun helloKotlin(): String {
        return "hello world"
    }

    @GetMapping("/talks")
    fun getTalk(): Talk {
        var talk = Talk("da event name",
                "javazone_2098",
                "43543523245234",
                "Biggun talking",
                "Short abstract",
                "url to event",
                "beginner",
                60,
                "en",
                null,
                null,
                "2017-09-13T14:40:00Z",
                "2017-09-13T15:40:00Z",
                arrayOf(Speaker("Petter Olsen", "www.vg.no", "pppcityre", ""),
                        Speaker("Feilfjos Bøhlston", "www.db.no", "", "@boobobo")
                )
        )
        elasticSearchService.storeDataToIndex("javazone_2098", talk);
        return talk
    }

}