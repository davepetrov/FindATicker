package com.tickersocial.tickersocial.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotTickersComponent {
    private List<Ticker> tickers;

    public HotTickersComponent(){
        this.tickers = new ArrayList<Ticker>();
    }

    public List<Ticker> getTickers() {
        return this.tickers;
    }

    public void add(Ticker newTicker){
        //Check if ticker is in hot tickers list
        for (Ticker ticker: this.tickers){
            if (ticker.getSymbol().equals(newTicker.getSymbol())){
                return;
            }
        }
        this.tickers.add(newTicker);
    }
}
