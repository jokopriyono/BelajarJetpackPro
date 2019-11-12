package com.jo.belajarjetpackpro.ui.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jo.belajarjetpackpro.data.CourseEntity
import com.jo.belajarjetpackpro.data.source.AcademyRepository
import com.jo.belajarjetpackpro.utils.FakeDataDummyy
import com.jo.belajarjetpackpro.utils.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class BookmarkViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var bookmarkViewModel: BookmarkViewModel
    private val academyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setUp() {
        bookmarkViewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun getBookmarks() {
        val dummyCourses = FakeDataDummyy.generateDummyCourses()
        val courses = MutableLiveData<List<CourseEntity>>()
        courses.value = dummyCourses

        `when`(academyRepository.getBookmarkedCourses()).thenReturn(courses)

        val observer: Observer<List<CourseEntity>> = mock()

        bookmarkViewModel.getBookmarks().observeForever(observer)

        verify(observer).onChanged(dummyCourses)
    }
}