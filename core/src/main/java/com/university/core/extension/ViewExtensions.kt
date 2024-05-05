package com.university.core.extension

import android.view.View


//region View
fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone(isAnimated: Boolean = false) {
    if (isAnimated) clearAnimation()
    visibility = View.GONE
}
//endregion