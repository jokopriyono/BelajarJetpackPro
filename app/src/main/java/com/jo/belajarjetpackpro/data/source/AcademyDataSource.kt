package com.jo.belajarjetpackpro.data.source

import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.data.ModuleEntity

interface AcademyDataSource {

    fun getAllCourses(): List<CourseEntity>

    fun getCourseWithModules(courseId: String): CourseEntity?

    fun getAllModulesByCourse(courseId: String): List<ModuleEntity>

    fun getBookmarkedCourses(): List<CourseEntity>

    fun getContent(courseId: String, moduleId: String): ModuleEntity?
}