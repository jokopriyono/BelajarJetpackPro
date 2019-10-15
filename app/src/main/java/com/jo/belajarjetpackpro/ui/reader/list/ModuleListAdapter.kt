package com.jo.belajarjetpackpro.ui.reader.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.data.ModuleEntity

class ModuleListAdapter(private val listener: MyAdapterClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var modules = arrayListOf<ModuleEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ModuleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_module_list_custom, parent, false))
    }

    override fun getItemCount(): Int = modules.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val moduleEntity = modules[position]
        val moduleViewHolder = holder as ModuleViewHolder
        moduleViewHolder.textTitle.text = moduleEntity.mTitle
        moduleViewHolder.itemView.setOnClickListener {
            listener.onItemClicked(moduleViewHolder.adapterPosition, modules[moduleViewHolder.adapterPosition].mModuleId)
        }
    }

    class ModuleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.text_module_title)
        val textLastSeen: TextView = itemView.findViewById(R.id.text_last_seen)
    }
}

interface MyAdapterClickListener {
    fun onItemClicked(position: Int, moduleId: String)
}