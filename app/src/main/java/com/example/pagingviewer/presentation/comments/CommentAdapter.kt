package com.example.pagingviewer.presentation.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingviewer.databinding.ItemCommentBinding
import com.example.pagingviewer.models.Comment

class CommentAdapter: PagingDataAdapter<Comment,CommentAdapter.CommentViewHolder>(CommentItemDiffCallback()) {
    inner class CommentViewHolder(
        private val binding: ItemCommentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(commentItem: Comment) {
            binding.apply {
                tvCommentName.text = commentItem.name
                tvCommentEmail.text = commentItem.email
                tvCommentBody.text = commentItem.body
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCommentBinding.inflate(inflater,parent,false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val commentItem : Comment? = getItem(position)
        commentItem?.let { holder.bind(commentItem) }
    }
}

class CommentItemDiffCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(
        oldItem: Comment, newItem: Comment
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Comment, newItem: Comment
    ): Boolean {
        return oldItem == newItem
    }
}
