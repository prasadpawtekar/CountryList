package com.thomas.walmarttest.model.remote

import com.thomas.walmarttest.common.Constants
import com.thomas.walmarttest.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.COUNTRIES_ENDPOINT)
    suspend fun getCountryList(): Response<List<Country>>

    companion object {
        fun getInstance(): ApiService = ApiClient.retrofit.create(ApiService::class.java)
    }


}