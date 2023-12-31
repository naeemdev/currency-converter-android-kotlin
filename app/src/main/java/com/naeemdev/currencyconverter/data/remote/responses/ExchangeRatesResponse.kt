package com.naeemdev.currencyconverter.data.remote.responses


import com.google.gson.annotations.SerializedName

data class ExchangeRatesResponse(
    @SerializedName("quotes")
    var quotes: HashMap<String, Double> = HashMap(),
    @SerializedName("source")
    val source: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timestamp")
    val timestamp: Int
)