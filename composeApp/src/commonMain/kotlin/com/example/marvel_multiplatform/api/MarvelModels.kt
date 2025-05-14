package com.example.marvel_multiplatform.api

import kotlinx.serialization.Serializable

@Serializable
data class MarvelResponse<T>(
    val code: Int,
    val status: String,
    val data: DataContainer<T>
)

@Serializable
data class DataContainer<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

@Serializable
data class Thumbnail(
    val path: String,
    val extension: String
) {
    fun getUrl() = "$path.$extension".replace("http://", "https://")
}
