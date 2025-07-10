package com.example.pagingviewer.presentation.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pagingviewer.data.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val repository: AlbumRepository
) : ViewModel() {

//    private val albumList = MutableLiveData<List<Albums>>()
//    val albums: LiveData<List<Albums>> = albumList
//    fun fetchAlbumsData() {
//        Log.d("PAGER","album api call")
//        viewModelScope.launch {
//            try {
//                val result = repository.getAlbums()
//                albumList.value = result
//
//
//            } catch (e: Exception) {
//                Log.e("AlbumViewModel", "Error: ${e.message}")
//            }
//        }
//    }

    val albumFlow = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 2),
        pagingSourceFactory = { repository.getAlbumPagingSource() }
    ).flow.cachedIn(viewModelScope)
}