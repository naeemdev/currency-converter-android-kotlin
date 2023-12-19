package com.naeemdev.currencyconverter.repositories.currencies

import com.naeemdev.currencyconverter.data.local.currencies.CurrencyDao
import com.naeemdev.currencyconverter.data.local.currencies.CurrencyItem
import com.naeemdev.currencyconverter.data.remote.APIs
import com.naeemdev.currencyconverter.data.remote.responses.CurrenciesResponse
import com.naeemdev.currencyconverter.utils.Constants.NO_INTERNET
import com.naeemdev.currencyconverter.utils.Constants.SOMETHING_WENT_WRONG
import com.naeemdev.currencyconverter.utils.Resource
import javax.inject.Inject

class DefaultCurrenciesRepository @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val APIs: APIs
) : CurrenciesRepository {

    override suspend fun insertAllCurrencyItems(currencyItems: List<CurrencyItem>) {
        currencyDao.insertCurrencyItems(currencyItems)
    }

    override suspend fun insertCurrencyItem(currencyItem: CurrencyItem) {
        currencyDao.insertCurrencyItem(currencyItem)
    }

    override suspend fun deleteCurrencyItem(currencyItem: CurrencyItem) {
        currencyDao.deleteCurrencyItem(currencyItem)
    }

    override suspend fun deleteAllCurrencyItems() {
        currencyDao.deleteAllCurrencyItems()
    }

    override suspend fun getAllCurrencyItems(): List<CurrencyItem> {
        return currencyDao.getAllCurrencyItems()
    }

    /**
     * Get currencies
     */
    override suspend fun getCurrencies(): Resource<CurrenciesResponse> {
        return try {
            val response = APIs.getCurrencies()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error(SOMETHING_WENT_WRONG, null)
            } else {
                Resource.error(SOMETHING_WENT_WRONG, null)
            }
        } catch (e: Exception) {
            Resource.error(NO_INTERNET, null)
        }
    }
}












