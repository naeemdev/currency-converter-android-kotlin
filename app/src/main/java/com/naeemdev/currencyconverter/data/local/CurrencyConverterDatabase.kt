package com.naeemdev.currencyconverter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naeemdev.currencyconverter.data.local.currencies.CurrencyDao
import com.naeemdev.currencyconverter.data.local.currencies.CurrencyItem
import com.naeemdev.currencyconverter.data.local.exchangerates.ExchangeItem
import com.naeemdev.currencyconverter.data.local.exchangerates.ExchangeRatesDao

@Database(
    entities = [CurrencyItem::class, ExchangeItem::class],
    version = 1
)
abstract class CurrencyConverterDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    abstract fun exchangeRatesDao(): ExchangeRatesDao
}