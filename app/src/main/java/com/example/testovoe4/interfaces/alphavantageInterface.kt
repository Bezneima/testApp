package com.example.testovoe4.interfaces

import com.example.testovoe4.dataclasses.AllExchanges
import com.example.testovoe4.dataclasses.RealtimeCurrencyExchangeRate
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface alphavantageService {
    companion object Factory {
        fun create(): alphavantageService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("https://www.alphavantage.co/")
                .build()
            return retrofit.create(alphavantageService::class.java)
        }
    }

    @GET("query")
    fun getExchange(
        @Query("function") function: String,
        @Query("from_currency") from_currency: String,
        @Query("to_currency") to_currency: String,
        @Query("apikey") apiKey: String
    ): RealtimeCurrencyExchangeRate


    fun getAllExchange(): AllExchanges {
        val allExchanges = AllExchanges()
        allExchanges.euRtoRUB = getExchange(
            "CURRENCY_EXCHANGE_RATE",
            "USD",
            "RUB",
            "H1CUA8G3C0AFD74W"
        ).realtimeCurrencyExchangeRate.get5ExchangeRate()

        allExchanges.euRtoRUB = getExchange(
            "CURRENCY_EXCHANGE_RATE",
            "EUR",
            "RUB",
            "H1CUA8G3C0AFD74W"
        ).realtimeCurrencyExchangeRate.get5ExchangeRate()

        allExchanges.euRtoRUB = getExchange(
            "CURRENCY_EXCHANGE_RATE",
            "GBT",
            "RUB",
            "H1CUA8G3C0AFD74W"
        ).realtimeCurrencyExchangeRate.get5ExchangeRate()

        return allExchanges
    }
}