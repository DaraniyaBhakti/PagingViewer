package com.example.pagingviewer.data.impl

import com.example.pagingviewer.data.paging.CommentPagingSource
import com.example.pagingviewer.data.remote.ApiService
import com.example.pagingviewer.data.repository.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CommentRepository {
//    override suspend fun getComments(): Flow<PagingData<Comment>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 10,
//                prefetchDistance = 2,
//                enablePlaceholders = false
//            ),
//            pagingSourceFactory = {CommentPagingSource(apiService)}
//        ).flow
//    }

    override fun getCommentPagingSource(): CommentPagingSource {
        return CommentPagingSource(apiService)
    }
}