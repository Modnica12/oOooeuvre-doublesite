package dev.oOooeuvre

import Logo
import MyCSS
import Repo
import androidx.compose.runtime.*
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import parseText

fun main() {
    renderComposable(rootElementId = "root") {
        Div(attrs = { style { margin(0.px); overflowX("hidden") } }) {
            mainPage()
        }
    }
}

@Composable
private fun mainPage() {
    var logo: Logo by mutableStateOf(Repo.logos.first())
    var verticalScroll: Double by mutableStateOf(0.0)
    val isHorizontal = isHorizontal()

    Style(MyCSS)

    Header(attrs = { classes(MyCSS.header) }) {
        Span(attrs = { classes(MyCSS.logoText) }) {
            Text("${logo.common}${logo.horizontalSpacing.spacing}${logo.rare}${logo.horizontalSpacing.spacing}${logo.common}${logo.horizontalSpacing.spacing}${logo.common}")
        }
    }

    Div(attrs = { MyCSS.main }) {

        if (isHorizontal) {
            StartImageHorizontal(verticalScroll)
        } else {
            StartImageVertical(verticalScroll)
        }

        ImagesList(isHorizontal)
    }

    LaunchedEffect(null) {
        while (true) {
            delay(3000)
            logo = Repo.logos.random()
        }
    }

    window.addEventListener("scroll", {
        verticalScroll = window.scrollY
    })
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
private fun StartImageHorizontal(verticalScroll: Double) {
    Div(attrs = { classes(MyCSS.startImageHorizontal) }) {
        parseText().forEachIndexed { index, textParts ->
            Div(attrs = {
                classes(MyCSS.mainTextHorizontal)
                style {
                    transform {
                        val horizontalScroll = if (index % 2 == 0) -verticalScroll.px else verticalScroll.px
                        translateX(horizontalScroll * 0.8)
                    }
                }
            }) {
                textParts.forEach { textPart ->
                    Span(attrs = { style { color(textPart.color) } }) {
                        Text(textPart.text)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
private fun StartImageVertical(verticalScroll: Double) {
    Div(attrs = { classes(MyCSS.startImageVertical) }) {
        listOf("○", "●", "○", "○",).forEachIndexed { index, symbol ->
            Div(attrs = {
                classes(MyCSS.mainTextVertical)
                style {
                    transform {
                        val horizontalScroll = if (index < 2) -verticalScroll.px else verticalScroll.px
                        translateX(horizontalScroll * (-(index % 2) + 1.5))
                    }
                }
            }) {
                Text(symbol)
            }
        }
    }
}

@Composable
private fun ImagesList(isHorizontal: Boolean) {
    Repo.photos.forEach { photo ->
        Div(attrs = { classes(if (isHorizontal) MyCSS.imageContainerHorizontal else MyCSS.imageContainerVertical) }) {
            Img(
                src = photo.url,
                attrs = {
                    style { if (isHorizontal) height(100.vh) else width(100.vw) }
                },
            )
        }
    }
}

@Composable
private fun isHorizontal(): Boolean = with (window.screen) {
    width / height > 1280 / 855
//    width > height
}