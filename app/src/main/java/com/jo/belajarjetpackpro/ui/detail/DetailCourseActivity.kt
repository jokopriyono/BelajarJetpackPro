package com.jo.belajarjetpackpro.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.ui.reader.CourseReaderActivity
import com.jo.belajarjetpackpro.utils.DataDummy
import com.jo.belajarjetpackpro.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_course.*
import kotlinx.android.synthetic.main.content_detail_course.*

class DetailCourseActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    private lateinit var detailCourseAdapter: DetailCourseAdapter
    private lateinit var viewModel: DetailCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)

        viewModel = obtainViewModel(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailCourseAdapter = DetailCourseAdapter()

        intent.extras?.let {
            val courseId = it.getString(EXTRA_COURSE)
            courseId?.let { id ->
                progress_bar.visibility = VISIBLE
                viewModel.courseId = id
                /*viewModel.courseId = id
                detailCourseAdapter.mModules = DataDummy.generateDummyModules(id)

                populateCourse(id)*/
            }
        }

        viewModel.getModules().observe(this, Observer {
            progress_bar.visibility = GONE
            detailCourseAdapter.mModules.clear()
            detailCourseAdapter.mModules.addAll(it)
            detailCourseAdapter.notifyDataSetChanged()
        })

        viewModel.getCourse().observe(this, Observer {
            it?.let {
                populateCourse(it.courseId)
            }
        })

        rv_module.isNestedScrollingEnabled = false
        rv_module.layoutManager = LinearLayoutManager(this)
        rv_module.setHasFixedSize(true)
        rv_module.adapter = detailCourseAdapter
        val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
        rv_module.addItemDecoration(dividerItemDecoration)

    }

    private fun obtainViewModel(activity: FragmentActivity): DetailCourseViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(DetailCourseViewModel::class.java)
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
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, viewModel.courseId)
            startActivity(intent)
        }
    }

}
