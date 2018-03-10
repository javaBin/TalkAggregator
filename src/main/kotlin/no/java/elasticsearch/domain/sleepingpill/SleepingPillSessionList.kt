package no.java.elasticsearch.domain.sleepingpill

import no.java.elasticsearch.domain.Talk

data class SleepingPillSessionList(var sessions : List<SleepingPillSession>) {
    fun convertToTalkList(eventName : String) : List<Talk> {
        return sessions.map { s -> s.convertToTalk(eventName) }
    }
}