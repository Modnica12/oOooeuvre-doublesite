import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.Color

object Repo {

    val photos = listOf(
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2017.00.33.jpeg",
        ),
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2016.58.23.jpeg",
        ),
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2017.00.54.jpeg",
        ),
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2016.57.49.jpeg",
        ),
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2016.59.44.jpeg",
        ),
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2016.58.55%20(1).jpeg",
        ),
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2017.01.38.jpeg",
        ),
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2017.01.48.jpeg",
        ),
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2016.58.38.jpeg",
        ),
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2017.00.09.jpeg",
        ),
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2016.59.22.jpeg",
        ),
    )

    val logos = listOf(
        Logo(common = "o", rare = "ø"),
        Logo(common = "○", rare = "●", horizontalSpacing = HorizontalSpacing.None),
        Logo(common = "●", rare = "○", horizontalSpacing = HorizontalSpacing.None),
        Logo(common = "o", rare = "ɵ"),
        Logo(common = "o", rare = "ѳ"),
        Logo(common = "ӧ", rare = "ӫ"),
    )

    val text =
            "○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○{●}{○○ ○}{●}{○}○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○{●}○{○ ○}●{○}○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○{●}{○○ ○}●{○}○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○{●}○○ {○}●{○}○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○{●}○○ {○}●{○}○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○{●}○○ {○}{●}{○}○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○\n" +
            "○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○ ○●○○"

}

fun parseText(): List<List<TextPart>> = Repo.text
    .lines()
    .map { line ->
        val result = mutableListOf<TextPart>()
        var currentText = ""
        line.forEach { symbol ->
            if (symbol in listOf('{', '}')) {
                if (currentText.isNotEmpty()) {
                    result.add(TextPart(currentText, if (symbol == '}') Color.red else Color.black))
                }
                currentText = ""
                return@forEach
            }
            currentText += symbol
        }
        result.add(TextPart(currentText, Color.black))
        result
}

data class Photo(
    val url: String,
)

data class Logo(
    val common: String,
    val rare: String,
    val horizontalSpacing: HorizontalSpacing = HorizontalSpacing.Thin,
)

enum class HorizontalSpacing(val spacing: String) {
    None(""),
    Thin(" "),
    Default(" "),
    Large("  ")
}

data class TextPart(
    val text: String,
    val color: CSSColorValue,
)
