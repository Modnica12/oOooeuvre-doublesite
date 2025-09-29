package presentation.ui

import org.jetbrains.compose.web.css.*

object MyCSS : StyleSheet() {

    val rootContainer by style {
        margin(0.px)
        overflow("hidden")
        width(100.vw)
        maxWidth(100.vw)
    }

    val header by style {
        alignContent(AlignContent.End)
        width(100.percent)
        backgroundColor(Color.transparent)
        position(Position.Fixed)
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.SpaceBetween)
        property("z-index", "3")
        property("mix-blend-mode", "difference")
        overflow("hidden")
    }

    val logoText by style {
        fontSize(164.pt)
        lineHeight(164.pt)
        paddingTop(8.px)
        paddingLeft(16.px)
        color(Color.white)
        makeNonSelectable()
        fontFamily("Arial")
        overflow("hidden")
    }

    val mainContainer by style {
        width(100.vw)
        position(Position.Relative)
        overflow("hidden")
    }

    val startImageContainer by style {
        height(100.vh)
        makeNonSelectable()
        overflow("hidden")
    }

    val startText by style {
        color(Color.black)
        position(Position.Fixed)
        marginLeft(50.vw - 36.pt)
        marginTop(50.vh - 60.pt)
        fontSize(120.pt)
        lineHeight(120.pt)
        fontFamily("Arial")
        overflow("hidden")
    }

    val fullWidthContentBlock by style {
        position(Position.Relative)
        width(100.vw)
        // on windows scrollbar takes space and creates horizontal scroll
        maxWidth(99.vw)
        backgroundColor(Color.white)
        overflow("hidden")
    }

    val fullHeightContentBlock by style {
        position(Position.Relative)
        height(100.vh)
        backgroundColor(Color.white)
        overflow("hidden")
    }

    val mainText by style {
        right(16.px)
        position(Position.Absolute)
        top(8.px)
        width(60.vw)
        height(50.vh)
        fontSize(30.pt)
        lineHeight(20.pt)
        textAlign("right")
        property("text-overflow", "ellipsis")
        color(Color.white)
        property("mix-blend-mode", "difference")
        fontFamily("Arial")
        overflow("hidden")
    }

    val clockBlockForHorizontal by style {
        position(Position.Absolute)
        right(16.px)
        bottom(8.px)
        makeNonSelectable()
        overflow("hidden")
    }

    val clockBlockForVertical by style {
        position(Position.Absolute)
        bottom(40.vh)
        width(100.vw)
        makeNonSelectable()
        overflow("hidden")
    }

    val clockTextForHorizontal by style {
        color(Color.black)
        fontSize(120.pt)
        lineHeight(80.pt)
        fontFamily("Arial")
        overflow("hidden")
    }

    val clockTextForVertical by style {
        color(Color.black)
        fontSize(188.pt)
        lineHeight(104.pt)
        width(100.vw)
        textAlign("center")
        fontFamily("Arial")
        overflow("hidden")
    }

    val contactsBlock by style {
        position(Position.Absolute)
        left(16.px)
        bottom(8.px)
        overflow("hidden")
    }

    val contactText by style {
        color(Color.black)
        fontSize(48.pt)
        textDecoration("none")
        fontFamily("Arial")
        overflow("hidden")
        marginTop(16.px)
    }

    private fun CSSBuilder.makeNonSelectable() {
        property("user-select", "none")
        property("-webkit-user-select", "none")
        property("-moz-user-select", "none")
        property("-ms-user-select", "none")
    }

}