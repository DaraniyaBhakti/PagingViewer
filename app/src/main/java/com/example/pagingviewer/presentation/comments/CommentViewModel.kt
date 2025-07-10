package com.example.pagingviewer.presentation.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pagingviewer.data.repository.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val repository: CommentRepository
) : ViewModel() {

//    private val commentList = MutableLiveData<List<Comment>>()
//    val comment : LiveData<List<Comment>> = commentList
//    fun fetchCommentData(){
//        Log.d("PAGER","comments api call")
//        viewModelScope.launch {
//            try {
//                val result = repository.getComments()
//                commentList.value = result
//            }catch (e: Exception){
//                Log.e("CommentViewModel", "Error: ${e.message}")
//            }
//        }
//    }

    val commentPagingFlow = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 2),
        pagingSourceFactory = { repository.getCommentPagingSource() }
    ).flow.cachedIn(viewModelScope)
}