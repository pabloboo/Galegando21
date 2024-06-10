package com.galegando21.day18Mastermind

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.galegando21.R

class ColorAdapter(private val colors: List<Int>, private val colorMap: Map<Int, Char>, private val startDragListener: StartDragListener) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    interface StartDragListener {
        fun requestDrag(viewHolder: RecyclerView.ViewHolder)
    }

    inner class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorImage: ImageView = itemView.findViewById(R.id.color_image_mastermind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.color_card_mastermind, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.colorImage.setImageResource(colors[position])
        holder.colorImage.tag = colorMap[colors[position]]
        holder.colorImage.setOnLongClickListener {
            startDragListener.requestDrag(holder)
            true
        }
    }

    override fun getItemCount() = colors.size
}