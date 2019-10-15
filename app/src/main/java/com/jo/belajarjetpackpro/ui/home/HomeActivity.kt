package com.jo.belajarjetpackpro.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.ui.academy.AcademyFragment
import com.jo.belajarjetpackpro.ui.bookmark.BookmarkFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    companion object {
        const val SELECTED_MENU = "selected_menu"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState != null)
            savedInstanceState.getInt(SELECTED_MENU)
        else
            nav_view.selectedItemId = R.id.action_home
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SELECTED_MENU, nav_view.selectedItemId)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        val fragment = if (it.itemId == R.id.action_home) {
            AcademyFragment.newInstance()
        } else {
            BookmarkFragment.newInstance()
        }

        supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment)
                .commit()

        return@OnNavigationItemSelectedListener true
    }
}
