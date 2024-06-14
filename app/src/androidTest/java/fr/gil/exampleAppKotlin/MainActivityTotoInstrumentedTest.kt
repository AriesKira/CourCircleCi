package com.example.myfirstapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.gil.exampleAppKotlin.views.MainActivity
import fr.gil.exampleAppKotlin.R
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import tools.fastlane.screengrab.Screengrab
import tools.fastlane.screengrab.locale.LocaleTestRule

@RunWith(AndroidJUnit4::class)
class MainActivityTotoInstrumentedTest {

    @get:Rule
    val activityRule = androidx.test.rule.ActivityTestRule(MainActivity::class.java)

    @Test
    fun test_main_activity_toto_is_displayed() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main_activity)).check(matches(isDisplayed()))
        Screengrab.screenshot("main_activity_toto")
    }
}
