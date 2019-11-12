package com.jo.belajarjetpackpro.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jo.belajarjetpackpro.data.source.remote.RemoteRepository
import com.jo.belajarjetpackpro.utils.FakeDataDummyy
import com.jo.belajarjetpackpro.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock


class AcademyRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteRepository::class.java)
    private val academyRepository = FakeAcademyRepository(remote)

    private val courseResponses = FakeDataDummyy.generateRemoteDummyCourses()
    private val courseId = courseResponses[0].id
    private val moduleResponses = FakeDataDummyy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponses[0].moduleId
    private val content = FakeDataDummyy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses() {
        doAnswer {
            (it.arguments[0] as RemoteRepository.LoadCoursesCallback).onAllCoursesReceived(
                courseResponses
            )
            return@doAnswer null
        }.`when`(remote).getAllCourses(any())

        val result = LiveDataTestUtil.getValue(academyRepository.getAllCourses())

        verify(
            remote,
            times(1)
        ).getAllCourses(any())

        assertNotNull(result)
        assertEquals(courseResponses.size, result.size)
    }

    @Test
    fun getAllModulesByCourse() {
        doAnswer {
            (it.arguments[1] as RemoteRepository.LoadModulesCallback).onAllModulesReceived(
                moduleResponses
            )
            return@doAnswer null
        }.`when`(remote)
            .getModules(eq(courseId), any())

        val result = LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))

        verify(remote, times(1)).getModules(
            eq(courseId),
            any()
        )

        assertNotNull(result)
        assertEquals(moduleResponses.size, result.size)
    }

    @Test
    fun getBookmarkedCourses() {
        doAnswer {
            (it.arguments[0] as RemoteRepository.LoadCoursesCallback).onAllCoursesReceived(
                courseResponses
            )
            return@doAnswer null
        }.`when`(remote).getAllCourses(any())

        val result = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses())

        verify(
            remote,
            times(1)
        ).getAllCourses(any())

        assertNotNull(result)
        assertEquals(courseResponses.size, result.size)
    }

    @Test
    fun getContent() {
        doAnswer {
            (it.arguments[1] as RemoteRepository.LoadModulesCallback).onAllModulesReceived(
                moduleResponses
            )
            return@doAnswer null
        }.`when`(remote)
            .getModules(eq(courseId), any())

        doAnswer {
            (it.arguments[1] as RemoteRepository.GetContentCallback).onContentReceived(
                content
            )
            return@doAnswer null
        }.`when`(remote)
            .getContent(eq(moduleId), any())

        val resultContent =
            LiveDataTestUtil.getValue(academyRepository.getContent(courseId, moduleId))

        verify(remote, times(1)).getModules(
            eq(courseId),
            any()
        )
        verify(remote, times(1)).getContent(
            eq(moduleId),
            any()
        )

        assertNotNull(resultContent)
        assertNotNull(resultContent?.contentEntity)
        assertNotNull(resultContent?.contentEntity?.mContent)
        assertEquals(content.content, resultContent?.contentEntity?.mContent)
    }

    @Test
    fun getCourseWithModules() {
        doAnswer {
            (it.arguments[0] as RemoteRepository.LoadCoursesCallback).onAllCoursesReceived(
                courseResponses
            )
        }.`when`(remote).getAllCourses(any())

        val result = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId))

        verify(remote, times(1)).getAllCourses(any())

        assertNotNull(result)
        assertNotNull(result?.title)
        assertEquals(courseResponses[0].title, result?.title)
    }
}