package com.example.pagingviewer.data.remote

import com.example.pagingviewer.models.Albums
import com.example.pagingviewer.models.Comment
import com.example.pagingviewer.models.Post
import retrofit2.http.GET
import retrofit2.http.Query


// Defines how you talk to the backend

// ABSTRACTION
// hides retrofit logic       ??
interface ApiService {

    @GET("/posts")
    suspend fun getPosts(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): List<Post>

    @GET("/comments")
    suspend fun getComments(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): List<Comment>

    @GET("/albums")
    suspend fun getAlbums(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): List<Albums>
}