package com.naeemdev.currencyconverter.repositories.exchangerate

import com.naeemdev.currencyconverter.data.local.exchangerates.ExchangeItem
import com.naeemdev.currencyconverter.data.remote.responses.ExchangeRatesResponse
import com.naeemdev.currencyconverter.utils.Resource

interface ExchangeRateRepository {

    suspend fun insertAllExchangeItems(exchangeItems: List<ExchangeItem>)

    suspend fun insertExchangeItem(exchangeItem: ExchangeItem)

    suspend fun deleteExchangeItem(exchangeItem: ExchangeItem)

    suspend fun deleteAllExchangeItems()

    suspend fun deleteAllExchangeItems(selectedSourceCurrency: String)

    suspend fun getAllExchangeItems(): List<ExchangeItem>

    suspend fun getAllExchangeItems(selectedSourceCurrency: String): List<ExchangeItem>

    suspend fun getExchangeRates(selectedSourceCurrency: String): Resource<ExchangeRatesResponse>
}