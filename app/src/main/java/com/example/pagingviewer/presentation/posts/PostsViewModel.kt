package com.example.pagingviewer.presentation.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pagingviewer.models.Post
import com.example.pagingviewer.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


// INHERITANCE
@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    // ENCAPSULATION -  read-only post-list is accessible outside class
//    private val postListData = MutableLiveData<List<Post>>()
//    val post : LiveData<List<Post>> = postListData
//
//    fun fetchPostData(){
//        Log.d("PAGER","post api call")
//        viewModelScope.launch {
//            try {
////                Calls repository to fetch posts
//                val result = repository.getPosts()
//                postListData.value = result
//
//            } catch (e: Exception) {
//                Log.e("PostsViewModel", "Error: ${e.message}")
//            }
//        }
//    }


    //when using paging3 we have to use below code
    val postFlow = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 5),
        pagingSourceFactory = { repository.getPostPagingSource() }
    ).flow.cachedIn(viewModelScope)

}