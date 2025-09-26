package data

import models.*

object Repo {

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