package com.jo.belajarjetpackpro.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jo.belajarjetpackpro.data.ModuleEntity
import com.jo.belajarjetpackpro.data.source.AcademyRepository

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    var courseId = ""
    var moduleId = ""

    fun getModules(): LiveData<List<ModuleEntity>> =
        academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): LiveData<ModuleEntity?> =
        academyRepository.getContent(courseId, moduleId)
}