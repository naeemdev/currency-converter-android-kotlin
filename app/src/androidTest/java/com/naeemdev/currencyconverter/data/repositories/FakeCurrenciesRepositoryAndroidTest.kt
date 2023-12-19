package com.naeemdev.currencyconverter.data.repositories

import androidx.lifecycle.MutableLiveData
import com.naeemdev.currencyconverter.data.local.currencies.CurrencyItem
import com.naeemdev.currencyconverter.data.remote.responses.CurrenciesResponse
import com.naeemdev.currencyconverter.repositories.currencies.CurrenciesRepository
import com.naeemdev.currencyconverter.utils.Resource

class FakeCurrenciesRepositoryAndroidTest : CurrenciesRepository {

    private val _currencies = mutableListOf<CurrencyItem>()
    private val currencies = MutableLiveData<List<CurrencyItem>>(_currencies)


    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        currencies.postValue(_currencies)
    }

    override suspend fun insertAllCurrencyItems(currencyItems: List<CurrencyItem>) {
        _currencies.addAll(currencyItems)
        refreshLiveData()
    }

    override suspend fun insertCurrencyItem(currencyItem: CurrencyItem) {
        _currencies.add(currencyItem)
        refreshLiveData()
    }

    override suspend fun deleteCurrencyItem(currencyItem: CurrencyItem) {
        _currencies.remove(currencyItem)
        refreshLiveData()
    }

    override suspend fun deleteAllCurrencyItems() {
        _currencies.clear()
        refreshLiveData()
    }

    override suspend fun getAllCurrencyItems(): List<CurrencyItem> {
        return _currencies
    }

    /**
     * Get Popular Article
     *  getCurrencies(): Resource<Currencies>
     */
    override suspend fun getCurrencies(): Resource<CurrenciesResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)

        } else {
            val currenciesMap = hashMapOf<String, String>()
            currenciesMap["AED"] = "United Arab Emirates Dirham"
            currenciesMap["AFN"] = "Afghan Afghani"
            val response = CurrenciesResponse(currenciesMap, true)
            Resource.success(response)
        }
    }
}











