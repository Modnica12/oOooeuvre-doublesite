package presentation.ui

import presentation.NumberToSymbolsConverter
import androidx.compose.runtime.*
import kotlinx.browser.window
import models.Logo
import data.models.Photo
import models.Ref
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.get
import presentation.ViewModel
import presentation.isHorizontal
import presentation.isMobile

private const val ROOT_ELEMENT_ID = "root"

private const val EVENT_TYPE_SCROLL = "scroll"
private const val EVENT_TYPE_VISIBILITY_CHANGE = "visibilitychange"

private const val FIELD_VISIBILITY_STATE = "visibilityState"

fun main() {
    renderComposable(rootElementId = ROOT_ELEMENT_ID) {
        Div(attrs = { MyCSS.rootContainer }) {
            mainPage()
        }
    }
}

@Composable
private fun mainPage() {
    val viewModel = remember { ViewModel() }
    var verticalScroll: Float by remember { mutableStateOf(0f) }
    Style(MyCSS)

    Header(attrs = { classes(MyCSS.header) }) {
        Span(attrs = { classes(MyCSS.logoText) }) {
            Text(viewModel.state.logo.toPrettyString())
        }
    }

    Div(attrs = { MyCSS.mainContainer }) {
        StartImage(verticalScroll)
        if (!viewModel.state.isLoading) {
            if (isHorizontal()) {
                ImagesListHorizontal(viewModel.state.photos)
            } else {
                ImagesListVertical(viewModel.state.photos)
            }
            Footer(viewModel.state.refs, viewModel.state.hours, viewModel.state.minutes, viewModel.state.seconds)
        }
    }

    window.addEventListener(EVENT_TYPE_SCROLL, {
        verticalScroll = window.scrollY.toFloat()
    })

    window.addEventListener(EVENT_TYPE_VISIBILITY_CHANGE, {
        val visibilityState = window.document[FIELD_VISIBILITY_STATE] as? String
        viewModel.visibilityChanged(visibilityState)
    })
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
private fun StartImage(verticalScroll: Float) {
    Div(attrs = { classes(MyCSS.startImageContainer) }) {
        Logo.default.symbolsList.forEachIndexed { index, symbol ->
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
private fun ImagesListVertical(photos: List<Photo>) {
    photos.forEach { photo ->
        Div(
            attrs = {
                classes(MyCSS.fullWidthContentBlock)
                style {
                    display(DisplayStyle.Flex)
                    backgroundColor(Color.black)
                }
            }
        ) {
            Img(
                src = photo.url,
                attrs = {
                    style {
                        width(100.vw)
                        flex(1)
                    }
                },
            )
        }
    }
}

@Composable
private fun ImagesListHorizontal(photos: List<Photo>) {
    photos.forEachIndexed { index, photo ->
        Div(attrs = {
            classes(MyCSS.fullWidthContentBlock)
            style { height(50.vh) }
        }) {
            Img(
                src = photo.url,
                attrs = {
                    style {
                        property("pointer-events", "none")
                        height(50.vh)
                        overflow("hidden")
                    }
                },
            )
            photo.text?.let { text ->
                Div(
                    attrs = {
                        classes(MyCSS.mainText)
                        onClick {
                            window.navigator.clipboard.writeText(text)
                        }
                    }
                ) {
                    Text(text)
                }
            }
        }
    }
}

@Composable
private fun Footer(refs: List<Ref>, hours: Int, minutes: Int, seconds: Int) {
    Div(attrs = { classes(MyCSS.fullHeightContentBlock) }) {
        Clock(hours, minutes, seconds)
        Contacts(refs)
    }
}

@Composable
private fun Clock(hours: Int, minutes: Int, seconds: Int) {
    val numberToSymbolsConverter = NumberToSymbolsConverter()
    Div(attrs = {
        classes(if (isHorizontal()) MyCSS.clockBlockForHorizontal else MyCSS.clockBlockForVertical)
    }) {
        listOf(hours, minutes, seconds).forEach { timeUnit ->
            Div(attrs = { classes(if (isHorizontal()) MyCSS.clockTextForHorizontal else MyCSS.clockTextForVertical) }) {
                Text(numberToSymbolsConverter(timeUnit).joinToString(separator = ""))
            }
        }
    }
}

@Composable
private fun Contacts(refs: List<Ref>) {
    Div(attrs = {
        classes(MyCSS.contactsBlock)
        style {
            // fix for mobile screen's rounded corners
            if (!isHorizontal()) {
                bottom(48.px)
                left(64.px)
            }
        }
    }) {
        refs.forEach { ref ->
            Div {
                A(
                    href = ref.url,
                    attrs = {
                        classes(MyCSS.contactText)
                        style {
                            fontSize(ref.size.pt * if (isHorizontal()) 1 else 2)
                        }
                    }
                ) {
                    Text(ref.text)
                }
            }
        }
    }
}

private fun getAlphaDivider(): Float =
    when {
        !isHorizontal() -> 3.5f
        isMobile() -> (0.0008f * window.screen.height) // Calculate divider for different mobile height (mobile/tablet)
        else -> 1.1f
    }
