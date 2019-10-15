package com.jo.belajarjetpackpro.ui.detail

import androidx.lifecycle.ViewModel
import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.data.ModuleEntity
import com.jo.belajarjetpackpro.utils.DataDummy

class DetailCourseViewModel : ViewModel() {
    private lateinit var mCourse: CourseEntity
    var courseId: String = ""

    fun getCourse(): CourseEntity {
        for (i in 0 until DataDummy.generateDummyCourses().size) {
            val courseEntity = DataDummy.generateDummyCourses()[i]
            if (courseEntity.courseId == courseId) {
                mCourse = courseEntity
            }
        }
        return mCourse
    }

    fun getModules(): List<ModuleEntity> = DataDummy.generateDummyModules(courseId)
}