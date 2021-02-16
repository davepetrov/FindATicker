package com.tickersocial.tickersocial.service.SocialServices;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tickersocial.tickersocial.Model.HotTickersComponent;
import com.tickersocial.tickersocial.Model.Ticker;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@NoArgsConstructor
public class StockTwitsService {
    private HotTickersComponent hotTickers;

    public StockTwitsService(HotTickersComponent hotTickersComponent) {
        this.hotTickers = hotTickersComponent;
    }

    public HotTickersComponent getTrending() throws UnirestException {
        this.populateTrending();
        this.populateTickerInfo();
        return this.hotTickers;
    }
    private void populateTrending() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest
                .get("https://api.stocktwits.com/api/2/trending/symbols.json")
                .queryString("limit", 10)
                .asJson();

        JSONObject bodyObj = jsonResponse.getBody().getObject();
        JSONArray symbolsObj = bodyObj.getJSONArray("symbols");

        for (int i = 0; i < symbolsObj.length(); i++) {
            Ticker ticker = new Ticker();
            ticker.setSymbol(symbolsObj.getJSONObject(i).getString("symbol"));
            ticker.setTitle(symbolsObj.getJSONObject(i).getString("title"));
            this.hotTickers.add(ticker);
        }
    }

    private void populateTickerInfo(){
        //TODO: Find a way to get mention volume given the ticker name
    }
}
