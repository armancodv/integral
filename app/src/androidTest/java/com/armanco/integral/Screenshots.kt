package com.armanco.integral

import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.rule.ActivityTestRule
import androidx.viewpager2.widget.ViewPager2
import com.armanco.integral.ui.activity.main.MainActivity
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

        // category
        runOnUiThread { AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO) }
        onView(isRoot()).perform(waitFor(1000))
        Screengrab.screenshot("3_category_light")
        runOnUiThread { AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES) }
        onView(isRoot()).perform(waitFor(1000))
        Screengrab.screenshot("3_category_dark")

        // formula
        onView(withId(R.id.rvCategory)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2,click()))
        runOnUiThread { AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO) }
        onView(isRoot()).perform(waitFor(1000))
        Screengrab.screenshot("4_formula_light")
        runOnUiThread { AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES) }
        onView(isRoot()).perform(waitFor(1000))
        Screengrab.screenshot("4_formula_dark")

        // solver
        runOnUiThread { activityRule.activity.findNavController(R.id.nav_host_fragment).navigate(R.id.solverFragment) }
        runOnUiThread { AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO) }
        onView(isRoot()).perform(waitFor(1000))
        Screengrab.screenshot("1_solver_light")
        runOnUiThread { AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES) }
        onView(isRoot()).perform(waitFor(1000))
        Screengrab.screenshot("1_solver_dark")

        // plot
        runOnUiThread {
            activityRule.activity.findNavController(R.id.nav_host_fragment).navigate(R.id.plotFragment)
        }
        onView(isRoot()).perform(waitFor(1000))
        onView(withId(R.id.plotButton))?.perform(click())
        runOnUiThread { AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO) }
        onView(isRoot()).perform(waitFor(1000))
        Screengrab.screenshot("2_plot_light")
        runOnUiThread { AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES) }
        onView(isRoot()).perform(waitFor(1000))
        Screengrab.screenshot("2_plot_dark")

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
