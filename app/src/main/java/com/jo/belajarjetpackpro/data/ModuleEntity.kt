package com.jo.belajarjetpackpro.data

data class ModuleEntity(
        var contentEntity: ContentEntity? = null,
        val mModuleId: String,
        val mCourseId: String,
        val mTitle: String,
        val mPosition: Int,
        val mRead: Boolean = false
) {
    constructor(mModuleId: String, mCourseId: String, mTitle: String, mPosition: Int, mRead: Boolean) :
            this(null, mModuleId, mCourseId, mTitle, mPosition, mRead)
}