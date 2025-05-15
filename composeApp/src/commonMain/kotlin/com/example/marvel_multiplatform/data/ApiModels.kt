package com.example.marvel_multiplatform.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarvelResponse<T>(
    val code: Int,
    val status: String,
    val data: MarvelDataContainer<T>
)

@Serializable
data class MarvelDataContainer<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)

@Serializable
data class Personaje(
    val id: Int,
    val name: String,
    val description: String,
    @SerialName("thumbnail") val thumbnail: Thumbnail
)

@Serializable
data class Thumbnail(
    val path: String,
    val extension: String
) {
    fun getUrl(): String = "$path.$extension".replace("http://", "https://")
}
