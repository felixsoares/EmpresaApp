package com.felix.empresa.data.vo.response

open class ResponseVO {
    var success: Boolean = false
    var errors: List<String> = mutableListOf()
}