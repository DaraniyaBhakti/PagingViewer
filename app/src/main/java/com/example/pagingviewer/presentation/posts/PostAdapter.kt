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
        val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        // not need to bind values here for text if we are using dta binding
//        fun bind(postItem: Post) {
//            binding.apply {
//                tvTitle.text = postItem.title
//                tvBody.text = postItem.body
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postItem: Post? = getItem(position)

        //used to bind data when not using data binding
//        postItem?.let { holder.bind(postItem) }

        //set data using two data binding
        postItem?.let {
            holder.binding.post = it
        }
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
