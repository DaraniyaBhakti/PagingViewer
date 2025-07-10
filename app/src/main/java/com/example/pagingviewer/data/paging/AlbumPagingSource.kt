package com.example.pagingviewer.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingviewer.data.remote.ApiService
import com.example.pagingviewer.models.Albums

class AlbumPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Albums>() {
    override fun getRefreshKey(state: PagingState<Int, Albums>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Albums> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getAlbums(currentPage, 10)
            val nextKey = if (response.isEmpty()) null else (currentPage +1)
            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 1) null else (currentPage - 1),
                nextKey = nextKey
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}