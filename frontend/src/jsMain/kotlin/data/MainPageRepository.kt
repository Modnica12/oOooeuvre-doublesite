package data

import data.models.MainPage

class MainPageRepository {

    private val ktorDataSource = KtorDataSource()

    suspend fun getMainPage(): Result<MainPage> = runCatching { ktorDataSource.getMainPage() }

}