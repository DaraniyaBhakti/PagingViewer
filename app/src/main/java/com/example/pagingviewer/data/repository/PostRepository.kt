package com.example.pagingviewer.data.repository

import androidx.paging.PagingData
import com.example.pagingviewer.data.paging.PostPagingSource
import com.example.pagingviewer.models.Post
import com.example.pagingviewer.data.remote.ApiService
import kotlinx.coroutines.flow.Flow

// Called by the ViewModel
// Hides API logic from ViewModel

//without using DI Hilt
//class PostRepository(private val apiService: ApiService) {
//
//    suspend fun getPosts() : List<Post> = apiService.getPosts()
//}

interface PostRepository{
//    suspend fun getPosts(): Flow<PagingData<Post>>

    //used while using paging 3
    fun getPostPagingSource(): PostPagingSource
}