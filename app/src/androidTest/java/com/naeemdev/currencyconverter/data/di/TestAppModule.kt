package com.naeemdev.currencyconverter.data.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naeemdev.currencyconverter.data.local.CurrencyConverterDatabase
import com.naeemdev.currencyconverter.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named(Constants.DATABASE_NAME)
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, CurrencyConverterDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}