package data

import models.Photo

class PhotosRepository {

    private val ktorDataSource = KtorDataSource()

    suspend fun getPhotos(): Result<List<Photo>> = runCatching { ktorDataSource.getPhotos() }

}