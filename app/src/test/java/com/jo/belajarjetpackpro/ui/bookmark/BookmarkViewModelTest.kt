package com.jo.belajarjetpackpro.ui.bookmark

import com.jo.belajarjetpackpro.data.source.AcademyRepository
import com.jo.belajarjetpackpro.utils.FakeDataDummyy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class BookmarkViewModelTest {
    private lateinit var bookmarkViewModel: BookmarkViewModel
    private val academyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setUp() {
        bookmarkViewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun getBookmarks() {
        `when`(academyRepository.getBookmarkedCourses()).thenReturn(FakeDataDummyy.generateDummyCourses())
        val courseEntities = bookmarkViewModel.getBookmarks()
        verify(academyRepository).getBookmarkedCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}