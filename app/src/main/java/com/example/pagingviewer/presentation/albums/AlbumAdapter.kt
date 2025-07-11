package com.example.pagingviewer.presentation.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingviewer.R
import com.example.pagingviewer.databinding.ItemAlbumBinding
import com.example.pagingviewer.models.Albums

class AlbumAdapter :
//    ListAdapter
    PagingDataAdapter<Albums, AlbumAdapter.AlbumViewHolder>(AlbumItemDiffCallback()) {
    inner class AlbumViewHolder(
        private val binding: ItemAlbumBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(albumItem: Albums) {
            binding.apply {
                tvAlbumTitle.text = albumItem.title
                tvAlbumUser.text = binding.root.context.getString(R.string.uploaded_by_user, albumItem.userId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAlbumBinding.inflate(inflater, parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val albumItem: Albums? = getItem(position)
        albumItem?.let { holder.bind(albumItem) }
    }
}

class AlbumItemDiffCallback : DiffUtil.ItemCallback<Albums>() {
    override fun areItemsTheSame(
        oldItem: Albums, newItem: Albums
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Albums, newItem: Albums
    ): Boolean {
        return oldItem == newItem
    }
}
