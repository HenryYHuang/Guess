package com.home.guess

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import kotlinx.android.synthetic.main.activity_materia.*
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MateriaActivityTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MateriaActivity>(MateriaActivity::class.java)

    @Test
    fun guessWrong() {
    val secret = activityTestRule.activity.secretNumber.secret
    val resource = activityTestRule.activity.resources
//        val n = 5
        for (n in 1..10) {
            if (n != secret) {
                onView(withId(R.id.ed_number)).perform(clearText())
                //未簡化
                Espresso.onView(ViewMatchers.withId(R.id.ed_number))
                    .perform(ViewActions.typeText(n.toString()))
                //簡化
                onView(withId(R.id.ok_button)).perform(click())
                val message = if (n < secret) resource.getString(R.string.bigger)
                else resource.getString(R.string.smaller)
                onView(withText(message)).check(matches(isDisplayed()))
                onView(withText(R.string.ok)).perform(click())
            }
        }
    }

    @Test
    fun resetCount() {
        val secret = activityTestRule.activity.secretNumber.secret
        val resource = activityTestRule.activity.resources
        val n = -1
        onView(withId(R.id.ed_number)).perform(typeText(n.toString()), closeSoftKeyboard())
        onView(withId(R.id.ok_button)).perform(click())
        onView(withText(R.string.ok)).perform(click())
        onView(withId(R.id.fab)).perform(click())
        onView(withText(R.string.ok)).perform(click())
        onView(withId(R.id.txt_counter)).check(matches(withText("0")))

    }

}