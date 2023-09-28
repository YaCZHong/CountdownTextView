package com.xh.lib.util

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

fun getColor(context: Context, @ColorRes resId: Int): Int {
    return ContextCompat.getColor(context, resId)
}

fun getString(context: Context, @StringRes resId: Int): String {
    return context.getString(resId)
}