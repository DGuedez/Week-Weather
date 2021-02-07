package com.example.weekweather.presentation.ui.forecastweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.weekweather.R
import com.example.weekweather.databinding.ForecastDayElementBinding
import com.example.weekweather.domain.entity.ForeCastResponse
import javax.inject.Inject

class ForecastListAdapter @Inject constructor() :
    RecyclerView.Adapter<ForecastListAdapter.ForecastListViewHolder>() {

    var items: MutableList<ForeCastResponse.Daily> = mutableListOf()
    var actionListener: ActionListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastListViewHolder {
        val binding = ForecastDayElementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ForecastListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastListViewHolder, position: Int) {
        val item = items[position]


        holder.itemView.context.run {
            holder.dayElementDate?.text = item.dt
            holder.dayElementWeatherDescription?.text =
                getString(R.string.weather_description, item.weather?.get(0)?.description)
            holder.dayElementMax?.text = getString(R.string.max_Temperature, item.temp?.max)
            holder.dayElementMin?.text = getString(R.string.min_Temperature, item.temp?.min)
            holder.dayElementContainer.setOnClickListener {
                actionListener?.onDayClick(item)
            }
        }
    }


    override fun getItemCount(): Int = items.size

    inner class ForecastListViewHolder(binding: ForecastDayElementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var dayElementDate: TextView? = binding.dayElementDate
        var dayElementWeatherDescription: TextView? = binding.dayElementWeatherDescription
        var dayElementMax: TextView? = binding.dayElementMax
        var dayElementMin: TextView? = binding.dayElementMin
        var dayElementContainer: ConstraintLayout = binding.dayElementContainer
    }

    interface ActionListener {
        fun onDayClick(selectedDay: ForeCastResponse.Daily)
    }
}

