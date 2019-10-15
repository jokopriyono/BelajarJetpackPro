package com.jo.belajarjetpackpro.ui.reader

interface CourseReaderCallback {
    fun moveTo(position: Int, moduleId: String)
}