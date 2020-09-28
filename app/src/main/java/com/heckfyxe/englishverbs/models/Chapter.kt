package com.heckfyxe.englishverbs.models

import java.util.*

data class Chapter(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val assetName: String
)