package com.jo.belajarjetpackpro.ui.academy

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.ui.detail.DetailCourseActivity

class AcademyAdapter(private val activity: Activity) :
        RecyclerView.Adapter<AcademyAdapter.AcademyViewHolder>() {
    var mCourses = arrayListOf<CourseEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcademyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_academy, parent, false)
        return AcademyViewHolder(view)
    }

    override fun getItemCount(): Int = mCourses.size

    override fun onBindViewHolder(holder: AcademyViewHolder, position: Int) {
        holder.tvTitle.text = mCourses[position].title
        holder.tvDescription.text = mCourses[position].description
        holder.tvDate.text = String.format("Deadline %s", mCourses[position].deadline)
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, DetailCourseActivity::class.java)
            intent.putExtra(DetailCourseActivity.EXTRA_COURSE, mCourses[position].courseId)
            activity.startActivity(intent)
        }

        Glide.with(holder.itemView.context)
                .load(mCourses[position].imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.imgPoster)
    }

    class AcademyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
        val tvDate: TextView = itemView.findViewById(R.id.tv_item_date)
        val imgPoster: ImageView = itemView.findViewById(R.id.img_poster)
    }

}