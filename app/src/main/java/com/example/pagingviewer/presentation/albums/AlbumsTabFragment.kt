package com.example.pagingviewer.presentation.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.pagingviewer.databinding.FragmentAlbumsTabBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumsTabFragment : Fragment() {
    private lateinit var binding: FragmentAlbumsTabBinding

    private val adapter: AlbumAdapter by lazy {
        AlbumAdapter()
    }
    private val viewModel: AlbumsViewModel by viewModels()
    private var isLoaded = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumsTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvAlbum.adapter = adapter

        // Not required while using paging
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
        if (!isLoaded) {
            loadAlbums()
            isLoaded = true
        }
    }

    private fun loadAlbums() {
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