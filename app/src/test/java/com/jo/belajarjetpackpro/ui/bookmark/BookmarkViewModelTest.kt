package com.jo.belajarjetpackpro.ui.bookmark

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class BookmarkViewModelTest {
    private lateinit var bookmarkViewModel: BookmarkViewModel

    @Before
    fun setUp() {
        bookmarkViewModel = BookmarkViewModel()
    }

    @Test
    fun getBookmarks() {
        val courseEntities = bookmarkViewModel.getBookmarks()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}