package com.solo4.nasanow.utils

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

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
