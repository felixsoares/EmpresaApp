package com.felix.empresa.data.repository

import com.felix.empresa.data.connection.API
import com.felix.empresa.data.database.LocalStorage
import com.felix.empresa.data.vo.request.LoginRequestVO
import com.felix.empresa.data.vo.response.LoginResponseVo
import com.felix.empresa.util.Constants
import io.reactivex.Observable

interface LoginRepository {
    fun doOauth(loginRequestVO: LoginRequestVO): Observable<LoginResponseVo>
}

class LoginRepositoryImpl(
    private val api: API,
    private val localStorage: LocalStorage
) : LoginRepository {

    override fun doOauth(loginRequestVO: LoginRequestVO): Observable<LoginResponseVo> {
        return api.doOauth(loginRequestVO).map { response ->
            if (response.success) {
                with(localStorage) {
                    save(Constants.ACCESS_TOKEN_KEY, response.accessToken)
                    save(Constants.CLIENT_KEY, response.client)
                    save(Constants.UID_KEY, response.uid)
                }
            }
            return@map response
        }
    }
}