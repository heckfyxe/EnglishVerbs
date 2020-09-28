package com.heckfyxe.englishverbs.ui.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("adapter")
fun <T : RecyclerView.ViewHolder> adapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<T>?
) {
    recyclerView.adapter = adapter
}