package com.cris.notes.utils

object Constants {
    // Change to your server address. Keep trailing slash.
    const val BASE_URL = "http://192.168.1.10:5000/"
    // Batch window in ms for sending readings
    const val SEND_INTERVAL_MS = 2000L
    // Max items per batch
    const val MAX_BATCH = 200
}
