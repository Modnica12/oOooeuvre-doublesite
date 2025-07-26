import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*

object MyCSS : StyleSheet() {

    val header by style {
        alignContent(AlignContent.End)
        width(100.percent)
        backgroundColor(Color.transparent)
        position(Position.Fixed)
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.SpaceBetween)
        property("mix-blend-mode", "difference")
    }

    val main by style {
        width(100.percent)
        position(Position.Relative)
    }

    val startImage by style {
        height(100.vh)
//        backgroundColor(Color.white)
        property("z-index", "3")
    }

    val logoText by style {
        fontSize(164.pt)
        lineHeight(164.pt)

        paddingTop(8.px)
        paddingLeft(16.px)

        color(Color.white)
    }

    val imageContainerHorizontal by style {
        width(100.percent)
        height(100.vh)
        backgroundColor(Color.white)
        justifyContent(JustifyContent.FlexEnd)
        display(DisplayStyle.Flex)
    }

    val imageContainerVertical by style {
        display(DisplayStyle.Flex)
        width(100.vw)
        backgroundColor(Color.white)
    }

    @OptIn(ExperimentalComposeWebApi::class)
    val mainText by style {
        height(5.vh)
        width(100.percent)
        color(Color.black)
        display(DisplayStyle.Flex)
//        overflow("hidden")
        fontSize(64.pt)
        lineHeight(5.vh)
        padding(0.px)
        backgroundColor(Color.white)
        whiteSpace("nowrap")
    }

}