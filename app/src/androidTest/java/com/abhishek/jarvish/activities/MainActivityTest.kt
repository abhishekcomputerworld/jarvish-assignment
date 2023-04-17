package com.abhishek.jarvish.activities

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.abhishek.jarvish.R


@RunWith(JUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNavigationToAnotherFragment() {
        // Start the activity scenario
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Perform an action to navigate to another fragment
   /*     Espresso.onView(ViewMatchers.withId(R.id.action_FirstFragment_to_SecondFragment))
            .perform(ViewActions.click())*/

        // Close the activity scenario
        activityScenario.close()
    }
}
