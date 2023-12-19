package com.naeemdev.currencyconverter.utils

import android.widget.Filter
import com.naeemdev.currencyconverter.data.local.exchangerates.ExchangeItem
import com.naeemdev.currencyconverter.ui.exchangeRate.ExchangeRatesAdapter

/**
 * Filter / Search in local list
 */
class ExchangeRateSearchFilter(
    private val mAdapter: ExchangeRatesAdapter,
    var currenciesList: ArrayList<ExchangeItem>
) : Filter() {

    private var filteredList: ArrayList<ExchangeItem> = ArrayList()

    override fun performFiltering(search: CharSequence): FilterResults {
        filteredList.clear()
        val results = FilterResults()
        if (search.isEmpty()) {
            filteredList.addAll(currenciesList)
        } else {
            for (contact in currenciesList) {
                if (contact.currency.contains(search, ignoreCase = true)) {
                    filteredList.add(contact)
                }
            }
        }
        results.values = filteredList
        results.count = filteredList.size
        return results
    }

    override fun publishResults(constraint: CharSequence, results: FilterResults) {
        mAdapter.updateSearchResults(results.values as ArrayList<ExchangeItem>)
    }

}