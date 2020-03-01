package com.felix.empresa.login.presenter

import com.felix.empresa.data.repository.LoginRepository
import com.felix.empresa.data.vo.request.LoginRequestVO
import com.felix.empresa.data.vo.response.LoginResponseVo
import com.felix.empresa.ui.login.business.LoginContract
import com.felix.empresa.ui.login.model.LoginModel
import com.felix.empresa.ui.login.presenter.LoginPresenter
import com.felix.empresa.util.RxImmediateSchedulerRule
import io.mockk.*
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginPresenterTest {

    @Rule
    @JvmField
    var testScheduler = RxImmediateSchedulerRule()

    private val mView: LoginContract.View = mockk(relaxed = true)
    private val mRepository: LoginRepository = mockk(relaxed = true)
    private val mModel: LoginModel = mockk(relaxed = true)
    lateinit var mPresenter: LoginContract.Presenter

    @Before
    fun setup() {
        mPresenter = LoginPresenter(mView, mRepository, mModel)
    }

    @Test
    fun testDoAouthWithSuccess() {
        val email = "meu@email.com"
        val password = "123456"
        every { mModel.validateForm(email, password) } returns true

        val response = LoginResponseVo("access", "client", "uid")
        response.success = true
        val request = LoginRequestVO(email, password)
        every { mRepository.doOauth(neq(request)) } returns Observable.just(response)

        mPresenter.doLogin(email, password)

        with(mView) {
            verifySequence {
                hideErrors()
                showLoading()
                openMainView()
                hideLoading()
            }
        }
    }

    @Test
    fun testDoAouthWithRequestError() {
        val email = "meu@email.com"
        val password = "123456"
        every { mModel.validateForm(email, password) } returns true

        val response = LoginResponseVo("access", "client", "uid")
        response.errors = listOf("Algum erro aconteceu")
        val request = LoginRequestVO(email, password)
        every { mRepository.doOauth(neq(request)) } returns Observable.just(response)

        mPresenter.doLogin(email, password)

        with(mView) {
            verifySequence {
                hideErrors()
                showLoading()
                showError(response.errors[0])
                hideLoading()
            }
        }
    }

    @Test
    fun testDoAouthWithRequestProblem() {
        val email = "meu@email.com"
        val password = "123456"
        every { mModel.validateForm(email, password) } returns true

        val request = LoginRequestVO(email, password)
        every { mRepository.doOauth(neq(request)) } returns Observable.error(Exception())

        mPresenter.doLogin(email, password)

        with(mView) {
            verifySequence {
                hideErrors()
                showLoading()
                showError(neq("Algum erro será lançado aqui"))
                hideLoading()
            }
        }
    }

    @Test
    fun testInvalidForm() {
        val email = "meu@email.com"
        val password = "1234"
        every { mModel.validateForm(email, password) } returns false

        mPresenter.doLogin(email, password)

        with(mView) {
            verifySequence {
                hideErrors()
                showLoading()
                showError(neq("Algum erro será lançado aqui"))
                hideLoading()
            }
        }
    }

}