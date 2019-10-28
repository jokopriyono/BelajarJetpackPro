package com.jo.belajarjetpackpro.ui.reader.content


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.data.ModuleEntity
import com.jo.belajarjetpackpro.ui.reader.CourseReaderViewModel
import com.jo.belajarjetpackpro.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_content.*

class ModuleContentFragment : Fragment() {
    companion object {
        val TAG = ModuleContentFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(): ModuleContentFragment {
            val moduleContentFragment = ModuleContentFragment()
            val args = Bundle()
            moduleContentFragment.arguments = args
            return moduleContentFragment
        }
    }

    private lateinit var viewModel: CourseReaderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            viewModel = obtainViewModel(it)
            val module = viewModel.getSelectedModule()

            populateWebView(module)
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): CourseReaderViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(CourseReaderViewModel::class.java)
    }

    private fun populateWebView(entity: ModuleEntity?) {
        web_view.loadData(entity?.contentEntity?.mContent, "text/html", "UTF-8")
    }
}
