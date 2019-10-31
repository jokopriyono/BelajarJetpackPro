package com.jo.belajarjetpackpro.data.source

import androidx.lifecycle.LiveData
import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.data.ModuleEntity

interface AcademyDataSource {

    fun getAllCourses(): LiveData<List<CourseEntity>>

    fun getCourseWithModules(courseId: String): LiveData<CourseEntity?>

    fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>>

    fun getBookmarkedCourses(): LiveData<List<CourseEntity>>

    fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity?>
}