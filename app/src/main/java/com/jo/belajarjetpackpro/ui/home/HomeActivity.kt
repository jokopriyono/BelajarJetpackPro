package com.jo.belajarjetpackpro.ui.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.jo.belajarjetpackpro.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }


    fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> {
                txt.setText(R.string.home)
                return true
            }
            R.id.action_bookmark -> {
                txt.setText(R.string.bookmark)
                return true
            }
        }
        return false
    }
}
