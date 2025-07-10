package com.example.pagingviewer.data.impl

import com.example.pagingviewer.data.paging.AlbumPagingSource
import com.example.pagingviewer.data.remote.ApiService
import com.example.pagingviewer.data.repository.AlbumRepository
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AlbumRepository {
//    override suspend fun getAlbums(): List<Albums> = apiService.getAlbums()

//    override suspend fun getAlbums(): Flow<PagingData<Albums>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 10,
//                enablePlaceholders = false
//            ),
//            pagingSourceFactory = { AlbumPagingSource(apiService) }
//        ).flow
//    }

    override fun getAlbumPagingSource(): AlbumPagingSource {
        return AlbumPagingSource(apiService)
    }
}