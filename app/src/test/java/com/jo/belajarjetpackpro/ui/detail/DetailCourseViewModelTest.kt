package com.jo.belajarjetpackpro.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.data.ModuleEntity
import com.jo.belajarjetpackpro.data.source.AcademyRepository
import com.jo.belajarjetpackpro.utils.FakeDataDummyy
import com.jo.belajarjetpackpro.utils.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


class DetailCourseViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var detailCourseViewModel: DetailCourseViewModel
    private val academyRepository = mock(AcademyRepository::class.java)
    private val dummyCourse: CourseEntity = FakeDataDummyy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId
    private val dummyModules = FakeDataDummyy.generateDummyModules(courseId)

    @Before
    fun setUp() {
        detailCourseViewModel = DetailCourseViewModel(academyRepository)
        detailCourseViewModel.courseId = courseId
    }

    @Test
    fun getCourse() {
        val courseEntities = MutableLiveData<CourseEntity>()
        courseEntities.value = dummyCourse
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(courseEntities)
        val observer: Observer<CourseEntity?> = mock()
        detailCourseViewModel.getCourse().observeForever(observer)
        verify(observer).onChanged(dummyCourse)
    }

    @Test
    fun getModules() {
        val moduleEntities = MutableLiveData<List<ModuleEntity>>()
        moduleEntities.value = dummyModules
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(moduleEntities)
        val observer: Observer<List<ModuleEntity>> = mock()
        detailCourseViewModel.getModules().observeForever(observer)
        verify(observer).onChanged(dummyModules)
    }
}