package com.example.pagingviewer.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingviewer.data.remote.ApiService
import com.example.pagingviewer.models.Post

class PostPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Post>() {
    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getPosts(currentPage, 10)
            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 1) null else (currentPage - 1),
                nextKey = if (response.isEmpty()) null else (currentPage +1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}