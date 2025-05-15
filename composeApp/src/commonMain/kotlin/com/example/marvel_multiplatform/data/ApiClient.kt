package com.example.marvel_multiplatform.data

class MarvelRepository(private val apiService: MarvelApiService) {
    suspend fun fetchPersonajes(): List<Personaje> = apiService.getPersonajes()
}

