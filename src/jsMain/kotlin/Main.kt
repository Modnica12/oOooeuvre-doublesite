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
        StartImage(verticalScroll)
        if (isHorizontal) {
            ImagesListHorizontal()
        } else {
            ImagesListVertical()
        }
        Footer(isHorizontal, (START_TIME / 1000).toInt())
    }

    LaunchedEffect(null) {
        while (true) {
            delay(5000)
            RandomWithoutRepeating@ while (true) {
                val newLogo = Repo.logos.random()
                if (newLogo != logo) {
                    logo = newLogo
                    break@RandomWithoutRepeating
                }
            }
        }
    }

    window.addEventListener("scroll", {
        verticalScroll = window.scrollY.toFloat()
    })
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
private fun StartImage(verticalScroll: Float) {
    Div(attrs = { classes(MyCSS.startImageContainer) }) {
        listOf("○", "●", "○", "○").forEachIndexed { index, symbol ->
            Div(attrs = {
                classes(MyCSS.startText)
                style {
                    val limit = 100.vh.value
                    val limitedScroll = (verticalScroll - limit).coerceAtLeast(0f)
                    transform {
                        val limitedHorizontalScroll = verticalScroll.coerceAtMost(limit).px
                        val horizontalScroll = if (index < 2) -limitedHorizontalScroll else limitedHorizontalScroll
                        val logoBuildingTranslation = horizontalScroll * (-(index % 2) + 1.5)
                        translateX(logoBuildingTranslation)
                    }
                    val alphaLimitedScroll = limitedScroll / getAlphaDivider()
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
        Div(attrs = { classes(MyCSS.fullWidthContentBlock); style { display(DisplayStyle.Flex); backgroundColor(Color.black) } }) {
            Img(
                src = photo.url,
                attrs = { style { width(100.vw); flex(1) } },
            )
        }
    }
}

@Composable
private fun ImagesListHorizontal() {
    Repo.photos.forEach { photo ->
        Div(attrs = {
            classes(MyCSS.fullWidthContentBlock)
            style {
                height(50.vh)
                property("pointer-events", "none")
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
            photo.text?.let { text ->
                Div(attrs = { classes(MyCSS.mainText) }) {
                    Text(convertBinaryToSymbols(text))
                }
            }
        }
    }
}

@Composable
private fun Footer(isHorizontal: Boolean, startTimeInSeconds: Int) {
    Div(attrs = { classes(MyCSS.fullHeightContentBlock) }) {
        Clock(isHorizontal, startTimeInSeconds)
        Contacts()
    }
}

@Composable
private fun Clock(isHorizontal: Boolean, startTimeInSeconds: Int) {
    val numberToSymbolsConverter = NumberToSymbolsConverter()
    var hours: Int by remember { mutableStateOf(startTimeInSeconds / 60 / 60) }
    var minutes: Int by remember { mutableStateOf((startTimeInSeconds - (hours * 60 * 60)) / 60) }
    var seconds: Int by remember { mutableStateOf(startTimeInSeconds - minutes * 60 - hours * 60 * 60) }

    Div(attrs = {
        classes(if (isHorizontal) MyCSS.clockBlockForHorizontal else MyCSS.clockBlockForVertical)
    }) {
        listOf(hours, minutes, seconds).forEach { timeUnit ->
            Div(attrs = { classes(if (isHorizontal) MyCSS.clockTextForHorizontal else MyCSS.clockTextForVertical) }) {
                Text(numberToSymbolsConverter(timeUnit).joinToString(separator = ""))
            }
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
                A(href = ref.url, attrs = { classes(MyCSS.contactText); style { fontSize(48.pt) } }) {
                    Text(ref.text)
                }
            }
        }
    }
}

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

private fun getAlphaDivider(): Float =
    when {
        !isHorizontal() -> 3.5f
        isMobile() -> (0.0008f * window.screen.height) // Calculate divider for different mobile height (mobile/tablet)
        else -> 1.1f
    }
