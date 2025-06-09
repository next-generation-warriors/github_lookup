package com.example.githublookup.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "items")
    val users: List<User>?,
    @Json(name = "total_count")
    val totalCount: Int?
)
