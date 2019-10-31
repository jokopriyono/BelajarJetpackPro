package com.jo.belajarjetpackpro.data.source.remote

import android.os.Handler
import com.jo.belajarjetpackpro.data.source.remote.response.ContentResponse
import com.jo.belajarjetpackpro.data.source.remote.response.CourseResponse
import com.jo.belajarjetpackpro.data.source.remote.response.ModuleResponse
import com.jo.belajarjetpackpro.utils.JsonHelper


class RemoteRepository(private val jsonHelper: JsonHelper) {
    companion object {
        private var remoteRepository: RemoteRepository? = null
        private const val SERVICE_LATENCY_IN_MILLIS = 2000L

        fun getInstance(helper: JsonHelper): RemoteRepository {
            if (remoteRepository == null)
                remoteRepository = RemoteRepository(helper)
            return remoteRepository!!
        }
    }

    fun getAllCourses(callback: LoadCoursesCallback) {
        val handler = Handler()
        handler.postDelayed(
            { callback.onAllCoursesReceived(jsonHelper.loadCourses()) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getModules(courseId: String, callback: LoadModulesCallback) {
        val handler = Handler()
        handler.postDelayed(
            { callback.onAllModulesReceived(jsonHelper.loadModule(courseId)) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getContent(moduleId: String, callback: GetContentCallback) {
        val handler = Handler()
        handler.postDelayed(
            { callback.onContentReceived(jsonHelper.loadContent(moduleId)) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    interface LoadCoursesCallback {
        fun onAllCoursesReceived(courseResponses: List<CourseResponse>)

        fun onDataNotAvailable()
    }

    interface LoadModulesCallback {
        fun onAllModulesReceived(moduleResponses: List<ModuleResponse>)

        fun onDataNotAvailable()
    }

    interface GetContentCallback {
        fun onContentReceived(contentResponse: ContentResponse?)

        fun onDataNotAvailable()
    }
}