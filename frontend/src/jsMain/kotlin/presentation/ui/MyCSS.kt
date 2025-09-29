package presentation.ui

import org.jetbrains.compose.web.css.*

object MyCSS : StyleSheet() {

    val rootContainer by style {
        margin(0.px)
        overflowX("hidden")
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
    }

    val logoText by style {
        fontSize(164.pt)
        lineHeight(164.pt)
        paddingTop(8.px)
        paddingLeft(16.px)
        color(Color.white)
        makeNonSelectable()
        fontFamily("Arial")
        fontWeight("900")
    }

    val mainContainer by style {
        width(100.vw)
        position(Position.Relative)
    }

    val startImageContainer by style {
        height(100.vh)
        overflowX("hidden")
        makeNonSelectable()
    }

    val startText by style {
        color(Color.black)
        position(Position.Fixed)
        marginLeft(50.vw - 36.pt)
        marginTop(50.vh - 60.pt)
        fontSize(120.pt)
        lineHeight(120.pt)
        fontFamily("Arial")
    }

    val fullWidthContentBlock by style {
        position(Position.Relative)
        width(100.vw)
        backgroundColor(Color.white)
    }

    val fullHeightContentBlock by style {
        position(Position.Relative)
        height(100.vh)
        backgroundColor(Color.white)
        overflowY("hidden")
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
    }

    val clockBlockForHorizontal by style {
        position(Position.Absolute)
        right(16.px)
        bottom(8.px)
        makeNonSelectable()
    }

    val clockBlockForVertical by style {
        position(Position.Absolute)
        bottom(40.vh)
        width(100.vw)
        makeNonSelectable()
    }

    val clockTextForHorizontal by style {
        color(Color.black)
        fontSize(120.pt)
        lineHeight(80.pt)
        fontFamily("Arial")
    }

    val clockTextForVertical by style {
        color(Color.black)
        fontSize(188.pt)
        lineHeight(96.pt)
        width(100.vw)
        textAlign("center")
        fontFamily("Arial")
    }

    val contactsBlock by style {
        position(Position.Absolute)
        left(16.px)
        bottom(8.px)
    }

    val contactText by style {
        color(Color.black)
        fontSize(48.pt)
        textDecoration("none")
        fontFamily("Arial")
    }

    private fun CSSBuilder.makeNonSelectable() {
        property("user-select", "none")
        property("-webkit-user-select", "none")
        property("-moz-user-select", "none")
        property("-ms-user-select", "none")
    }

}