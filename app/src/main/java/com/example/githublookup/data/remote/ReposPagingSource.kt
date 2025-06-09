package com.example.githublookup.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githublookup.data.api.GithubApiService
import com.example.githublookup.data.models.Repo

class ReposPagingSource(
    private val api: GithubApiService,
    private val username: String
) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: 1
            val response = api.getUserRepos(username = username, page = page, perPage = 10)
            val repos = response.body().orEmpty()

            LoadResult.Page(
                data = repos,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (repos.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition
    }
}