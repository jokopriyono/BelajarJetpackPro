package com.jo.belajarjetpackpro.ui.bookmark


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.data.CourseEntity
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
            bookmarkViewModel = ViewModelProviders.of(this).get(BookmarkViewModel::class.java)

            bookmarkAdapter = BookmarkAdapter(it, this)
            bookmarkAdapter.courses = bookmarkViewModel.getBookmarks()

            rv_bookmark.layoutManager = LinearLayoutManager(it)
            rv_bookmark.setHasFixedSize(true)
            rv_bookmark.adapter = bookmarkAdapter
        }
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
