package com.example.marvel_multiplatform.data

interface MarvelApiService {
    suspend fun getPersonajes(): List<Personaje>
}
