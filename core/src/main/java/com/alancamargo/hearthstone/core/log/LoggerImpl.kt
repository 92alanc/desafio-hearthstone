package com.alancamargo.hearthstone.core.log

import android.util.Log
import javax.inject.Inject

private const val TAG = "LOG_ALAN"

internal class LoggerImpl @Inject constructor() : Logger {

    override fun debug(message: String) {
        Log.d(TAG, message)
    }

    override fun error(exception: Throwable) {
        Log.e(TAG, exception.message, exception)
    }
}
