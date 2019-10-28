package com.jo.belajarjetpackpro.data.source.remote

import com.jo.belajarjetpackpro.data.source.remote.response.ContentResponse
import com.jo.belajarjetpackpro.data.source.remote.response.CourseResponse
import com.jo.belajarjetpackpro.data.source.remote.response.ModuleResponse
import com.jo.belajarjetpackpro.utils.JsonHelper

class RemoteRepository(private val jsonHelper: JsonHelper) {
    companion object {
        private var remoteRepository: RemoteRepository? = null

        fun getInstance(helper: JsonHelper): RemoteRepository {
            if (remoteRepository == null)
                remoteRepository = RemoteRepository(helper)
            return remoteRepository!!
        }
    }

    fun getAllCourses(): List<CourseResponse> = jsonHelper.loadCourses()

    fun getModules(courseId: String): List<ModuleResponse> = jsonHelper.loadModule(courseId)

    fun getContent(moduleId: String): ContentResponse? = jsonHelper.loadContent(moduleId)
}