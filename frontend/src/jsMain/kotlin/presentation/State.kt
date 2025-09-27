package presentation

import data.models.Photo
import models.Logo

data class State(
    val logo: Logo = Logo.default,
    val photos: List<Photo> = emptyList(),
    val hours: Int = 0,
    val minutes: Int = 0,
    val seconds: Int = 0,
    val isLoading: Boolean = true,
)