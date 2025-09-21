package models

data class State(
    val logo: Logo = Logo.default,
    val hours: Int = 0,
    val minutes: Int = 0,
    val seconds: Int = 0,
)