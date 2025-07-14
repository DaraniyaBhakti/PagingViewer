package com.example.pagingviewer.presentation.utils

import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
@BindingAdapter("formattedText", "formatArg", requireAll = true)
fun TextView.setFormattedText(@StringRes resId: Int, arg: Int) {
    text = context.getString(resId, arg)
}
@BindingAdapter("setRecyclerAdapter")
fun RecyclerView.setRecyclerAdapter(adapter: RecyclerView.Adapter<*>?){
    this.adapter = adapter
}

@BindingAdapter("onRefreshListener")
fun setOnRefreshListener(
    swipeRefreshLayout: SwipeRefreshLayout,
    listener: () -> Unit
) {
    swipeRefreshLayout.setOnRefreshListener {
        listener()
    }
}

