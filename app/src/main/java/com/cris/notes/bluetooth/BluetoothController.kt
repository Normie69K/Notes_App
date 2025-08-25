package com.cris.notes.bluetooth

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresPermission

class BluetoothController(private val context: Context) {
    private val manager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val adapter: BluetoothAdapter = manager.adapter

    fun isEnabled(): Boolean = adapter?.isEnabled == true

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun requestEnable(activity: Activity, requestCode: Int = 1001) {
        val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        activity.startActivityForResult(intent, requestCode)
    }

    fun canScan(): Boolean = adapter != null && isEnabled()
}
