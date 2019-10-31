package com.jo.belajarjetpackpro.ui.academy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_academy.*

class AcademyFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(): AcademyFragment {
            val academyFragment = AcademyFragment()
            val args = Bundle()
            academyFragment.arguments = args
            return academyFragment
        }
    }

    private lateinit var academyAdapter: AcademyAdapter
    private lateinit var academyViewModel: AcademyViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            academyViewModel = obtainViewModel(it)

            academyAdapter = AcademyAdapter(it)

            academyViewModel.getCourses().observe(this, Observer { courses ->
                progress_bar.visibility = GONE
                academyAdapter.mCourses.clear()
                academyAdapter.mCourses.addAll(courses)
                academyAdapter.notifyDataSetChanged()
            })

            rv_academy.layoutManager = LinearLayoutManager(it)
            rv_academy.setHasFixedSize(true)
            rv_academy.adapter = academyAdapter
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): AcademyViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(AcademyViewModel::class.java)
    }
}
