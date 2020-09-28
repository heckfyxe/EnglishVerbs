package com.heckfyxe.englishverbs.ui.home

import android.view.View
import com.heckfyxe.englishverbs.databinding.HomeButtonBinding
import com.heckfyxe.englishverbs.models.Chapter
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class ChapterViewHolder(view: View) : RecyclerViewHolder<Chapter>(view) {

    val binding: HomeButtonBinding = HomeButtonBinding.bind(view)

    fun bind(chapter: Chapter) {
        binding.let {
            it.chapter = chapter
            it.executePendingBindings()
        }
    }
}