package com.heckfyxe.englishverbs.utils

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel

fun AndroidViewModel.getString(@StringRes resId: Int) =
    getApplication<Application>().getString(resId)
