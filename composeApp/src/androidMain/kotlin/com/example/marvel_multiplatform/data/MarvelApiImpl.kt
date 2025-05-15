package com.example.data

import com.example.marvel_multiplatform.data.MarvelApiService
import com.example.marvel_multiplatform.data.MarvelResponse
import com.example.marvel_multiplatform.data.Personaje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.security.MessageDigest

class MarvelApiImpl : MarvelApiService {

    private val api: MarvelHttpService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MarvelHttpService::class.java)
    }

    override suspend fun getPersonajes(): List<Personaje> = withContext(Dispatchers.IO) {
        val ts = System.currentTimeMillis().toString()
        val publicKey = "e4254e10e4260c3d3febaf70051f0617"
        val privateKey = "3099a8a5de28d5a5ec17d4eeea36df0af39b1320"
        val hash = generateHash(ts, privateKey, publicKey)

        val response = api.getCharacters(
            ts = ts,
            apikey = publicKey,
            hash = hash
        )

        response.data.results
    }

    private fun generateHash(ts: String, privateKey: String, publicKey: String): String {
        val input = ts + privateKey + publicKey
        val md = MessageDigest.getInstance("MD5")
        val bytes = md.digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}

interface MarvelHttpService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): MarvelResponse<Personaje>
}
