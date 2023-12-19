package com.naeemdev.currencyconverter.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.naeemdev.currencyconverter.ui.currencies.CurrenciesAdapter
import com.naeemdev.currencyconverter.ui.exchangeRate.ExchangeRatesAdapter
import com.naeemdev.currencyconverter.ui.currencies.CurrenciesFragment
import com.naeemdev.currencyconverter.ui.exchangeRate.ExchangeRatesFragment
import javax.inject.Inject

class FragmentsFactory @Inject constructor(
    private val exchangeRatesAdapter: ExchangeRatesAdapter,
    private val currenciesAdapter: CurrenciesAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ExchangeRatesFragment::class.java.name -> ExchangeRatesFragment(exchangeRatesAdapter)
            CurrenciesFragment::class.java.name -> CurrenciesFragment(currenciesAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}