package presentation

import data.Repo
import data.models.Photo
import models.Logo
import models.Ref

data class State(
    val logo: Logo = Logo.default,
    val photos: List<Photo> = emptyList(),
    val hours: Int = 0,
    val minutes: Int = 0,
    val seconds: Int = 0,
    val refs: List<Ref> = Repo.refs,
    val isLoading: Boolean = true,
)