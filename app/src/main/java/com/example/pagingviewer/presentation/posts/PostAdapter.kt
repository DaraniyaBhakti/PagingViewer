package com.example.pagingviewer.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingviewer.databinding.ItemPostBinding
import com.example.pagingviewer.models.Post

class PostAdapter : PagingDataAdapter<Post, PostAdapter.PostViewHolder>(PostItemDiffCallback()) {
    inner class PostViewHolder(
        private val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(postItem: Post) {
            binding.apply {
                tvTitle.text = postItem.title
                tvBody.text = postItem.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postItem: Post? = getItem(position)
        postItem?.let { holder.bind(postItem) }
    }
}

class PostItemDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(
        oldItem: Post, newItem: Post
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Post, newItem: Post
    ): Boolean {
        return oldItem == newItem
    }
}
