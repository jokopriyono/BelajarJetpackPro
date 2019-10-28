package com.jo.belajarjetpackpro.ui.reader.list


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.data.ModuleEntity
import com.jo.belajarjetpackpro.ui.reader.CourseReaderActivity
import com.jo.belajarjetpackpro.ui.reader.CourseReaderCallback
import com.jo.belajarjetpackpro.ui.reader.CourseReaderViewModel
import com.jo.belajarjetpackpro.utils.DataDummy
import com.jo.belajarjetpackpro.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_list.*

class ModuleListFragment : Fragment(), MyAdapterClickListener {
    companion object {

        val TAG = ModuleListFragment::class.java.simpleName
        @JvmStatic
        fun newInstance(): ModuleListFragment {
            val moduleListFragment = ModuleListFragment()
            val args = Bundle()
            moduleListFragment.arguments = args
            return moduleListFragment
        }

    }

    private lateinit var moduleListAdapter: ModuleListAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback
    private lateinit var viewModel: CourseReaderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_module_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            viewModel = obtainViewModel(it)
            moduleListAdapter = ModuleListAdapter(this)
            populateRecyclerView(DataDummy.generateDummyModules("a14"))
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): CourseReaderViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(CourseReaderViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }

    private fun populateRecyclerView(generateDummyModules: ArrayList<ModuleEntity>) {
        progress_bar.visibility = GONE
        moduleListAdapter.modules = generateDummyModules

        rv_module.layoutManager = LinearLayoutManager(context)
        rv_module.setHasFixedSize(true)
        rv_module.adapter = moduleListAdapter
        val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
        rv_module.addItemDecoration(dividerItemDecoration)
    }

    override fun onItemClicked(position: Int, moduleId: String) {
        courseReaderCallback.moveTo(position, moduleId)
        viewModel.moduleId = moduleId
    }
}
