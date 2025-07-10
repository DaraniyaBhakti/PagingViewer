package com.example.pagingviewer.presentation.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pagingviewer.data.remote.ApiService
import com.example.pagingviewer.data.remote.RetrofitHelper
import com.example.pagingviewer.data.repository.PostRepository
import com.example.pagingviewer.databinding.FragmentPostsTabBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PostsTabFragment : Fragment() {

    private lateinit var binding: FragmentPostsTabBinding
    private lateinit var adapter: PostAdapter;
    private val viewModel: PostsViewModel by viewModels()
    private var hasLoadedOnce = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //not required while using dependency injection
//        val retrofit = RetrofitHelper.getInstance()
//        val apiService = retrofit.create(ApiService::class.java)
//        val repository = PostRepository(apiService)
//        viewModel = ViewModelProvider(this, PostViewModelFactory(repository))[PostsViewModel::class.java]

        adapter = PostAdapter()
        binding.rvPost.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPost.adapter = adapter

        //not have to call when using paging 3
//        viewModel.fetchPostData()
//        viewModel.post.observe(viewLifecycleOwner){postList ->
//            adapter.submitList(postList)
//        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }

    }

    override fun onResume() {
        super.onResume()
        if (!hasLoadedOnce) {
            loadPostWhenTabSelected()
            hasLoadedOnce = true
        }
    }

    private fun loadPostWhenTabSelected() {
        //need to call api using paging 3 with this
        lifecycleScope.launch {
            viewModel.postFlow.collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                // Only show swipeRefreshLoader on initial load
                binding.swipeRefreshLayout.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }
    }


}