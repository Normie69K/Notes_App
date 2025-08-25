package com.cris.notes.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import com.cris.notes.utils.Logger

class BluetoothScanner(
    context: Context,
    private val onResult: (mac: String, rssi: Int, ts: Long) -> Unit
) {
    private val controller = BluetoothController(context)
    private var scanner: BluetoothLeScanner? = null
    private var callback: ScanCallback? = null

    @SuppressLint("MissingPermission")
    fun start() {
        if (!controller.canScan()) {
            Logger.e("Bluetooth disabled or adapter null")
            return
        }
        scanner = controller.adapter.bluetoothLeScanner
        if (scanner == null) {
            Logger.e("BluetoothLeScanner is null")
            return
        }
        callback = object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult) {
                val device = result.device ?: return
                val mac = device.address ?: return
                val rssi = result.rssi
                onResult(mac, rssi, System.currentTimeMillis())
            }
            override fun onBatchScanResults(results: MutableList<ScanResult>) {
                val ts = System.currentTimeMillis()
                for (r in results) {
                    val dev = r.device ?: continue
                    val mac = dev.address ?: continue
                    onResult(mac, r.rssi, ts)
                }
            }
        }
        scanner?.startScan(callback)
        Logger.d("Scan started")
    }

    @SuppressLint("MissingPermission")
    fun stop() {
        try {
            scanner?.let { sc ->
                callback?.let { sc.stopScan(it) }
            }
        } catch (t: Throwable) {
            Logger.e("stopScan error", t)
        } finally {
            callback = null
            scanner = null
            Logger.d("Scan stopped")
        }
    }
}
