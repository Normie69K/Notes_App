package com.cris.notes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cris.bluetoothapp.R


class DeviceAdapter(
    private val devices: MutableList<DeviceItem>
) : RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_device, parent, false)
        return DeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = devices[position]
        holder.tvName.text = device.name ?: "Unknown"
        holder.tvAddress.text = device.address
        holder.tvRssi.text = "RSSI: ${device.rssi}"
    }

    override fun getItemCount(): Int = devices.size

    fun updateList(newDevices: List<DeviceItem>) {
        devices.clear()
        devices.addAll(newDevices)
        notifyDataSetChanged()
    }

    class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
        val tvRssi: TextView = itemView.findViewById(R.id.tvRssi)
    }
}
