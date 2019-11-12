package com.jo.belajarjetpackpro.ui.academy

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


class AcademyViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var academyViewModel: AcademyViewModel
    private val academyRepository = mock(AcademyRepository::class.java)


    @Before
    fun setUp() {
        academyViewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourses() {
        val dummyCourse = FakeDataDummyy.generateDummyCourses()
        val courses = MutableLiveData<List<CourseEntity>>()
        courses.value = dummyCourse

        val observer: Observer<List<CourseEntity>> = mock()

        `when`(academyViewModel.getCourses()).thenReturn(courses)
        academyViewModel.getCourses().observeForever(observer)

        verify(observer).onChanged(dummyCourse)
    }
}