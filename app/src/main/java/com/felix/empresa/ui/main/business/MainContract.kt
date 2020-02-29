package com.felix.empresa.ui.main.business

import com.felix.empresa.data.vo.response.EnterpriseVO

interface MainContract {
    interface Presenter {
        fun doSearch(search: String)
        fun clear()
        fun getEnterprise(id: Int)
        fun verifyMessageVisbility()
    }

    interface View {
        fun showInitialMessage()
        fun openEnterpriseDetail(enterprise: EnterpriseVO)
        fun showLoading()
        fun hideLoading()
        fun setupEnterprises(enterprises: List<EnterpriseVO>)
        fun showMessage(message: String)
        fun hideMessage()
        fun showFullLoading()
        fun showErrorToast()
        fun hideFullLoading()
    }
}