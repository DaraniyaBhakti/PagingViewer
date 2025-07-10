package com.example.pagingviewer.presentation.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.example.pagingviewer.presentation.albums.AlbumsTabFragment
import com.example.pagingviewer.presentation.comments.CommentsTabFragment
import com.example.pagingviewer.presentation.posts.PostsTabFragment

class ViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> PostsTabFragment()
            1 -> CommentsTabFragment()
            2 -> AlbumsTabFragment()
            else -> throw IllegalArgumentException("Invalid tab position: $position")
        }
    }

}