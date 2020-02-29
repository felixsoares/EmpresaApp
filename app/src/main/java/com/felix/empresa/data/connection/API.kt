package com.felix.empresa.data.connection

import com.felix.empresa.data.vo.request.LoginRequestVO
import com.felix.empresa.data.vo.response.EnterpriseResponseVO
import com.felix.empresa.data.vo.response.ListEnterpriseResponseVO
import com.felix.empresa.data.vo.response.LoginResponseVo
import io.reactivex.Observable
import retrofit2.http.*

interface API {
    @POST("users/auth/sign_in")
    fun doOauth(
        @Body request: LoginRequestVO
    ): Observable<LoginResponseVo>

    @GET("enterprises")
    fun searchEntreprises(
        @Query("name") name: String
    ): Observable<ListEnterpriseResponseVO>

    @GET("enterprises/{id}")
    fun searchEntreprise(
        @Path("id") idEnterprise: Int
    ): Observable<EnterpriseResponseVO>
}