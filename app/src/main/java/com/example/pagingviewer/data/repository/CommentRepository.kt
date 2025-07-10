package com.example.pagingviewer.data.repository

import androidx.paging.PagingData
import com.example.pagingviewer.data.paging.CommentPagingSource
import com.example.pagingviewer.data.paging.PostPagingSource
import com.example.pagingviewer.models.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
//    suspend fun getComments(): Flow<PagingData<Comment>>

    fun getCommentPagingSource(): CommentPagingSource
}