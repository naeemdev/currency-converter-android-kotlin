package com.naeemdev.currencyconverter.utils

object Constants {

    const val BASE_URL: String = "http://api.currencylayer.com/"
    const val API_KEY: String = "95b7bc55cf26da275c4ab5b395c3f767"
    const val FETCH_INTERVAL: Int = (30 * 60) // 30 minutes * 60 seconds
    //End Points
    const val LIVE_END_POINT = "/live"
    const val CURRENCIES_END_POINT = "list"

    //Other constants
    const val DATABASE_NAME = "currency_converter_db"
    const val SEARCH_TIME_DELAY = 300L
    const val DEFAULT_SOURCE_CURRENCY = "USD"
    const val NO_INTERNET = "No internet connection"
    const val SOMETHING_WENT_WRONG = "An unknown error occurred"
    const val KEY_SELECTED_CURRENCY = "SelectedCurrency"
}