package com.moeiny.reza.cognizanttest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.moeiny.reza.cognizanttest.CustomAssertions.Companion.hasError
import com.moeiny.reza.cognizanttest.CustomAssertions.Companion.hasItemCount
import com.moeiny.reza.cognizanttest.CustomMatchers.Companion.withItemCount
import com.moeiny.reza.cognizanttest.ui.info.InfoActivity

import org.junit.Rule
import org.junit.Test

class InfoActivityTest {

  @Rule
  @JvmField
  var activityRule = ActivityTestRule<InfoActivity>(InfoActivity::class.java)

  @Test
  fun countItems() {
    onView(withId(R.id.recyclerView))
      .check(matches(withItemCount(14)))
  }

  @Test
  fun allPresentAssertion() {
    onView(withId(R.id.recyclerView))
      .check(hasItemCount(14))
  }

  @Test
  fun errorAssertion() {
    onView(withId(R.id.recyclerView))
      .check(hasError())
  }

  }



