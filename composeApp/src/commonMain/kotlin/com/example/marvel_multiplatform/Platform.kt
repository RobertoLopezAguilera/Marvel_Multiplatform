package com.example.marvel_multiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform