package com.jo.belajarjetpackpro.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.data.ModuleEntity

class DetailCourseAdapter : RecyclerView.Adapter<DetailCourseAdapter.ModuleViewHolder>() {

    var mModules = arrayListOf<ModuleEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_module_list, parent, false)
        return ModuleViewHolder(view)
    }

    override fun getItemCount(): Int = mModules.size

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        holder.textTitle.text = mModules[position].mTitle
    }

    class ModuleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.text_module_title)
    }

}