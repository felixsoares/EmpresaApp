package com.felix.empresa.login

import androidx.test.espresso.intent.rule.IntentsTestRule
import com.felix.empresa.ui.login.view.LoginActivity
import org.junit.After
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

    @get:Rule
    val mActivityTestRule = IntentsTestRule(LoginActivity::class.java, false, false)

    private val mRobot = LoginActivityRoboto(mActivityTestRule)

    @Test
    fun testErroOnSubmitEmptyForm() {
        mRobot
            .start()
            .checkElementsPresent()
            .submitForm()
            .checkError()
    }

    @Test
    fun testSubmitFormSuccessfully() {
        mRobot
            .start()
            .checkElementsPresent()
            .insertEmail()
            .insertPassword()
            .submitForm()
            .checkLoadingPresent()
    }

    @After
    fun finish() {
        mActivityTestRule.finishActivity()
    }
}