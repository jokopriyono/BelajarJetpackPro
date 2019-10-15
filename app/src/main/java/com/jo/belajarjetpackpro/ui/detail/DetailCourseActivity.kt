package com.jo.belajarjetpackpro.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.ui.reader.CourseReaderActivity
import com.jo.belajarjetpackpro.utils.DataDummy
import kotlinx.android.synthetic.main.activity_detail_course.*
import kotlinx.android.synthetic.main.content_detail_course.*

class DetailCourseActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    private lateinit var detailCourseAdapter: DetailCourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailCourseAdapter = DetailCourseAdapter()

        intent.extras?.let {
            val courseId = it.getString(EXTRA_COURSE)
            courseId?.let { id ->
                detailCourseAdapter.mModules = DataDummy.generateDummyModules(id)

                populateCourse(id)
            }
        }

        rv_module.isNestedScrollingEnabled = false
        rv_module.layoutManager = LinearLayoutManager(this)
        rv_module.setHasFixedSize(true)
        rv_module.adapter = detailCourseAdapter
        val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
        rv_module.addItemDecoration(dividerItemDecoration)

    }

    private fun populateCourse(courseId: String) {
        val courseEntity = DataDummy.getCourse(courseId)

        text_title.text = courseEntity?.title
        text_desc.text = courseEntity?.description
        text_date.text = String.format("Deadline %s", courseEntity?.deadline)

        Glide.with(this)
                .load(courseEntity?.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(image_poster)

        btn_start.setOnClickListener {
            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseId)
            startActivity(intent)
        }
    }

}
