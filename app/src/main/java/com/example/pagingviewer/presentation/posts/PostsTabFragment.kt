package com.example.pagingviewer.presentation.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pagingviewer.databinding.FragmentPostsTabBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PostsTabFragment : Fragment() {

    private lateinit var binding: FragmentPostsTabBinding
    private val adapter: PostAdapter by lazy {
        PostAdapter()
    }
    private val viewModel: PostsViewModel by viewModels()
    private var isLoaded = false

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
        if (!isLoaded) {
            loadPosts()
            isLoaded = true
        }
    }

    private fun loadPosts() {
        //need to call api using paging 3 with this
        lifecycleScope.launch {
            viewModel.postFlow.collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                // only show swipeRefreshLoader while loading initially
                binding.swipeRefreshLayout.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }
    }
}