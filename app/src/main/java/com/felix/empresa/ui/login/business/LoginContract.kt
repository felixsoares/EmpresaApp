package com.felix.empresa.ui.login.business

interface LoginContract {
    interface Presenter {
        fun clear()
        fun doLogin(email: String, password: String)
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun openMainView()
        fun showError(error: String)
        fun hideErrors()
    }
}