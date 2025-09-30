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
        Ref(text = "my tg", url = "https://t.me/oOooeuvre", size = 64),
        Ref(text = "and special thx to leonidshkn", url = "https://t.me/leonidshkn", size = 32),
    )

}