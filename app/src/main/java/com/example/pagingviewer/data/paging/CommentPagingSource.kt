package com.example.pagingviewer.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingviewer.data.remote.ApiService
import com.example.pagingviewer.models.Comment

class CommentPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Comment>() {
    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getComments(currentPage, 10)
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