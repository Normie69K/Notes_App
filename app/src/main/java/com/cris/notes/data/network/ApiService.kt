// network/ApiService.kt
package com.cris.notes.data.network

import com.cris.notes.ui.DeviceItem
//import com.cris.bluetoothapp.ui.DeviceItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("devices")
    fun sendDevices(@Body devices: List<DeviceItem>): Call<Void>
}
