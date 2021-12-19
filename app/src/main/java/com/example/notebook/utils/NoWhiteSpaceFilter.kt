package com.example.notebook.utils

import android.text.InputFilter
import android.text.Spanned

object NoWhiteSpaceFilter : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? = source?.filter { it != ' ' }
}