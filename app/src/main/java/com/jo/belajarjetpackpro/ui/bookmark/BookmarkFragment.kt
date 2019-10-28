package com.jo.belajarjetpackpro.ui.bookmark


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_bookmark.*

/**
 * A simple [Fragment] subclass.
 */
class BookmarkFragment : Fragment(), BookmarkFragmentCallback {
    companion object {
        @JvmStatic
        fun newInstance(): BookmarkFragment {
            val bookmarkFragment = BookmarkFragment()
            val args = Bundle()
            bookmarkFragment.arguments = args
            return bookmarkFragment
        }
    }

    private lateinit var bookmarkAdapter: BookmarkAdapter
    private lateinit var bookmarkViewModel: BookmarkViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            bookmarkViewModel = obtainViewModel(it)

            bookmarkAdapter = BookmarkAdapter(it, this)
            val courses = arrayListOf<CourseEntity>()
            courses.addAll(bookmarkViewModel.getBookmarks())
            bookmarkAdapter.courses = courses

            rv_bookmark.layoutManager = LinearLayoutManager(it)
            rv_bookmark.setHasFixedSize(true)
            rv_bookmark.adapter = bookmarkAdapter
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): BookmarkViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory)
            .get<BookmarkViewModel>(BookmarkViewModel::class.java)
    }

    override fun onShareClick(courseEntity: CourseEntity) {
        activity?.let {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                    .from(it)
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang.")
                    .setText(String.format("Segera daftar kelas %s di dicoding.com", courseEntity.title))
                    .startChooser()

        }
    }

}
