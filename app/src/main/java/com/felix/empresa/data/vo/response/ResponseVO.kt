package com.felix.empresa.data.vo.response

open class ResponseVO {
    val success: Boolean = false
    val errors: List<String> = mutableListOf()
}