package com.tickersocial.tickersocial.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotTickersComponent {
    private List<Ticker> Tickers;

    public List<Ticker> getTickers() {
        return Tickers;
    }

    public void setTickers(List<Ticker> tickers) {
        Tickers = tickers;
    }
}
