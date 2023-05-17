package com.thomas.walmarttest.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val name: String,

    @SerializedName("capital")
    val capital: String,

    @SerializedName("code")
    val code: String,

    @SerializedName("region")
    val region: String
)
