package com.cris.notes.data.model

data class BluetoothReading(
    val deviceId: String,
    val rssi: Int,
    val timestamp: Long
)
