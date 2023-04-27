package com.alancamargo.hearthstone.core.design.tools

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

internal class ToastHelperImpl @Inject constructor(
    private val context: Context
) : ToastHelper {

    override fun showToast(textRes: Int) {
        Toast.makeText(context, textRes, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
