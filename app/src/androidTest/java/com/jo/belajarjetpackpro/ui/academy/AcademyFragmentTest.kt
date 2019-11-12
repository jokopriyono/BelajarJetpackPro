package com.jo.belajarjetpackpro.ui.academy

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

class AcademyFragmentTest {
    @get:Rule
    val activityRule = ActivityTestRule<SingleFragmentActivity>(SingleFragmentActivity::class.java)
    private val academyFragment = AcademyFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(academyFragment)
    }

    @Test
    fun loadCourses() {
        Thread.sleep(3000)
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).check(RecyclerViewItemCountAssertion(5))
    }
}