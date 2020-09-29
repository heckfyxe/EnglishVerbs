package com.heckfyxe.englishverbs.models

import androidx.annotation.StringRes
import java.util.*

data class Chapter(
    val id: String = UUID.randomUUID().toString(),
    @StringRes val name: Int,
    val assetName: String
)