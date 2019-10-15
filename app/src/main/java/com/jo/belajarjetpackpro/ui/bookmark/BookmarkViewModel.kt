package com.jo.belajarjetpackpro.ui.bookmark

import androidx.lifecycle.ViewModel
import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.utils.DataDummy

class BookmarkViewModel : ViewModel() {
    fun getBookmarks(): ArrayList<CourseEntity> {
        return DataDummy.generateDummyCourses()
    }
}