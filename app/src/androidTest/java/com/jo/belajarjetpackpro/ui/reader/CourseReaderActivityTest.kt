package com.jo.belajarjetpackpro.ui.reader

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.utils.FakeDataDummy
import com.jo.belajarjetpackpro.utils.RecyclerViewItemCountAssertion
import org.junit.Rule
import org.junit.Test

class CourseReaderActivityTest {
    private val dummyCourse = FakeDataDummy.generateDummyCourses()[0]

    @get:Rule
    val activityRule = object : ActivityTestRule<CourseReaderActivity>(CourseReaderActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val intent = Intent(targetContext, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, dummyCourse.courseId)
            return intent
        }
    }

    @Test
    fun loadModules() {
        onView(ViewMatchers.withId(R.id.rv_module)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.rv_module)).check(RecyclerViewItemCountAssertion(7))
    }

    @Test
    fun clickModule() {
        onView(ViewMatchers.withId(R.id.rv_module)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.rv_module)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(ViewMatchers.withId(R.id.web_view)).check(matches(isDisplayed()))
    }
}