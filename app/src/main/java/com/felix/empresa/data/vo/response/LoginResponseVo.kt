package com.felix.empresa.data.vo.response

import com.google.gson.annotations.SerializedName

class LoginResponseVo(
    @SerializedName("accesstoken")
    val accessToken: String,
    val client: String,
    val uid: String
) : ResponseVO()