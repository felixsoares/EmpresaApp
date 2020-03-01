package com.felix.empresa.ui.login.model

class LoginModel {

    fun validateForm(email: String, password: String): Boolean {
        return isEmailValid(email) && isPasswordValid(password)
    }

    private fun isEmailValid(email: String): Boolean {
        return email.trim().isNotEmpty()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.trim().isNotEmpty() && password.length >= 6
    }

}