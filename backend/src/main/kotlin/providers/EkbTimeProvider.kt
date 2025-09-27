package dev.oOooeuvre.providers

import dev.oOooeuvre.models.ClockTime
import io.ktor.util.date.*

private const val EKB_TIME_ZONE_OFFSET = 5 * 60 * 60 * 1000L

fun getEkbTime(): ClockTime {
    val ekbDate = GMTDate().plus(EKB_TIME_ZONE_OFFSET)
    return ClockTime(hours = ekbDate.hours, minutes = ekbDate.minutes, seconds = ekbDate.seconds)
}