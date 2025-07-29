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

    val startImageHorizontal by style {
        height(100.vh)
        property("z-index", "3")
    }

    val startImageVertical by style {
        height(100.vh)
//        justifyContent(JustifyContent.Center)
//        display(DisplayStyle.Flex)
        property("z-index", "3")
        overflowX("hidden")
    }

    val logoText by style {
        fontSize(164.pt)
        lineHeight(164.pt)

        paddingTop(8.px)
        paddingLeft(16.px)

        color(Color.white)
    }

    val imageContainerHorizontal by style {
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

    val mainTextHorizontal by style {
        height(5.vh)
        color(Color.black)
        display(DisplayStyle.Flex)
//        overflow("hidden")
        fontSize(64.pt)
        lineHeight(5.vh)
        padding(0.px)
        backgroundColor(Color.white)
        whiteSpace("nowrap")
    }

    @OptIn(ExperimentalComposeWebApi::class)
    val mainTextVertical by style {
        color(Color.black)
//        display(DisplayStyle.Flex)

//        position(Position.Absolute)
//        left(50.percent)
//        top(50.percent)
//        right(50.percent)
//        bottom(50.percent)
//        marginLeft((-50).px)
//        marginTop((-50).px)
//        marginRight((-50).px)
//        marginBottom((-50).px)


        position(Position.Fixed)
//        left(50.percent)
//        top(50.percent)
//        transform { translate((-50).percent,(-50).percent) }

        marginLeft(50.vw - 36.pt)
        marginTop(50.vh - 60.pt)

        fontSize(120.pt)
        lineHeight(120.pt)
    }

}