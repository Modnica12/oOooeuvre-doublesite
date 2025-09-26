package data

import models.ClockTime

class TimeRepository {

    private val ktorDataSource = KtorDataSource()

    suspend fun getEkbTimeMillis(): Result<ClockTime> = kotlin.runCatching {
        ktorDataSource.getEkbTime()
    }

}