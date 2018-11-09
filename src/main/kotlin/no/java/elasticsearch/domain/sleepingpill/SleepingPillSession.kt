package no.java.elasticsearch.domain.sleepingpill

import no.java.elasticsearch.domain.Speaker
import no.java.elasticsearch.domain.Talk
import java.util.Date

data class SleepingPillSession(
        val intendedAudience: String?,
        val endTimeZulu: String?,
        val level: String?,
        val length: Int,
        val format: String,
        val language: String,
        val sessionId: String,
        val video: String?,
        val abstract: String?,
        val title: String,
        val speakernoteUrl: String?,
        val room: String?,
        val conferenceId: String,
        val startTimeZulu: String?,
        val speakers: Array<Speaker>,
        val startTime: Date?,
        val endTime: Date?
) {
    fun convertToTalk(eventName:String) : Talk {
        return Talk(eventName = eventName, talkId=sessionId, abstract = abstract, startTime = convertToZuluTime(startTime),
                endTime = convertToZuluTime(endTime), language = language, level = level,
                notes = speakernoteUrl, speakers = speakers,
                title = title, videoUrl = video, eventDataUrl = "", lengthMinutes = null, eventId = conferenceId)
    }

}
fun convertToZuluTime(date : Date?) :String? {
    return date?.toString()
}
