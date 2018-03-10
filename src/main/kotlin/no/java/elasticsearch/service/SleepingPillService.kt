package no.java.elasticsearch.service

import no.java.elasticsearch.domain.Talk
import no.java.elasticsearch.domain.sleepingpill.SleepingPillSessionList
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class SleepingPillService {
    @Value("\${sleepingpill.base.url}")
    lateinit var sleepingPillBaseUrl: String

    fun getSessions(): List<Talk> {
        var sessionUrl = sleepingPillBaseUrl + JAVAZONE_2017_URI;
        var restTemplate = RestTemplate()
        var sleepingPillSessionList = restTemplate.getForObject(sessionUrl, SleepingPillSessionList::class.javaObjectType)
        LOGGER.info(sleepingPillSessionList.toString())
        return sleepingPillSessionList.convertToTalkList("JavaZone 2017")
    }

    companion object {
        var JAVAZONE_2017_URI = "allSessions/javazone_2017"
        var LOGGER: Logger = LoggerFactory.getLogger(SleepingPillService.javaClass)
    }
}