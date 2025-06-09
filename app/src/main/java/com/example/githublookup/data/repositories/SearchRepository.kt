package com.example.githublookup.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githublookup.data.api.GithubApiService
import com.example.githublookup.data.models.User
import com.example.githublookup.data.remote.UserSearchPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiService: GithubApiService) {

    fun searchUsersPaged(query: String): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { UserSearchPagingSource(apiService, query) }).flow
    }

}