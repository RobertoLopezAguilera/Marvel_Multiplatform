package com.example.marvel_multiplatform.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.* // ✅ ESTA LÍNEA ES NECESARIA
import kotlinx.serialization.json.Json

object MarvelApi {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    private const val BASE_URL = "https://gateway.marvel.com/v1/public"
    private const val PUBLIC_KEY = "e4254e10e4260c3d3febaf70051f0617"
    private const val PRIVATE_KEY = "3099a8a5de28d5a5ec17d4eeea36df0af39b1320"

    suspend fun getCharacters(limit: Int = 10): List<Character> {
        val ts = System.currentTimeMillis().toString()
        val hash = md5("$ts$PRIVATE_KEY$PUBLIC_KEY")

        val response: MarvelResponse<Character> = client.get("$BASE_URL/characters") {
            url {
                parameters.append("ts", ts)
                parameters.append("apikey", PUBLIC_KEY)
                parameters.append("hash", hash)
                parameters.append("limit", limit.toString())
            }
        }.body()

        return response.data.results
    }
}
