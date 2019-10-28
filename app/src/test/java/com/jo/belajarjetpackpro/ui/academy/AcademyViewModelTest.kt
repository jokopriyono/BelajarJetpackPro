package com.jo.belajarjetpackpro.ui.academy

import com.jo.belajarjetpackpro.data.source.AcademyRepository
import com.jo.belajarjetpackpro.utils.FakeDataDummyy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class AcademyViewModelTest {
    private lateinit var academyViewModel: AcademyViewModel
    private val academyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setUp() {
        academyViewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourses() {
        `when`(academyRepository.getAllCourses()).thenReturn(FakeDataDummyy.generateDummyCourses())
        val courseEntities = academyViewModel.getCourses()
        verify(academyRepository).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}