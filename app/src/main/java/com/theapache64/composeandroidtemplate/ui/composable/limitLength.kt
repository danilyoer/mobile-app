package com.theapache64.composeandroidtemplate.ui.composable

const val DEFAULT_MAX_LENGTH = 35

fun limitLength(
    input: String,
    maxLength: Int = DEFAULT_MAX_LENGTH
): String {
    return if (input.length <= maxLength) input else input.take(maxLength)
}
