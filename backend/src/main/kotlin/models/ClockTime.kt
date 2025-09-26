package dev.oOooeuvre.models

import kotlinx.serialization.Serializable

@Serializable
data class ClockTime(
    val hours: Int,
    val minutes: Int,
    val seconds: Int,
)