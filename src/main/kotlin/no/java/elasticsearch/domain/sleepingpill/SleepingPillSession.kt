package no.java.elasticsearch.domain.sleepingpill

import java.util.Date

class SleepingPillSession(
        var intendedAudience: String,
        var endTimeZulu: String,
        var level: String,
        var length: Int,
        var format: String,
        var language: String,
        var sessionId: String,
        var video: String,
        var abstract: String,
        var title: String,
        var speakernoteUrl: String,
        var room: String,
        var conferenceId: String,
        var startTimeZulu: String,
        var speakers: Array<SleepingPillSpeaker>,
        var startTime: Date,
        var endTime: Date
)