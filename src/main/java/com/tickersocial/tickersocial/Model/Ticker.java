package com.tickersocial.tickersocial.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ticker {
    private String currentPrice;
    private String currentVolume;
    private Activity activity;

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentVolume(String currentVolume) {
        this.currentVolume = currentVolume;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
