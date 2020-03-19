package com.felix.empresa.automation

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.felix.empresa.R

object UIAtomation {

    fun checkTextById(id: Int, text: String) {
        onView(withId(id)).check(matches(withText(text)))
    }

    fun checkElementPresent(id: Int) {
        onView(withId(id)).check(matches(isDisplayed()))
    }

    fun click(id: Int) {
        onView(withId(id)).perform(click())
    }

    fun inputText(id: Int, text: String) {
        onView(withId(id)).perform(typeText(text))
        Espresso.closeSoftKeyboard()
    }

    fun checkDialogPresent() {
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
    }
}