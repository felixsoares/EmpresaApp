package com.felix.empresa.data.repository

import com.felix.empresa.data.connection.API
import com.felix.empresa.data.database.LocalStorage
import com.felix.empresa.data.vo.response.EnterpriseResponseVO
import com.felix.empresa.data.vo.response.ListEnterpriseResponseVO
import io.reactivex.Observable

interface EnterpriseRepository {
    fun doSearch(search: String): Observable<ListEnterpriseResponseVO>
    fun getEnterprise(id: Int): Observable<EnterpriseResponseVO>
    fun clearPreferences()
}

class EnterpriseRepositoryImpl(
    private val api: API,
    private val localStorage: LocalStorage
) : EnterpriseRepository {

    override fun getEnterprise(id: Int): Observable<EnterpriseResponseVO> {
        return api.searchEntreprise(id)
    }

    override fun clearPreferences() {
        localStorage.clearAll()
    }

    override fun doSearch(search: String): Observable<ListEnterpriseResponseVO> {
        return api.searchEntreprises(search)
    }


}