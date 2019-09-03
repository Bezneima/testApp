package com.example.testovoe4.interfaces

import com.example.testovoe4.dataclasses.RealtimeCurrencyExchangeRate
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface AlphavantageService {
    companion object Factory {
        fun create(): AlphavantageService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("https://www.alphavantage.co/")
                .build()
            return retrofit.create(AlphavantageService::class.java)
        }
    }

    @GET("query")
    fun getExchange(
        @Query("function") function: String,
        @Query("from_currency") from_currency: String,
        @Query("to_currency") to_currency: String,
        @Query("apikey") apiKey: String
    ): Observable<RealtimeCurrencyExchangeRate>
}