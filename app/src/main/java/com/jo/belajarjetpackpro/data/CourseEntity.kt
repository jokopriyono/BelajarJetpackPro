package com.jo.belajarjetpackpro.data

data class CourseEntity(
    val courseId: String,
    val title: String,
    val description: String,
    val deadline: String,
    val bookmarked: Boolean = false,
    val imagePath: String
)