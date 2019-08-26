package com.example.testovoe4

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.testovoe4.interfaces.alphavantageService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usdTV = findViewById<TextView>(R.id.Usd)
        val eurTV = findViewById<TextView>(R.id.Eur)
        val gtbTV = findViewById<TextView>(R.id.Rub)

        val apiService = alphavantageService.create()

        val a = Observable.just(apiService.getAllExchange())
        val disposable = a
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                usdTV.text = result.usDtoRUB
                eurTV.text = result.euRtoRUB
                gtbTV.text = result.gbPtoRUB
            }, { error ->
                error.printStackTrace()
            })
    }


}
