package com.jo.belajarjetpackpro.ui.detail

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.utils.FakeDataDummy
import com.jo.belajarjetpackpro.utils.RecyclerViewItemCountAssertion
import org.junit.Rule
import org.junit.Test

class DetailCourseActivityTest {

    private val dummyCourse = FakeDataDummy.generateDummyCourses()[0]

    @get:Rule
    val activityRule =
        object : ActivityTestRule<DetailCourseActivity>(DetailCourseActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, DetailCourseActivity::class.java)
                result.putExtra(DetailCourseActivity.EXTRA_COURSE, dummyCourse.courseId)
                return result
            }
        }

    @Test
    fun loadCourse() {
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyCourse.title)))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(
            matches(
                withText(
                    String.format(
                        "Deadline %s",
                        dummyCourse.deadline
                    )
                )
            )
        )
    }

    @Test
    fun loadModules() {
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_module)).check(RecyclerViewItemCountAssertion(7))
    }
}