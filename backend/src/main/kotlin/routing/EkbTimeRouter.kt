package dev.oOooeuvre.routing

import dev.oOooeuvre.models.ClockTime
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.date.*

private const val EKB_TIME_PATH = "/ekbtime"

private const val EKB_TIME_ZONE_OFFSET = 5 * 60 * 60 * 1000L

fun Routing.setUpEkbTimeRoute() {
    get(EKB_TIME_PATH) {
        val ekbDate = GMTDate().plus(EKB_TIME_ZONE_OFFSET)
        val ekbClockTime = ClockTime(hours = ekbDate.hours, minutes = ekbDate.minutes, seconds = ekbDate.seconds)
        call.respond(ekbClockTime)

        println("$EKB_TIME_PATH RESPOND $ekbClockTime")
    }
}