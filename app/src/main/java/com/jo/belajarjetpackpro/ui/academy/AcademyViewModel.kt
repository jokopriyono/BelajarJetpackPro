package com.jo.belajarjetpackpro.ui.academy

import androidx.lifecycle.ViewModel
import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.utils.DataDummy

class AcademyViewModel : ViewModel() {
    fun getCourses(): ArrayList<CourseEntity> {
        return DataDummy.generateDummyCourses()
    }
}