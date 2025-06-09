package com.example.githublookup.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githublookup.data.api.GithubApiService
import com.example.githublookup.data.models.User

class UserSearchPagingSource(private val apiService: GithubApiService, private val query: String) :
    PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: 1
            val response =
                apiService.searchUsers(query = query, page = page, perPage = params.loadSize)

            if (response.isSuccessful) {
                val users = response.body()?.users ?: emptyList()
                LoadResult.Page(
                    data = users,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (users.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception("HTTP ${response.code()}: ${response.message()}"))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}