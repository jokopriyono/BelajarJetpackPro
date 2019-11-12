package com.jo.belajarjetpackpro.ui.reader

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jo.belajarjetpackpro.data.ContentEntity
import com.jo.belajarjetpackpro.data.ModuleEntity
import com.jo.belajarjetpackpro.data.source.AcademyRepository
import com.jo.belajarjetpackpro.utils.FakeDataDummyy
import com.jo.belajarjetpackpro.utils.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class CourseReaderViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var courseReaderViewModel: CourseReaderViewModel
    private val academyRepository = mock(AcademyRepository::class.java)
    private val dummyCourses = FakeDataDummyy.generateDummyCourses()[0]
    private val courseId = dummyCourses.courseId
    private val dummyModules = FakeDataDummyy.generateDummyModules(courseId)
    private val moduleId = dummyModules[0].mModuleId

    @Before
    fun setUp() {
        courseReaderViewModel = CourseReaderViewModel(academyRepository)
        courseReaderViewModel.courseId = courseId
        courseReaderViewModel.moduleId = moduleId
    }

    @Test
    fun getModules() {
        val moduleEntities = MutableLiveData<List<ModuleEntity>>()
        moduleEntities.value = dummyModules
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(moduleEntities)
        val observer: Observer<List<ModuleEntity>> = mock()
        courseReaderViewModel.getModules().observeForever(observer)
        verify(observer).onChanged(dummyModules)
    }

    @Test
    fun getSelectedModule() {
        val moduleEntity = MutableLiveData<ModuleEntity>()
        val dummyModule = dummyModules[0]
        val content =
            "<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
        dummyModule.contentEntity = ContentEntity(content)
        moduleEntity.value = dummyModule

        `when`(academyRepository.getContent(courseId, moduleId)).thenReturn(moduleEntity)

        val entity = courseReaderViewModel.getSelectedModule()
        val observer: Observer<ModuleEntity?> = mock()
        entity.observeForever(observer)
//        verify(academyRepository).getContent(courseId, moduleId)
//        assertNotNull(entity)
    }
}