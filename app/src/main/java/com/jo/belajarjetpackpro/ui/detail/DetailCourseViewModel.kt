package com.jo.belajarjetpackpro.ui.detail

import androidx.lifecycle.ViewModel
import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.data.ModuleEntity
import com.jo.belajarjetpackpro.data.source.AcademyRepository

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    var courseId: String = ""

    fun getCourse(): CourseEntity? = academyRepository.getCourseWithModules(courseId)

    fun getModules(): List<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)
}