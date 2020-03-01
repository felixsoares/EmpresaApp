package com.felix.empresa.ui.main.presenter

import com.felix.empresa.data.repository.EnterpriseRepository
import com.felix.empresa.ui.main.business.MainContract
import com.felix.empresa.ui.main.model.MainModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(
    private val mView: MainContract.View,
    private val mRepository: EnterpriseRepository,
    private val mModel: MainModel
) : MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getEnterprise(id: Int) {
        mView.showFullLoading()

        compositeDisposable.add(
            mRepository.getEnterprise(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    mView.hideFullLoading()
                }
                .subscribe({ response ->
                    if (response.success) {
                        mView.openEnterpriseDetail(response.enterprise)
                    } else {
                        mView.showErrorToast()
                    }
                }, {
                    mView.showErrorToast()
                    mView.hideFullLoading()
                })
        )
    }

    override fun verifyMessageVisbility() {
        if (!mModel.hasEnterprise) {
            mView.hideMessage()
            mView.showInitialMessage()
        }
    }

    override fun doSearch(search: String) {
        mView.hideMessage()
        mView.showLoading()

        if (!mModel.validateSearch(search)) {
            mView.hideLoading()
            mView.showMessage("Pesquisa somente após 3 caracteres")
            return
        }

        compositeDisposable.add(
            mRepository.doSearch(search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { mView.hideLoading() }
                .subscribe({ response ->
                    when {
                        response.success && response.enterprises.isNotEmpty() -> {
                            mModel.setHasEnterprises()
                            mView.setupEnterprises(response.enterprises)
                        }
                        response.success && response.enterprises.isEmpty() -> {
                            mModel.setHasentEnterprises()
                            mView.showMessage("Nenhuma empresa foi encontrada\npara busca realizada")
                        }
                        !response.success -> {
                            mModel.setHasentEnterprises()
                            mView.showMessage(response.errors[0])
                        }
                    }
                }, {
                    mView.showMessage("Ops, algo errado, tente novamente")
                    mView.hideLoading()
                })
        )
    }

    override fun clear() {
        mRepository.clearPreferences()
        compositeDisposable.clear()
    }
}