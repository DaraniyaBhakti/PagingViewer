package com.example.pagingviewer.presentation.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.pagingviewer.databinding.FragmentCommentsTabBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CommentsTabFragment : Fragment() {

    private lateinit var binding: FragmentCommentsTabBinding

    private val adapter: CommentAdapter by lazy {
        CommentAdapter()
    }
    private val viewModel: CommentViewModel by viewModels()
    private var hasLoadedOnce = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentsTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.adapter = adapter

//        viewModel.fetchCommentData()
//        viewModel.comment.observe(viewLifecycleOwner){commentList ->
//            adapter.submitList(commentList)
//        }

        binding.onRefresh = {
            adapter.refresh()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!hasLoadedOnce) {
            loadComments()
            hasLoadedOnce = true
        }
    }

    private fun loadComments() {
        lifecycleScope.launch {
            viewModel.commentPagingFlow.collectLatest {
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