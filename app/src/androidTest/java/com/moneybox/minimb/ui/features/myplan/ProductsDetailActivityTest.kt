package com.moneybox.minimb.ui.features.myplan

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moneybox.minimb.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductsDetailActivityTest {
    @get: Rule
    var activityRule = ActivityScenarioRule(ProductsDetailActivity::class.java)

    @Test
    fun productsDetailActivityLaunchesCorrectViewTest() {
        Espresso.onView(ViewMatchers.withId(R.id.iv_plan_value_label))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.productProgressBar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.iv_plan_value))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
