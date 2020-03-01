package com.felix.empresa.repository

import com.felix.empresa.data.connection.API
import com.felix.empresa.data.database.LocalStorage
import com.felix.empresa.data.repository.EnterpriseRepository
import com.felix.empresa.data.repository.EnterpriseRepositoryImpl
import com.felix.empresa.data.vo.response.EnterpriseResponseVO
import com.felix.empresa.data.vo.response.EnterpriseVO
import com.felix.empresa.data.vo.response.ListEnterpriseResponseVO
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class EnterpriseRepositoryTest {

    private val mApi: API = mockk(relaxed = true)
    private val mLocalStorage: LocalStorage = mockk(relaxed = true)
    lateinit var mRepository: EnterpriseRepository

    @Before
    fun setup() {
        mRepository = EnterpriseRepositoryImpl(mApi, mLocalStorage)
    }

    @Test
    fun testDoSearch() {
        val search = "empresas"
        val response = ListEnterpriseResponseVO(mutableListOf())
        every { mApi.searchEntreprises(search) } returns Observable.just(response)

        mRepository.doSearch(search)

        verify {
            mApi.searchEntreprises(search)
        }
    }

    @Test
    fun testGetEnterprise() {
        val id = 1
        val response = EnterpriseResponseVO(
            EnterpriseVO(
                1,
                "name",
                "country",
                "zone",
                "image",
                "description"
            )
        )
        every { mApi.searchEntreprise(id) } returns Observable.just(response)

        mRepository.getEnterprise(id)

        verify {
            mApi.searchEntreprise(id)
        }
    }

}