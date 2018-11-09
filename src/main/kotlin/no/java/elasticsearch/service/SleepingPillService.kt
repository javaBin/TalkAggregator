package no.java.elasticsearch.service

import no.java.elasticsearch.domain.Talk
import no.java.elasticsearch.domain.sleepingpill.SleepingPillConference
import no.java.elasticsearch.domain.sleepingpill.SleepingPillConferenceList
import no.java.elasticsearch.domain.sleepingpill.SleepingPillSessionList
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.lang.IllegalStateException

@Service
class SleepingPillService {
    @Value("\${sleepingpill.base.url}")
    lateinit var sleepingPillBaseUrl: String

    fun getPublicSessions(conference: SleepingPillConference): List<Talk> {
        val sessionUrl = "$sleepingPillBaseUrl$PUBLIC_ALL_SESSIONS/${conference.slug}"
        val sleepingPillSessionList: SleepingPillSessionList = RestTemplate().getForObject(sessionUrl, SleepingPillSessionList::class.javaObjectType) !!
//        LOGGER.info(sleepingPillSessionList.toString())
        return sleepingPillSessionList.convertToTalkList(conference.name)
    }

    fun getAllPublicSessions(): List<Talk> =
            getAllConferences().conferences
                    .filter { conference -> conference.slug != "javazone_2008" }
                    .flatMap {
                        getPublicSessions(it)
                    }

    fun getAllConferences(): SleepingPillConferenceList {
        val allConferenceUrl = sleepingPillBaseUrl + PUBLIC_ALL_SESSIONS
        LOGGER.info("Getting all conferences at url $allConferenceUrl")
        return RestTemplate().getForObject(allConferenceUrl, SleepingPillConferenceList::class.javaObjectType)
                ?: throw IllegalStateException("Got bill from RestTemplate")
    }

    companion object {
        var PUBLIC_ALL_SESSIONS = "/public/allSessions"
        var LOGGER: Logger = LoggerFactory.getLogger(SleepingPillService::class.java)
    }
}