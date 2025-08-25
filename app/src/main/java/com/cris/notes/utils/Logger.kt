package com.cris.notes.utils

import android.util.Log

object Logger {
    private const val TAG = "BT_APP"
    fun d(msg: String) = Log.d(TAG, msg)
    fun e(msg: String, t: Throwable? = null) = Log.e(TAG, msg, t)
}
