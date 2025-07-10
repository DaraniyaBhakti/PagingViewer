package com.example.pagingviewer.presentation.albums

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagingviewer.databinding.FragmentAlbumsTabBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumsTabFragment : Fragment() {
    private lateinit var binding: FragmentAlbumsTabBinding

    private lateinit var adapter: AlbumAdapter
    private val viewModel: AlbumsViewModel by viewModels()
    private var hasLoadedOnce = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumsTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = AlbumAdapter()
        binding.rvAlbum.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAlbum.adapter = adapter
//        viewModel.fetchAlbumsData()

//        viewModel.albums.observe(viewLifecycleOwner){albums->
//            adapter.submitList(albums)
//        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }

    }

    override fun onResume() {
        super.onResume()
        if (!hasLoadedOnce) {
            loadAlbumsWhenTabSelected()
            hasLoadedOnce = true
        }
    }

    fun loadAlbumsWhenTabSelected() {
        lifecycleScope.launch {
            viewModel.albumFlow.collectLatest {
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