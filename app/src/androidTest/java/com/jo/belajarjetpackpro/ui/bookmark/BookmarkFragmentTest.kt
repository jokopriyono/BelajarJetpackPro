package com.jo.belajarjetpackpro.ui.bookmark

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.testing.SingleFragmentActivity
import com.jo.belajarjetpackpro.utils.RecyclerViewItemCountAssertion
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BookmarkFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule<SingleFragmentActivity>(SingleFragmentActivity::class.java)
    private val bookmarkFragment = BookmarkFragment.newInstance()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(bookmarkFragment)
    }

    @Test
    fun loacBookmarks() {
        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_bookmark)).check(RecyclerViewItemCountAssertion(5))
    }
}