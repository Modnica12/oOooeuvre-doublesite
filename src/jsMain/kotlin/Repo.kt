object Repo {

    val photos = listOf(
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2016.58.23.jpeg",
        ),
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2017.00.33.jpeg",
        ),
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2016.57.49.jpeg",
        ),
        Photo(
            url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/photo_2025-07-16%2017.00.54.jpeg",
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
        Logo(common = "o", rare = "ɵ"),
        Logo(common = "o", rare = "ѳ"),
        Logo(common = "ӧ", rare = "ӫ"),
    )

    val refs = listOf(
        Ref(text = "tg", url = "https://www.google.com"),
        Ref(text = "tg2", url = "https://www.google.com"),
    )

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

data class Ref(
    val text: String,
    val url: String,
)


fun convertBinaryToSymbols(binary: String): String = binary
    .map { symbol ->
        when (symbol) {
            '0' -> "○"
            '1' -> "●"
            else -> " "
        }
    }
        .joinToString(separator = "")
