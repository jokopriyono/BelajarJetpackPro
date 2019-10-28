package com.jo.belajarjetpackpro.ui.bookmark

import androidx.lifecycle.ViewModel
import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.data.source.AcademyRepository
import com.jo.belajarjetpackpro.utils.DataDummy

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks(): ArrayList<CourseEntity> {
        return DataDummy.generateDummyCourses()
    }
}