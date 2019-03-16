package no.java.elasticsearch.controller

import org.apache.http.client.HttpClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SearchController() {
    @GetMapping("search")
    fun search(@RequestParam searchString: String) {
        // val httpGet : HttpGet()
        //  httpClient.execute()
    }
}