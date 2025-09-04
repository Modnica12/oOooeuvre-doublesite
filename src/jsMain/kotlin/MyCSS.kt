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
        property("mix-blend-mode", "difference")
    }

    val mainContainer by style {
        width(100.vw)
        position(Position.Relative)
    }

    val startImageContainer by style {
        height(100.vh)
        overflowX("hidden")
        property("user-select", "none")
        property("-webkit-user-select", "none")
        property("-moz-user-select", "none")
        property("-ms-user-select", "none")
    }

    val logoText by style {
        fontSize(164.pt)
        lineHeight(164.pt)
        paddingTop(8.px)
        paddingLeft(16.px)
        color(Color.white)
        property("user-select", "none")
        property("-webkit-user-select", "none")
        property("-moz-user-select", "none")
        property("-ms-user-select", "none")
    }

    val fullWidthContentBlock by style {
        display(DisplayStyle.Flex)
        width(100.vw)
        backgroundColor(Color.white)
    }

    val fullHeightContentBlock by style {
        position(Position.Relative)
        height(100.vh)
        backgroundColor(Color.white)
        overflowY("hidden")
        property("mix-blend-mode", "difference")
    }

    val imagesGrid by style {
        position(Position.Absolute)
        display(DisplayStyle.Grid)
        property("grid-template-columns", "repeat(3, 1fr)")
        property("grid-template-rows", "auto")
        width(100.vw)
        backgroundColor(Color.white)
        property("mix-blend-mode", "difference")
    }

    val mainTextVertical by style {
        color(Color.black)

        position(Position.Fixed)

        marginLeft(50.vw - 36.pt)
        marginTop(50.vh - 60.pt)

        fontSize(120.pt)
        lineHeight(120.pt)
    }

    val clockBlock by style {
        position(Position.Absolute)
        right(16.px)
        bottom(8.px)

        property("user-select", "none")
        property("-webkit-user-select", "none")
        property("-moz-user-select", "none")
        property("-ms-user-select", "none")
    }

    val clockText by style {
        color(Color.black)
//        position(Position.Relative)
        fontSize(120.pt)
        lineHeight(80.pt)
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
    }

}