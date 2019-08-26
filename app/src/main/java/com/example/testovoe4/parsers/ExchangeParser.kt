package com.example.testovoe4.parsers

import com.example.testovoe4.dataclasses.RealtimeCurrencyExchangeRate
import com.example.testovoe4.dataclasses.RealtimeCurrencyExchangeRate_
import com.fasterxml.jackson.databind.ObjectMapper

class ExchangeParser {
    fun parse(input:String):RealtimeCurrencyExchangeRate_ {
        var mapper = ObjectMapper()
        var output = mapper.readValue(input, RealtimeCurrencyExchangeRate::class.java)
        return output.realtimeCurrencyExchangeRate
    }


}