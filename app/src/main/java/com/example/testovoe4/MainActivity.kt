package com.example.testovoe4

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.example.testovoe4.interfaces.AlphavantageService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usdTV = findViewById<TextView>(R.id.Usd)
        val eurTV = findViewById<TextView>(R.id.Eur)
        val gbpTV = findViewById<TextView>(R.id.GBP)


        val values = listOf(
            Pair("USD", findViewById<TextView>(R.id.Usd)),
            Pair("EUR", findViewById<TextView>(R.id.Eur)),
            Pair("GBP", findViewById<TextView>(R.id.GBP))
        )
        val apiService = AlphavantageService.create()
        apiService.getExchange(
            "CURRENCY_EXCHANGE_RATE",
            "USD",
            "RUB",
            "H1CUA8G3C0AFD74W"
        )
            .repeat()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(60000, TimeUnit.MILLISECONDS)
            .subscribe({
                usdTV.text = "USD: " + it.realtimeCurrencyExchangeRate.get5ExchangeRate() + " rub"
            },
                {
                    usdTV.setText("USD: 0 rub")
                    Log.e("ERROR", it.message)
                    NetworkUsages().noInternetAlert(this)
                })

        apiService.getExchange(
            "CURRENCY_EXCHANGE_RATE",
            "EUR",
            "RUB",
            "H1CUA8G3C0AFD74W"
        )
            .delay(60000, TimeUnit.MILLISECONDS)
            .repeat()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                eurTV.text = "EUR: " + it.realtimeCurrencyExchangeRate.get5ExchangeRate() + " rub"
            },
                {
                    eurTV.setText("EUR: 0 rub")
                    Log.e("ERROR", it.message)
                })

        apiService.getExchange(
            "CURRENCY_EXCHANGE_RATE",
            "GBP",
            "RUB",
            "H1CUA8G3C0AFD74W"
        )
            .repeat()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(60000, TimeUnit.MILLISECONDS)
            .subscribe({
                gbpTV.text = "GBP: " + it.realtimeCurrencyExchangeRate.get5ExchangeRate() + " rub"
            },
                {
                    gbpTV.setText("GBP: 0 rub")
                    Log.e("ERROR", it.message)
                })
    }
}
