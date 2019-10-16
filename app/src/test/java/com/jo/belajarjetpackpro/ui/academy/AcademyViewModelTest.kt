package com.jo.belajarjetpackpro.ui.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class AcademyViewModelTest {
    private lateinit var academyViewModel: AcademyViewModel

    @Before
    fun setUp() {
        academyViewModel = AcademyViewModel()
    }

    @Test
    fun getCourses() {
        val courseEntities = academyViewModel.getCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}