package com.felix.empresa.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.ContextCompat
import com.felix.empresa.data.connection.API
import com.felix.empresa.data.database.LocalStorage
import com.felix.empresa.data.repository.LoginRepository
import com.felix.empresa.data.repository.LoginRepositoryImpl
import com.felix.empresa.data.vo.request.LoginRequestVO
import com.felix.empresa.data.vo.response.LoginResponseVo
import com.felix.empresa.util.Constants
import io.mockk.*
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class LoginRepositoryTest {

    private val mApi: API = mockk(relaxed = true)
    private val mLocalStorage: LocalStorage = mockk(relaxed = true)
    lateinit var mRepository: LoginRepository

    @Before
    fun setup() {
        mRepository = LoginRepositoryImpl(mApi, mLocalStorage)
    }

    @Test
    fun testOauthMethod() {
        val request = LoginRequestVO("meu@email.com", "minha senha")
        val response = LoginResponseVo("access", "client", "uid")
        response.success = true

        every { mApi.doOauth(request) } returns Observable.just(response)

        mRepository.doOauth(request)

        verify {
            mApi.doOauth(request)
        }
    }

}