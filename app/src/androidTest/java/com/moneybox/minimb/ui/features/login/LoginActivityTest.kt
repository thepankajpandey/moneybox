package com.moneybox.minimb.ui.features.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moneybox.minimb.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @get: Rule
    var activityRule = ActivityScenarioRule(LoginActivity::class.java)

    private val email = "jaeren+androidtest@moneyboxapp.com"
    private val password = "P455word12"

    @Test
    fun loginActivityLaunchesCorrectViewTest() {
        onView(withId(R.id.img_mblogo)).check(matches(isDisplayed()))

        onView(withId(R.id.input_email)).perform(clearText(), typeText(email))

        onView(withId(R.id.input_password)).perform(clearText(), typeText(password))

        onView(withId(R.id.btn_login)).check(matches(isDisplayed()))
    }
}