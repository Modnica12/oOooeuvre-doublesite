package models

data class Logo(
    val common: String,
    val rare: String,
    val horizontalSpacing: HorizontalSpacing = HorizontalSpacing.Thin,
) {

    val symbolsList: List<String>
        get() = listOf(common, rare, common, common)

    fun toPrettyString(): String =
        symbolsList
            .joinToString(separator = horizontalSpacing.spacing)

    companion object {

        val default = Logo(common = "○", rare = "●", horizontalSpacing = HorizontalSpacing.None)

    }

}