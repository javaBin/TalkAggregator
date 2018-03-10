package no.java.elasticsearch.domain.sleepingpill

import no.java.elasticsearch.domain.Speaker
import no.java.elasticsearch.domain.Talk
import java.util.Date

data class SleepingPillSession(
        var intendedAudience: String?,
        var endTimeZulu: String,
        var level: String,
        var length: Int,
        var format: String,
        var language: String,
        var sessionId: String,
        var video: String?,
        var abstract: String,
        var title: String,
        var speakernoteUrl: String?,
        var room: String?,
        var conferenceId: String,
        var startTimeZulu: String,
        var speakers: Array<Speaker>,
        var startTime: Date,
        var endTime: Date
) {

    fun convertToTalk(eventName:String) : Talk {
        return Talk(eventName = eventName, talkId=sessionId, abstract = abstract, startTime = convertToZuluTime(startTime),
                endTime = convertToZuluTime(endTime), language = language, level = level,
                notes = speakernoteUrl, speakers = speakers,
                title = title, videoUrl = video, eventDataUrl = "", lengthMinutes = null, eventId = conferenceId)
    }

    fun convertToZuluTime(date : Date) :String {
        return date.toString()
    }
}
