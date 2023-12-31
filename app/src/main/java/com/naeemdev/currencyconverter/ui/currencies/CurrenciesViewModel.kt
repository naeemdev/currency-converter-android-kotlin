package com.naeemdev.currencyconverter.ui.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naeemdev.currencyconverter.data.local.currencies.CurrencyItem
import com.naeemdev.currencyconverter.data.remote.responses.CurrenciesResponse
import com.naeemdev.currencyconverter.repositories.currencies.CurrenciesRepository
import com.naeemdev.currencyconverter.utils.Constants
import com.naeemdev.currencyconverter.utils.Event
import com.naeemdev.currencyconverter.utils.Resource
import com.naeemdev.currencyconverter.utils.Status

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val repository: CurrenciesRepository
) : ViewModel() {

    /**
     * Livedata to hold currencies
     */
    val _currencies = MutableLiveData<Event<Resource<ArrayList<CurrencyItem>>>>()

    val currencies: LiveData<Event<Resource<ArrayList<CurrencyItem>>>> = _currencies

    fun updateCurrenciesLiveData(response: Resource<ArrayList<CurrencyItem>>) {
        _currencies.postValue(Event(response))
    }

    fun deleteAllCurrencyItems() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllCurrencyItems()
    }

    fun insertCurrencyItemsIntoDb(currencyItems: List<CurrencyItem>) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAllCurrencyItems(currencyItems)
        }

    /**
     * Search currencies from DB and Server
     */
    fun getCurrencies() {
        _currencies.value = Event(Resource.loading(null))

        viewModelScope.launch(Dispatchers.IO) {
            val dbResponse = repository.getAllCurrencyItems()

            viewModelScope.launch {
                updateCurrenciesLiveData(Resource.success(ArrayList(dbResponse)))
                if (dbResponse.isEmpty()) {
                    val response = repository.getCurrencies()
                    validateResponse(response)
                }
            }
        }
    }

    /*
      Validate and propagate response to UI
     */
    private fun validateResponse(response: Resource<CurrenciesResponse>) {
        val currencyItems: ArrayList<CurrencyItem> = ArrayList()
        when (response.status) {
            Status.SUCCESS -> {
                response.data?.currencies?.forEach { (currency, description) ->
                    currencyItems.add(
                        CurrencyItem(
                            currency = currency,
                            currencyLabel = description
                        )
                    )
                }
                if (currencyItems.isNotEmpty()) {
                    deleteAllCurrencyItems()
                    insertCurrencyItemsIntoDb(currencyItems)
                }
                updateCurrenciesLiveData(Resource.success(currencyItems))
            }
            else -> {
                updateCurrenciesLiveData(
                    Resource.error(
                        response.message ?: Constants.NO_INTERNET,
                        currencyItems
                    )
                )
            }
        }
    }

}













