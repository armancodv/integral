package com.armanco.integral

import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matcher
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tools.fastlane.screengrab.Screengrab
import tools.fastlane.screengrab.cleanstatusbar.CleanStatusBar
import tools.fastlane.screengrab.locale.LocaleTestRule


@RunWith(JUnit4::class)
class Screenshots {
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Rule @JvmField
    val localeTestRule = LocaleTestRule()

    @Test
    fun testTakeScreenshot() {
        runOnUiThread { AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO) }
        onView(isRoot()).perform(waitFor(2000))
        Screengrab.screenshot("category_light")
        runOnUiThread { AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES) }
        onView(isRoot()).perform(waitFor(2000))
        Screengrab.screenshot("category_dark")
        onView(withId(R.id.rvCategory)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2,click()))
        runOnUiThread { AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO) }
        onView(isRoot()).perform(waitFor(2000))
        Screengrab.screenshot("formula_light")
        runOnUiThread { AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES) }
        onView(isRoot()).perform(waitFor(2000))
        Screengrab.screenshot("formula_dark")
        onView(withId(R.id.solver)).perform(click())
        runOnUiThread { AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO) }
        onView(isRoot()).perform(waitFor(2000))
        Screengrab.screenshot("solver_light")
        runOnUiThread { AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES) }
        onView(isRoot()).perform(waitFor(2000))
        Screengrab.screenshot("solver_dark")

    }

    private fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }


    fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id)
                v.performClick()
            }
        }
    }

    companion object {

        @BeforeClass @JvmStatic
        fun beforeAll() {
            CleanStatusBar.enableWithDefaults()
        }

        @AfterClass @JvmStatic
        fun afterAll() {
            CleanStatusBar.disable()
        }
    }
}
