package com.example.githublookup.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repo(
    @Json(name = "name")
    val name: String,
    @Json(name = "description")
    val description: String?,
    @Json(name = "stargazers_count")
    val numOfStars: Int?,
    @Json(name = "forks_count")
    val forksCount: Int?
)
