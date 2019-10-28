package com.jo.belajarjetpackpro.ui.detail

import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.data.source.AcademyRepository
import com.jo.belajarjetpackpro.utils.FakeDataDummyy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class DetailCourseViewModelTest {

    private lateinit var detailCourseViewModel: DetailCourseViewModel
    private val academyRepository = mock(AcademyRepository::class.java)
    private val dummyCourse: CourseEntity = FakeDataDummyy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId

    @Before
    fun setUp() {
        detailCourseViewModel = DetailCourseViewModel(academyRepository)
        detailCourseViewModel.courseId = courseId
    }

    @Test
    fun getCourse() {
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(dummyCourse)
        val course = detailCourseViewModel.getCourse()
        verify(academyRepository).getCourseWithModules(courseId)
        assertNotNull(course)
        val courseId = course?.courseId
        assertNotNull(courseId)
        assertEquals(dummyCourse.courseId, courseId)
    }

    @Test
    fun getModules() {
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(
            FakeDataDummyy.generateDummyModules(courseId)
        )
        val moduleEntities = detailCourseViewModel.getModules()
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size)
    }
}