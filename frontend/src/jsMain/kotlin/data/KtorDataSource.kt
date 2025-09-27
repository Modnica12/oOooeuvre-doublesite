package data

import data.models.ClockTime
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import data.models.MainPage

class KtorDataSource {

    val client = HttpClient(Js) {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    prettyPrint = true
                }
            )
        }

        defaultRequest {
            contentType(ContentType.Application.Json.withCharset(io.ktor.utils.io.charsets.Charsets.UTF_8))
        }
    }

    suspend fun getEkbTime(): ClockTime {
        return client.get("$HOST/ekbtime").body()
    }

    suspend fun getMainPage(): MainPage {
        return client.get("$HOST/mainpage").body()
    }

    companion object {

        private const val HOST = "http://0.0.0.0:7777"

    }

}