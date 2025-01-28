package com.app.antweber.model

import com.google.gson.annotations.SerializedName

data class UnsplashResponse(
    val id: String,
    @SerializedName("urls")
    val urls: Urls,
    @SerializedName("user")
    val user: User
)

data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)

data class User(
    val id: String,
    val username: String,
    @SerializedName("name")
    val name: String
)
