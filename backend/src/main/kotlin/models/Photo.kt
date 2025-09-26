package dev.oOooeuvre.models

import kotlinx.serialization.Serializable

// TODO: вынести бы эту модельку, но нужно объединить проекты в один градловый. И сделать их модулямм
@Serializable
data class Photo(
    val url: String,
    val text: String? = null,
)