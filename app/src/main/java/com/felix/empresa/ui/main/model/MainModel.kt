package com.felix.empresa.ui.main.model

class MainModel {

    var hasEnterprise = false

    fun validateSearch(search: String): Boolean {
        return search.length > 3
    }

    fun setHasEnterprises() {
        hasEnterprise = true
    }

    fun setHasNotEnterprises(){
        hasEnterprise = false
    }

}