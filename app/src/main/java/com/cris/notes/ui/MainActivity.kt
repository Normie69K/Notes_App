package com.cris.notes.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cris.bluetoothapp.R
//import com.cris.notes.R
import com.cris.notes.bluetooth.BluetoothScanner
import com.cris.notes.data.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var btnEnableBluetooth: Button
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var tvStatus: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DeviceAdapter

    private val devices = mutableListOf<DeviceItem>()
    private lateinit var scanner: BluetoothScanner
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnEnableBluetooth = findViewById(R.id.btnEnableBluetooth)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        tvStatus = findViewById(R.id.tvStatus)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = DeviceAdapter(devices)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        scanner = BluetoothScanner(this) { mac, rssi, ts ->
            val item = DeviceItem(name = null, address = mac, rssi = rssi)
            if (!devices.any { it.address == mac }) {
                runOnUiThread {
                    devices.add(item)
                    adapter.notifyItemInserted(devices.size - 1)
                }
            }
        }

        btnEnableBluetooth.setOnClickListener {
            // BluetoothController handles request if needed
            tvStatus.text = "Enable Bluetooth manually if disabled"
        }

        btnStart.setOnClickListener {
            if (checkPermission()) {
                scanner.start()
                tvStatus.text = "Scanning..."
                startSendingLoop()
            }
        }

        btnStop.setOnClickListener {
            scanner.stop()
            tvStatus.text = "Stopped"
        }
    }

    private fun checkPermission(): Boolean {
        val perms = arrayOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        val notGranted = perms.filter {
            ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        return if (notGranted.isEmpty()) {
            true
        } else {
            ActivityCompat.requestPermissions(this, notGranted.toTypedArray(), 1)
            false
        }
    }

    private fun startSendingLoop() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (devices.isNotEmpty()) {
                    lifecycleScope.launch {
                        try {
                            withContext(Dispatchers.IO) {
                                RetrofitClient.api.sendDevices(devices).execute()
                            }
                            runOnUiThread {
                                tvStatus.append("\nSent ${devices.size} devices to server")
                            }
                        } catch (e: Exception) {
                            runOnUiThread {
                                tvStatus.append("\nError: ${e.message}")
                            }
                        }
                    }
                }
                handler.postDelayed(this, 5000) // every 5s
            }
        }, 5000)
    }
}
