package com.example.testovoe4.dataclasses;

import com.fasterxml.jackson.annotation.*;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Realtime Currency Exchange Rate"
})
public class RealtimeCurrencyExchangeRate {

    @JsonProperty("Realtime Currency Exchange Rate")
    private RealtimeCurrencyExchangeRate_ realtimeCurrencyExchangeRate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Realtime Currency Exchange Rate")
    public RealtimeCurrencyExchangeRate_ getRealtimeCurrencyExchangeRate() {
        return realtimeCurrencyExchangeRate;
    }

    @JsonProperty("Realtime Currency Exchange Rate")
    public void setRealtimeCurrencyExchangeRate(RealtimeCurrencyExchangeRate_ realtimeCurrencyExchangeRate) {
        this.realtimeCurrencyExchangeRate = realtimeCurrencyExchangeRate;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

