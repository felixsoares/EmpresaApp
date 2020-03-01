package com.felix.empresa.login.model

import com.felix.empresa.ui.login.model.LoginModel
import org.junit.Assert
import org.junit.Test

class LoginModelTest {

    private val mLoginModel = LoginModel()

    @Test
    fun testValidateForm() {
        val email = "meu@email.com"
        val password = "123456"
        Assert.assertTrue(mLoginModel.validateForm(email, password))
    }

    @Test
    fun testInvalidEmail() {
        var email = ""
        val password = "123456"
        Assert.assertFalse(mLoginModel.validateForm(email, password))

        email = " "
        Assert.assertFalse(mLoginModel.validateForm(email, password))
    }

    @Test
    fun testInvalidPassword() {
        val email = "meu@email.com"
        var password = ""
        Assert.assertFalse(mLoginModel.validateForm(email, password))

        password = " "
        Assert.assertFalse(mLoginModel.validateForm(email, password))

        password = "123"
        Assert.assertFalse(mLoginModel.validateForm(email, password))
    }

}