package com.example.githublookup.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "avatar_url")
    val avatarUrl: String?,
    @Json(name = "login")
    val login: String,
    @Json(name = "name")
    val name: String?,
    @Json(name = "bio")
    val bio: String?,
    @Json(name = "followers")
    val followers: Int?,
    @Json(name = "public_repos")
    val publicRepos: Int?
)

val sampleUser = User(
    login = "octocat",
    name = "The Octocat",
    avatarUrl = "https://avatars.githubusercontent.com/u/583231?v=4",
    bio = "A test GitHub mascot",
    followers = 9001,
    publicRepos = 42
)