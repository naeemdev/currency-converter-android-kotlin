package com.naeemdev.currencyconverter.repositories.currencies


import com.naeemdev.currencyconverter.data.local.currencies.CurrencyItem
import com.naeemdev.currencyconverter.data.remote.responses.CurrenciesResponse
import com.naeemdev.currencyconverter.utils.Resource

interface CurrenciesRepository {

    suspend fun insertAllCurrencyItems(currencyItems: List<CurrencyItem>)

    suspend fun insertCurrencyItem(currencyItem: CurrencyItem)

    suspend fun deleteCurrencyItem(currencyItem: CurrencyItem)

    suspend fun deleteAllCurrencyItems()

    suspend fun getAllCurrencyItems(): List<CurrencyItem>

    suspend fun getCurrencies(): Resource<CurrenciesResponse>
}