package com.jo.belajarjetpackpro.data

data class ModuleEntity(
    val mModuleId: String,
    val mCourseId: String,
    val mTitle: String,
    val mPosition: Int,
    val mRead: Boolean = false
)