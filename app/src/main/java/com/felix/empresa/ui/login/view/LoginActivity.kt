package com.felix.empresa.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.felix.empresa.R
import com.felix.empresa.ui.login.business.LoginContract
import com.felix.empresa.ui.main.view.MainActivity
import com.felix.empresa.widget.CustomDialog
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.scope.currentScope
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var mDialog: CustomDialog
    private val mPresenter by lazy {
        this.currentScope.get<LoginContract.Presenter> {
            parametersOf(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mDialog = CustomDialog(this)
        btnLogin.setOnClickListener {
            mPresenter.doLogin(edtEmail.text.toString(), edtPassword.text.toString())
        }
    }

    override fun onDestroy() {
        mPresenter.clear()
        super.onDestroy()
    }

    override fun showLoading() {
        mDialog.show()
    }

    override fun hideLoading() {
        mDialog.dismiss()
    }

    override fun openMainView() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun hideErrors() {
        inputEmail.error = null
        inputPassword.error = null
        txtMessageError.visibility = View.GONE
    }

    override fun showError(error: String) {
        inputEmail.error = " "
        inputPassword.error = " "
        txtMessageError.visibility = View.VISIBLE
        txtMessageError.text = error
    }
}
