package com.jo.belajarjetpackpro.ui.reader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.ui.reader.content.ModuleContentFragment
import com.jo.belajarjetpackpro.ui.reader.list.ModuleListFragment
import com.jo.belajarjetpackpro.viewmodel.ViewModelFactory

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {
    companion object {
        const val EXTRA_COURSE_ID = "extra_course_id"
    }

    private lateinit var viewModel: CourseReaderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_reader)

        viewModel = obtainViewModel(this)

        intent.extras?.let {
            val courseId = it.getString(EXTRA_COURSE_ID)
            courseId?.let { id ->
                viewModel.courseId = id
                populateFragment()
            }
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): CourseReaderViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(CourseReaderViewModel::class.java)
    }

    private fun populateFragment() {
        val ft = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(ModuleListFragment.TAG)
        if (fragment == null) {
            fragment = ModuleListFragment.newInstance()
            ft.add(R.id.frame_container, fragment, ModuleListFragment.TAG)
            ft.addToBackStack(null)
        }
        ft.commit()
    }

    override fun moveTo(position: Int, moduleId: String) {
        val fragment = ModuleContentFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.frame_container, fragment, ModuleContentFragment.TAG)
                .addToBackStack(null)
                .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1)
            finish()
        else
            super.onBackPressed()
    }
}
