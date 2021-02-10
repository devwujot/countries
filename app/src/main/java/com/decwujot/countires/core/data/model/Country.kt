package com.decwujot.countires.core.data.model

import com.google.gson.annotations.SerializedName

data class Country(
    var name: String,
    @SerializedName("alpha2Code")
    var alphaCode: String,
    var capital: String,
    @SerializedName("subregion")
    var region: String,
    var nativeName: String,
    var population: Int,
    var flag: String
)