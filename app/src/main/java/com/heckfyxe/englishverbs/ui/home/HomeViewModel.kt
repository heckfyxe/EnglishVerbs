package com.heckfyxe.englishverbs.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.heckfyxe.englishverbs.R
import com.heckfyxe.englishverbs.models.Chapter
import me.ibrahimyilmaz.kiel.adapterOf

class HomeViewModel : ViewModel() {

    private val chapters = listOf(
        Chapter(name = R.string.chapter_1, assetName = "1"),
        Chapter(name = R.string.chapter_2, assetName = "2"),
        Chapter(name = R.string.chapter_3, assetName = "3"),
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

    private val _navigateToQuizFragment = MutableLiveData<Chapter?>()
    val navigateToQuizFragment: LiveData<Chapter?> = _navigateToQuizFragment

    private fun onChapterClicked(chapter: Chapter) {
        _navigateToQuizFragment.value = chapter
    }

    fun onNavigatedToQuizFragment() {
        _navigateToQuizFragment.value = null
    }
}