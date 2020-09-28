package com.heckfyxe.englishverbs.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.heckfyxe.englishverbs.R
import com.heckfyxe.englishverbs.models.Chapter
import com.heckfyxe.englishverbs.utils.getString
import me.ibrahimyilmaz.kiel.adapterOf

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val chapters = listOf(
        Chapter(name = getString(R.string.chapter_1), assetName = "1"),
        Chapter(name = getString(R.string.chapter_2), assetName = "2"),
        Chapter(name = getString(R.string.chapter_3), assetName = "3"),
    )

    val adapter = liveData {
        val adapter = adapterOf<Chapter> {
            register(
                viewHolder = ::ChapterViewHolder,
                layoutResource = R.layout.home_button,
                onBindViewHolder = { viewHolder, _, chapter ->
                    viewHolder.bind(chapter)
                    viewHolder.binding.root.setOnClickListener {
                        onChapterClicked(chapter)
                    }
                }
            )
        }.apply {
            submitList(chapters)
        }
        emit(adapter)
    }

    private fun onChapterClicked(chapter: Chapter) {

    }
}