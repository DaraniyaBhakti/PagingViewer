package com.example.pagingviewer.data.impl

import com.example.pagingviewer.data.paging.PostPagingSource
import com.example.pagingviewer.data.remote.ApiService
import com.example.pagingviewer.data.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PostRepository {
//    override suspend fun getPosts(): Flow<PagingData<Post>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 10,
//                prefetchDistance = 2,
//                enablePlaceholders = false
//            ),
//            pagingSourceFactory = {PostPagingSource(apiService)}
//        ).flow
//    }

    override fun getPostPagingSource(): PostPagingSource {
        return PostPagingSource(apiService)
    }
}