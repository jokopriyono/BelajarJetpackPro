package com.jo.belajarjetpackpro.ui.bookmark

import androidx.lifecycle.ViewModel
import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.data.source.AcademyRepository

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks(): List<CourseEntity> = academyRepository.getBookmarkedCourses()
}