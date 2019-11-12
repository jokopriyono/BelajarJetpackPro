package com.jo.belajarjetpackpro.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.jo.belajarjetpackpro.R
import com.jo.belajarjetpackpro.ui.home.HomeActivity
import org.junit.Rule
import org.junit.Test

class AcademyTest {

    @get:Rule
    val activityRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)

    @Test
    fun toDetailActivityTest() {
        Thread.sleep(3000)
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        Thread.sleep(3000)
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText("Menjadi Android Developer Expert")))
    }

    @Test
    fun toReaderActivityTest() {
        Thread.sleep(3000)
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        Thread.sleep(3000)
        onView(withId(R.id.btn_start)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_start)).perform(click())

        onView(withId(R.id.frame_container)).check(matches(isDisplayed()))

        Thread.sleep(3000)
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_module)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        Thread.sleep(3000)
        onView(withId(R.id.web_view)).check(matches(isDisplayed()))
    }
}