package com.example.marvel_multiplatform.api

import java.security.MessageDigest

fun md5(input: String): String {
    val bytes = MessageDigest.getInstance("MD5").digest(input.toByteArray())
    return bytes.joinToString("") { "%02x".format(it) }
}
