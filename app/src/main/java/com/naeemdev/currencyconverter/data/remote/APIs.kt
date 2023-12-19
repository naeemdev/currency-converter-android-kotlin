package com.naeemdev.currencyconverter.data.remote

import com.naeemdev.currencyconverter.data.remote.responses.CurrenciesResponse
import com.naeemdev.currencyconverter.data.remote.responses.ExchangeRatesResponse
import com.naeemdev.currencyconverter.utils.Constants.API_KEY
import com.naeemdev.currencyconverter.utils.Constants.CURRENCIES_END_POINT
import com.naeemdev.currencyconverter.utils.Constants.DEFAULT_SOURCE_CURRENCY
import com.naeemdev.currencyconverter.utils.Constants.LIVE_END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {

    @GET(LIVE_END_POINT)
    suspend fun getExchangeRates(
        @Query("source") source: String = DEFAULT_SOURCE_CURRENCY,
        @Query("access_key") apiKey: String = API_KEY
    ): Response<ExchangeRatesResponse>


    @GET(CURRENCIES_END_POINT)
    suspend fun getCurrencies(
        @Query("access_key") apiKey: String = API_KEY
    ): Response<CurrenciesResponse>

}