package com.example.githublookup.data.api

import com.example.githublookup.data.models.Repo
import com.example.githublookup.data.models.SearchResponse
import com.example.githublookup.data.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): Response<User>

    @GET("users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<Repo>>

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<SearchResponse>
}