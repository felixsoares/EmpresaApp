package com.felix.empresa.data.vo.response

import java.io.Serializable

class EnterpriseVO(
    val id: Int,
    val name: String,
    val country: String,
    val zone: String,
    val image: String,
    val description: String
) : ResponseVO(), Serializable