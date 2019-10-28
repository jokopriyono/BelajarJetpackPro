package com.jo.belajarjetpackpro.ui.reader

import androidx.lifecycle.ViewModel
import com.jo.belajarjetpackpro.data.ModuleEntity
import com.jo.belajarjetpackpro.data.source.AcademyRepository

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    var courseId = ""
    var moduleId = ""

    private fun getModules(): List<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): ModuleEntity? = academyRepository.getContent(courseId, moduleId)
}