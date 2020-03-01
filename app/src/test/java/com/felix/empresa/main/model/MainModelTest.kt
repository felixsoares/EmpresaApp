package com.felix.empresa.main.model

import com.felix.empresa.ui.main.model.MainModel
import org.junit.Assert
import org.junit.Test

class MainModelTest {

    private val mModel = MainModel()

    @Test
    fun testValidSearch() {
        val search = "empresa"
        Assert.assertTrue(mModel.validateSearch(search))
    }

    @Test
    fun testInvalidSearch() {
        val search = "emp"
        Assert.assertFalse(mModel.validateSearch(search))
    }

    @Test
    fun testHasEnterprises() {
        mModel.setHasEnterprises()
        Assert.assertTrue(mModel.hasEnterprise)
    }

    @Test
    fun testHasentEnterprises() {
        mModel.setHasentEnterprises()
        Assert.assertFalse(mModel.hasEnterprise)
    }
}