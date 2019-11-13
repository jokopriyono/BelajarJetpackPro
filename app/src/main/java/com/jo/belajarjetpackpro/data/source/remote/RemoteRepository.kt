package com.jo.belajarjetpackpro.data.source.remote

import android.os.Handler
import com.jo.belajarjetpackpro.data.source.remote.response.ContentResponse
import com.jo.belajarjetpackpro.data.source.remote.response.CourseResponse
import com.jo.belajarjetpackpro.data.source.remote.response.ModuleResponse
import com.jo.belajarjetpackpro.utils.EspressoIdlingResource
import com.jo.belajarjetpackpro.utils.JsonHelper


class RemoteRepository(private val jsonHelper: JsonHelper) {
    companion object {
        private var remoteRepository: RemoteRepository? = null
        const val SERVICE_LATENCY_IN_MILLIS = 2000L

        fun getInstance(helper: JsonHelper): RemoteRepository {
            if (remoteRepository == null)
                remoteRepository = RemoteRepository(helper)
            return remoteRepository!!
        }
    }

    fun getAllCourses(callback: LoadCoursesCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed(
            {
                callback.onAllCoursesReceived(jsonHelper.loadCourses())
                EspressoIdlingResource.decrement()
            }, SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getModules(courseId: String, callback: LoadModulesCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed(
            {
                callback.onAllModulesReceived(jsonHelper.loadModule(courseId))
                EspressoIdlingResource.decrement()
            }, SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getContent(moduleId: String, callback: GetContentCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed(
            {
                callback.onContentReceived(jsonHelper.loadContent(moduleId))
                EspressoIdlingResource.decrement()
            }, SERVICE_LATENCY_IN_MILLIS
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