package com.felix.empresa.main.presenter

import com.felix.empresa.data.repository.EnterpriseRepository
import com.felix.empresa.data.vo.response.EnterpriseVO
import com.felix.empresa.data.vo.response.ListEnterpriseResponseVO
import com.felix.empresa.ui.main.business.MainContract
import com.felix.empresa.ui.main.model.MainModel
import com.felix.empresa.ui.main.presenter.MainPresenter
import com.felix.empresa.util.RxImmediateSchedulerRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainPresenterTest {

    @Rule
    @JvmField
    var testScheduler = RxImmediateSchedulerRule()

    private val mView: MainContract.View = mockk(relaxed = true)
    private val mRepository: EnterpriseRepository = mockk(relaxed = true)
    private val mModel: MainModel = mockk(relaxed = true)
    lateinit var mPresenter: MainContract.Presenter

    @Before
    fun setup() {
        mPresenter = MainPresenter(mView, mRepository, mModel)
    }

    @Test
    fun testDoSearchSuccessfuly() {
        val search = "empresa"
        every { mModel.validateSearch(search) } returns true

        val response = mockEnterprises()
        response.success = true
        every { mRepository.doSearch(search) } returns Observable.just(response)

        mPresenter.doSearch(search)

        with(mView) {
            verifySequence {
                hideMessage()
                showLoading()
                setupEnterprises(response.enterprises)
                hideLoading()
            }
        }

        verify {
            mModel.setHasEnterprises()
        }
    }

    @Test
    fun testDoSearchWithEmptyResponse() {
        val search = "empresa"
        every { mModel.validateSearch(search) } returns true

        val response = mockEmptyEnterprises()
        response.success = true
        every { mRepository.doSearch(search) } returns Observable.just(response)

        mPresenter.doSearch(search)

        with(mView) {
            verifySequence {
                hideMessage()
                showLoading()
                showMessage(neq("alguma mensagem de vazio"))
                hideLoading()
            }
        }

        verify {
            mModel.setHasentEnterprises()
        }
    }

    @Test
    fun testDoSearchWithError() {
        val search = "empresa"
        every { mModel.validateSearch(search) } returns true

        val response = mockEmptyEnterprises()
        response.success = false
        every { mRepository.doSearch(search) } returns Observable.just(response)

        mPresenter.doSearch(search)

        with(mView) {
            verifySequence {
                hideMessage()
                showLoading()
                showMessage(neq("alguma mensagem de vazio"))
                hideLoading()
            }
        }

        verify {
            mModel.setHasentEnterprises()
        }
    }

    @Test
    fun testProblemWhenRequest() {
        val search = "empresa"
        every { mModel.validateSearch(search) } returns true
        every { mRepository.doSearch(search) } returns Observable.error(Exception())

        mPresenter.doSearch(search)

        with(mView) {
            verifySequence {
                hideMessage()
                showLoading()
                showMessage(neq("alguma mensagem de vazio"))
                hideLoading()
            }
        }
    }

    @Test
    fun testWithInvalidSearch() {
        val search = "emp"
        every { mModel.validateSearch(search) } returns false

        mPresenter.doSearch(search)

        with(mView) {
            verifySequence {
                hideMessage()
                showLoading()
                hideLoading()
                showMessage(neq("alguma mensagem de vazio"))
            }
        }
    }

    private fun mockEnterprises(): ListEnterpriseResponseVO {
        return ListEnterpriseResponseVO(
            listOf(
                EnterpriseVO(
                    1,
                    "name",
                    "country",
                    "zone",
                    "image",
                    "description"
                ),
                EnterpriseVO(
                    2,
                    "name",
                    "country",
                    "zone",
                    "image",
                    "description"
                )
            )
        )
    }

    private fun mockEmptyEnterprises(): ListEnterpriseResponseVO {
        return ListEnterpriseResponseVO(listOf())
    }
}