package com.example.pagingviewer.data.repository

import androidx.paging.PagingData
import com.example.pagingviewer.data.paging.AlbumPagingSource
import com.example.pagingviewer.models.Albums
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
//    suspend fun getAlbums(): List<Albums>
//suspend fun getAlbums(): Flow<PagingData<Albums>>

    fun getAlbumPagingSource() : AlbumPagingSource
}