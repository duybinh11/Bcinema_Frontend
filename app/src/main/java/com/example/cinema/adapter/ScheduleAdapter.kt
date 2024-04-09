package com.example.cinema.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.viewmodel.HomeViewModel
import java.time.LocalDate



class ScheduleAdapter(val viewModel: HomeViewModel) : RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder>() {
    private var selectedPosition = 0

    class ScheduleHolder(view: View) : RecyclerView.ViewHolder(view) {
        val day: TextView = view.findViewById(R.id.day)
        val time: TextView = view.findViewById(R.id.time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleHolder {
        return ScheduleHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_schedule, parent, false))
    }

    override fun getItemCount(): Int = 7

    override fun onBindViewHolder(holder: ScheduleHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val split = holder.time.text.toString().split('/')
            val time = "2024/${split[1]}/${split[0]}"
            setSelectedPosition(position)
            notifyDataSetChanged()
            Log.d("apiT","$time")
            viewModel.getMovieShowTime(time)
        }

        // Hiển thị thứ và thời gian
        val thu = position + 2
        val dayText = if (thu < 8) "Thứ $thu" else "Chủ nhật"
        holder.day.text = dayText
        val nowDay = LocalDate.now().plusDays(position.toLong())  // Tăng ngày dựa trên vị trí
        val timeText = "${nowDay.dayOfMonth}/${nowDay.monthValue}"
        holder.time.text = timeText

        // Chỉ định màu chữ dựa trên vị trí
        if (position == selectedPosition) {
            holder.day.setTextColor(Color.YELLOW)
            holder.time.setTextColor(Color.YELLOW)
        } else {
            holder.day.setTextColor(Color.WHITE)  // Đặt màu chữ mặc định
            holder.time.setTextColor(Color.WHITE)
        }

    }

    fun setSelectedPosition(position: Int) {
        selectedPosition = position
    }
}