package com.example.githublookup.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.githublookup.data.api.GithubApiService
import com.example.githublookup.data.models.User
import com.example.githublookup.data.remote.ReposPagingSource
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val apiService: GithubApiService) {

    suspend fun getUserDetails(userName: String): Result<User> {
        return try {
            val response = apiService.getUser(userName)
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) }
                    ?: Result.failure(Exception("Empty response"))
            } else {
                Result.failure(Exception("API error ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getUserReposPager(username: String) = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { ReposPagingSource(apiService, username) }
    ).flow
}