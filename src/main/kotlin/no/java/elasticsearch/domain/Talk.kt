package no.java.elasticsearch.domain

import java.time.LocalDate
import java.util.Date

data class Talk(var eventName:String,
                var eventSlug:String,
                var talkId:String,
                var title:String,
                var abstract:String,
                var eventDataUrl:String,
                var level:String,
                var lengthMinutes:Int,
                var language:String,
                var videoUrl:String?,
                var notes:String?,
                var startTime:String, //zulu timestring
                var endTime:String, //zulu timestring
                var speakers:Array<Speaker> )