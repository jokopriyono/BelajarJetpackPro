package com.jo.belajarjetpackpro.ui.academy

import androidx.lifecycle.ViewModel
import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.data.source.AcademyRepository

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): List<CourseEntity> = academyRepository.getAllCourses()
}