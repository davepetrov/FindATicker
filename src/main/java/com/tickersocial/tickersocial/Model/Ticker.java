package com.tickersocial.tickersocial.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ticker {
    private String symbol; // UID
    private String title;
    private String currentPrice;
    private String currentVolume;
    private Activity activity;

    public Ticker(String symbol, String title){
        this.symbol = symbol;
        this.title = title=title;
        this.activity=new Activity();

    }
    public Ticker(){
        this.activity=new Activity();
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrentPrice() {
        return this.currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getCurrentVolume() {
        return this.currentVolume;
    }

    public void setCurrentVolume(String currentVolume) {
        this.currentVolume = currentVolume;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
