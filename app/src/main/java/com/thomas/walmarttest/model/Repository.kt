package com.thomas.walmarttest.model

import com.thomas.walmarttest.model.remote.ApiService

class Repository(val apiService: ApiService) {

    suspend fun getCountryList() = apiService.getCountryList()
}