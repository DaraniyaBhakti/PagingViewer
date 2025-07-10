package com.example.pagingviewer.presentation.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagingviewer.databinding.FragmentCommentsTabBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CommentsTabFragment : Fragment() {

    private lateinit var binding: FragmentCommentsTabBinding

    private lateinit var adapter: CommentAdapter
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

        adapter = CommentAdapter()
        binding.rvComment.layoutManager = LinearLayoutManager(requireContext())
        binding.rvComment.adapter = adapter

//        viewModel.fetchCommentData()
//        viewModel.comment.observe(viewLifecycleOwner){commentList ->
//            adapter.submitList(commentList)
//        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }

    }

    override fun onResume() {
        super.onResume()
        if (!hasLoadedOnce) {
            loadCommentsWhenTabSelected()
            hasLoadedOnce = true
        }
    }

    private fun loadCommentsWhenTabSelected() {
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