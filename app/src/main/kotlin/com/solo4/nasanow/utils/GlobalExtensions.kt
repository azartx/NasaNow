package com.solo4.nasanow.utils

import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

// some extensions
fun Fragment.showToast(
    message: String,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun Fragment.showToast(
    @StringRes message: Int,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun Fragment.showSnack(
    duration: Int = Snackbar.LENGTH_SHORT,
    message: String,
    view: View = this.requireView(),
    bgColor: Int? = null
) {
    Snackbar.make(requireContext(), view, message, duration).apply {
        bgColor?.let { view.setBackgroundColor(it) }
    }.show()
}
