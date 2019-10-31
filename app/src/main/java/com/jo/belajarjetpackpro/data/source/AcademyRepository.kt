package com.jo.belajarjetpackpro.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jo.belajarjetpackpro.data.ContentEntity
import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.data.ModuleEntity
import com.jo.belajarjetpackpro.data.source.remote.RemoteRepository
import com.jo.belajarjetpackpro.data.source.remote.response.ContentResponse
import com.jo.belajarjetpackpro.data.source.remote.response.CourseResponse
import com.jo.belajarjetpackpro.data.source.remote.response.ModuleResponse

class AcademyRepository(private val remoteRepository: RemoteRepository) : AcademyDataSource {

    companion object {
        @Volatile
        private var INSTANCE: AcademyRepository? = null

        fun getInstance(remoteRepository: RemoteRepository): AcademyRepository {
            if (INSTANCE == null) {
                synchronized(AcademyRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = AcademyRepository(remoteRepository)
                    }
                }
            }
            return INSTANCE!!
        }
    }

    override fun getAllCourses(): LiveData<List<CourseEntity>> {
        val courseResults = MutableLiveData<List<CourseEntity>>()

        remoteRepository.getAllCourses(object : RemoteRepository.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                val courseList = arrayListOf<CourseEntity>()
                for (element in courseResponses) {
                    val (id, title, description, date, imagePath) = element
                    val course = CourseEntity(
                        id,
                        title,
                        description,
                        date,
                        false,
                        imagePath
                    )

                    courseList.add(course)
                }
                courseResults.postValue(courseList)
            }

            override fun onDataNotAvailable() {
            }

        })

        return courseResults
    }

    override fun getCourseWithModules(courseId: String): LiveData<CourseEntity?> {
        val courseResult = MutableLiveData<CourseEntity>()

        remoteRepository.getAllCourses(object : RemoteRepository.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                for (element in courseResponses) {
                    val (id, title, description, date, imagePath) = element
                    if (id == courseId) {
                        val course = CourseEntity(
                            id,
                            title,
                            description,
                            date,
                            false,
                            imagePath
                        )
                        courseResult.postValue(course)
                    }
                }
            }

            override fun onDataNotAvailable() {
            }

        })

        return courseResult
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>> {
        val moduleResults = MutableLiveData<List<ModuleEntity>>()

        remoteRepository.getModules(courseId, object : RemoteRepository.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                val moduleList = arrayListOf<ModuleEntity>()
                for (element in moduleResponses) {
                    val (moduleId, courseId1, title, position) = element
                    val course = ModuleEntity(
                        moduleId,
                        courseId1,
                        title,
                        position,
                        false
                    )

                    moduleList.add(course)
                }
                moduleResults.postValue(moduleList)
            }

            override fun onDataNotAvailable() {
            }
        })

        return moduleResults
    }

    override fun getBookmarkedCourses(): LiveData<List<CourseEntity>> {
        val courseResults = MutableLiveData<List<CourseEntity>>()

        remoteRepository.getAllCourses(object : RemoteRepository.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                val courseList = arrayListOf<CourseEntity>()
                for (element in courseResponses) {
                    val (id, title, description, date, imagePath) = element
                    val course = CourseEntity(
                        id,
                        title,
                        description,
                        date,
                        false,
                        imagePath
                    )
                    courseList.add(course)
                }
                courseResults.postValue(courseList)
            }

            override fun onDataNotAvailable() {
            }
        })

        return courseResults
    }

    override fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity?> {
        val moduleResult = MutableLiveData<ModuleEntity>()

        remoteRepository.getModules(courseId, object : RemoteRepository.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                val module: ModuleEntity
                for (element in moduleResponses) {
                    val (id, courseId1, title, position) = element

                    if (id == moduleId) {
                        module = ModuleEntity(id, courseId1, title, position, false)

                        remoteRepository.getContent(
                            moduleId,
                            object : RemoteRepository.GetContentCallback {
                                override fun onContentReceived(contentResponse: ContentResponse?) {
                                    module.contentEntity = ContentEntity(contentResponse!!.content)
                                    moduleResult.postValue(module)
                                }

                                override fun onDataNotAvailable() {

                                }
                            })
                        break
                    }
                }
            }

            override fun onDataNotAvailable() {
            }

        })

        return moduleResult
    }

}