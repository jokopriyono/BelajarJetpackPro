package com.jo.belajarjetpackpro.ui.academy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.utils.DataDummy
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            academyAdapter = AcademyAdapter(it)
            academyAdapter.mCourses = DataDummy.generateDummyCourses()

            rv_academy.layoutManager = LinearLayoutManager(it)
            rv_academy.setHasFixedSize(true)
            rv_academy.adapter = academyAdapter
        }
    }
}
