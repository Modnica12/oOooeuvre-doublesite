package dev.oOooeuvre

import Logo
import MyCSS
import NumberToSymbolsConverter
import Repo
import androidx.compose.runtime.*
import convertBinaryToSymbols
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import kotlin.js.Date

private val START_TIME = millisecondDiff(Date())

fun main() {
    renderComposable(rootElementId = "root") {
        Div(attrs = { MyCSS.rootContainer }) {
            mainPage()
        }
    }
}

@Composable
private fun mainPage() {
    var logo: Logo by remember { mutableStateOf(Repo.logos.first()) }
    var verticalScroll: Float by remember { mutableStateOf(0f) }
    val isHorizontal = isHorizontal()

    Style(MyCSS)

    Header(attrs = { classes(MyCSS.header) }) {
        Span(attrs = { classes(MyCSS.logoText) }) {
            Text("${logo.common}${logo.horizontalSpacing.spacing}${logo.rare}${logo.horizontalSpacing.spacing}${logo.common}${logo.horizontalSpacing.spacing}${logo.common}")
        }
    }

    Div(attrs = { MyCSS.mainContainer }) {
        StartImage(verticalScroll, isHorizontal)
        if (isHorizontal) {
            ImagesListHorizontal()
        } else {
            ImagesListVertical()
        }
        Footer((START_TIME / 1000).toInt())
    }

    LaunchedEffect(null) {
        while (true) {
            delay(5000)
            logo = Repo.logos.random()
        }
    }

    window.addEventListener("scroll", {
        verticalScroll = window.scrollY.toFloat()
    })
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
private fun StartImage(verticalScroll: Float, isHorizontal: Boolean) {
    Div(attrs = { classes(MyCSS.startImageContainer) }) {
        listOf("○", "●", "○", "○").forEachIndexed { index, symbol ->
            Div(attrs = {
                classes(MyCSS.mainTextVertical)
                style {
                    val limit = 100.vh.value
                    val limitedScroll = (verticalScroll - limit).coerceAtLeast(0f)
                    transform {
                        val limitedHorizontalScroll = verticalScroll.coerceAtMost(limit).px
                        val horizontalScroll = if (index < 2) -limitedHorizontalScroll else limitedHorizontalScroll
                        val logoBuildingTranslation = horizontalScroll * (-(index % 2) + 1.5)
                        translateX(logoBuildingTranslation)
                    }
                    val alphaLimitedScroll = limitedScroll / if (isHorizontal) 1.1 else 3.5
                    val alpha = (255 - alphaLimitedScroll) / 255
                    color(rgba(0, 0, 0, alpha))
                }
            }) {
                Text(symbol)
            }
        }
    }
}

@Composable
private fun ImagesListVertical() {
    Repo.photos.forEach { photo ->
        Div(attrs = { classes(MyCSS.fullWidthContentBlock) }) {
            Img(
                src = photo.url,
                attrs = { style { width(100.vw) } },
            )
        }
    }
}

@Composable
private fun ImagesListHorizontal() {
    Div(attrs = { MyCSS.imagesGrid }) {
        Repo.photos.forEach { photo ->
            Div(attrs = {
                classes(MyCSS.fullWidthContentBlock)
                style {
                    height(50.vh)
                    property("pointer-events", "none")
                    position(Position.Relative)
                }
            }) {
                Img(
                    src = photo.url,
                    attrs = {
                        style {
                            height(50.vh)
                        }
                    },
                )
                Div(attrs = {
                    style {
                        right(16.px)
                        position(Position.Absolute)
                        top(8.px)
                        width(50.vw)
                        height(50.vh)
                        fontSize(32.pt)
                    }
                }) {
                    Text(
                        convertBinaryToSymbols("11010000 10010011 11010001 10000000 11010001 10001111 11010000 10110111 11010000 10111101 11010000 10111110 11010000 10110101")
                    )
                }
            }
        }
    }
}

@Composable
private fun Footer(startTimeInSeconds: Int) {
    Div(attrs = { classes(MyCSS.fullHeightContentBlock) }) {
        Clock(startTimeInSeconds)
        Contacts()
    }
}

@Composable
private fun Clock(startTimeInSeconds: Int) {
    val numberToSymbolsConverter = NumberToSymbolsConverter()
    var hours: Int by remember { mutableStateOf(startTimeInSeconds / 60 / 60) }
    var minutes: Int by remember { mutableStateOf((startTimeInSeconds - (hours * 60 * 60)) / 60) }
    var seconds: Int by remember { mutableStateOf(startTimeInSeconds - minutes * 60 - hours * 60 * 60) }

    Div(attrs = { classes(MyCSS.clockBlock) }) {
        Div(attrs = { classes(MyCSS.clockText) }) {
            Text(numberToSymbolsConverter(hours).joinToString(separator = ""))
        }
        Div(attrs = { classes(MyCSS.clockText) }) {
            Text(numberToSymbolsConverter(minutes).joinToString(separator = ""))
        }
        Div(attrs = { classes(MyCSS.clockText) }) {
            Text(numberToSymbolsConverter(seconds).joinToString(separator = ""))
        }
    }

    LaunchedEffect(null) {
        while (true) {
            delay(1000)
            when {
                seconds < 59 -> {
                    seconds += 1
                }
                minutes < 59 -> {
                    seconds = 0
                    minutes += 1
                }
                hours < 23 -> {
                    seconds = 0
                    minutes = 0
                    hours += 1
                }
                else -> {
                    seconds = 0
                    minutes = 0
                    hours = 0
                }
            }
        }
    }
}

@Composable
private fun Contacts() {
    Div({ classes(MyCSS.contactsBlock) }) {
        Repo.refs.forEach { ref ->
            Div {
                A(href = ref.url, attrs = { classes(MyCSS.contactText); style { fontSize(if (isMobile()) 24.pt else 48.pt) } }) {
                    Text(ref.text)
                }
            }
        }
    }
}

@Composable
private fun isHorizontal(): Boolean = with (window.screen) {
    width > height
}

private fun isMobile(): Boolean = window.navigator.maxTouchPoints > 0

fun millisecondDiff(myDate: Date): Double {
    val midnightDay = myDate.getUTCDate()
    val midnightMonth = myDate.getUTCMonth()
    val midnightYear = myDate.getUTCFullYear()
    val midnightMilliseconds = Date(midnightYear, midnightMonth, midnightDay).getTime()
    return myDate.getTime() - midnightMilliseconds
}
