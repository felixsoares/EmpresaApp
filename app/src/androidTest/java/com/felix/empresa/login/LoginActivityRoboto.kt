package com.felix.empresa.login

import android.content.Intent
import androidx.test.rule.ActivityTestRule
import com.felix.empresa.R
import com.felix.empresa.automation.UIAtomation
import com.felix.empresa.ui.login.view.LoginActivity

class LoginActivityRoboto(
    private val activity: ActivityTestRule<LoginActivity>
) {

    fun start(): LoginActivityRoboto {
        activity.launchActivity(Intent())
        return this
    }

    fun checkLoadingPresent(): LoginActivityRoboto {
        UIAtomation.checkDialogPresent()
        return this
    }

    fun checkElementsPresent(): LoginActivityRoboto {
        UIAtomation.checkElementPresent(R.id.inputEmail)
        UIAtomation.checkElementPresent(R.id.inputPassword)
        UIAtomation.checkElementPresent(R.id.btnLogin)
        return this
    }

    fun submitForm(): LoginActivityRoboto {
        UIAtomation.click(R.id.btnLogin)
        return this
    }

    fun checkError(): LoginActivityRoboto {
        UIAtomation.checkElementPresent(R.id.txtMessageError)
        return this
    }

    fun insertEmail(): LoginActivityRoboto {
        UIAtomation.inputText(R.id.edtEmail, "testeapple@ioasys.com.br")
        return this
    }

    fun insertPassword(): LoginActivityRoboto {
        UIAtomation.inputText(R.id.edtPassword, "12341234")
        return this
    }

}