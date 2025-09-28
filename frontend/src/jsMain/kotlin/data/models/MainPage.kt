package data.models

import kotlinx.serialization.Serializable

@Serializable
data class MainPage(
    val photos: List<Photo>,
    val clockTime: ClockTime,
)