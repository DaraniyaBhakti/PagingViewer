package com.example.pagingviewer.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pagingviewer.data.repository.PostRepository

// View factory is used when we want to pass anything(repository) inside viewmodel
// we can pass repository instance inside viewmodel via view modal factory

// But while using dependency injection it is not required
class PostViewModelFactory(private val repository: PostRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PostsViewModel::class.java)){
            return PostsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}