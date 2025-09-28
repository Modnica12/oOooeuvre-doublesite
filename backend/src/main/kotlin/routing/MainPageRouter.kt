package dev.oOooeuvre.routing

import dev.oOooeuvre.models.MainPage
import dev.oOooeuvre.providers.getEkbTime
import dev.oOooeuvre.providers.getPhotos
import io.ktor.server.response.*
import io.ktor.server.routing.*

private const val MAIN_PAGE_PATH = "/mainpage"

fun Routing.setUpMainPageRouter() {
    get(MAIN_PAGE_PATH) {
        val mainPage = MainPage(
            photos = getPhotos(),
            clockTime = getEkbTime(),
        )
        call.respond(mainPage)
    }
}