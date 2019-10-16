package com.jo.belajarjetpackpro.ui.reader

import com.jo.belajarjetpackpro.data.ContentEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class CourseReaderViewModelTest {

    private lateinit var courseReaderViewModel: CourseReaderViewModel
    private lateinit var dummyContentEntity: ContentEntity
    private lateinit var moduleId: String

    @Before
    fun setUp() {
        courseReaderViewModel = CourseReaderViewModel()
        courseReaderViewModel.courseId = "a14"

        moduleId = "a14m1"

        val title = courseReaderViewModel.getModules()[0].mTitle
        dummyContentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">$title</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
    }

    @Test
    fun getModules() {
        val moduleEntities = courseReaderViewModel.getModules()
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size)
    }

    @Test
    fun getSelectedModule() {
        courseReaderViewModel.moduleId = moduleId
        val moduleEntity = courseReaderViewModel.getSelectedModule()
        assertNotNull(moduleEntity)
        val contentEntity = moduleEntity?.contentEntity
        assertNotNull(contentEntity)
        val content = contentEntity?.mContent
        assertNotNull(content)
        assertEquals(content, dummyContentEntity.mContent)
    }
}