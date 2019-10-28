package com.jo.belajarjetpackpro.ui.detail

import com.jo.belajarjetpackpro.data.CourseEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailCourseViewModelTest {

    private lateinit var detailCourseViewModel: DetailCourseViewModel
    private lateinit var dummyCourse: CourseEntity

    @Before
    fun setUp() {
        detailCourseViewModel = DetailCourseViewModel()
        dummyCourse = CourseEntity(
            "a14",
            "Menjadi Android Developer Expert",
            "Dicoding sebagai satu-satunya Google Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah diverifikasi langsung oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini. Peserta akan belajar membangun aplikasi Android dengan materi Testing, Debugging, Application, Application UX, Fundamental Application Components, Persistent Data Storage, dan Enhanced System Integration.",
            "100 Hari",
            false,
            "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg"
        )
    }

    @Test
    fun getCourse() {
        detailCourseViewModel.courseId = dummyCourse.courseId
        val courseEntity = detailCourseViewModel.getCourse()
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity.courseId)
        assertEquals(dummyCourse.deadline, courseEntity.deadline)
        assertEquals(dummyCourse.description, courseEntity.description)
        assertEquals(dummyCourse.imagePath, courseEntity.imagePath)
        assertEquals(dummyCourse.title, courseEntity.title)
    }

    @Test
    fun getModules() {
        val moduleEntities = detailCourseViewModel.getModules()
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size)
    }
}