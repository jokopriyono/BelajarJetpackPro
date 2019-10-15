package com.jo.belajarjetpackpro.ui.bookmark

import com.jo.belajarjetpackpro.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(courseEntity: CourseEntity)
}
