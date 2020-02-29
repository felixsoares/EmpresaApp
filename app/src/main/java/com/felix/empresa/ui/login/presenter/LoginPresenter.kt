package com.felix.empresa.ui.login.presenter

import com.felix.empresa.data.repository.LoginRepository
import com.felix.empresa.data.vo.request.LoginRequestVO
import com.felix.empresa.ui.login.business.LoginContract
import com.felix.empresa.ui.login.model.LoginModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter(
    private val mView: LoginContract.View,
    private val mRepository: LoginRepository,
    private val mModel: LoginModel
) : LoginContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun clear() {
        compositeDisposable.clear()
    }

    override fun doLogin(email: String, password: String) {
        mView.hideErrors()
        mView.showLoading()

        if (!mModel.validateForm(email, password)) {
            mView.showError("Credenciais informadas são inválidas, tente novamente")
            mView.hideLoading()
            return
        }

        compositeDisposable.add(
            mRepository.doOauth(LoginRequestVO(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    mView.hideLoading()
                }
                .subscribe({ response ->
                    if (response.success) {
                        mView.openMainView()
                    } else {
                        mView.showError(response.errors[0])
                    }
                }, {
                    mView.showError("Ops, algo deu errado, tente novamente")
                    mView.hideLoading()
                })
        )
    }

}