package no.java.elasticsearch.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class LoadDataScheduler(private val elasticSearchService: ElasticSearchService,
                        private val sleepingPillService: SleepingPillService) {
    @Scheduled(cron = "0 0 * * * *")
    fun loadData(){
        elasticSearchService.storeTalksToIndex("javazonepublic", sleepingPillService.getAllPublicSessions())
        sleepingPillService.getAllConferences().conferences.forEach{
            LOGGER.info(it.toString())
        }
    }
    companion object {
        private val LOGGER : Logger = LoggerFactory.getLogger(LoadDataScheduler::class.java)
    }
}