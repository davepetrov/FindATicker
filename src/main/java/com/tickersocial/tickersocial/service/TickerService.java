package com.tickersocial.tickersocial.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tickersocial.tickersocial.Model.Activity;
import com.tickersocial.tickersocial.Model.HotTickersComponent;
import com.tickersocial.tickersocial.Model.Ticker;
import com.tickersocial.tickersocial.Model.TickerNews;
import com.tickersocial.tickersocial.service.SocialServices.StockTwitsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TickerService{
    private HotTickersComponent hotTickers;
    private StockTwitsService stockTwitsService;

    public TickerService(){
        this.hotTickers = new HotTickersComponent();
        this.stockTwitsService = new StockTwitsService(this.hotTickers);
    }

    public ResponseEntity<HotTickersComponent> getTopTickers() {
        try {
            // https://api.stocktwits.com/developers/docs/api#trending-symbols-docs
            this.hotTickers = stockTwitsService.getTrending();
            if (this.hotTickers==null){
                return new ResponseEntity<HotTickersComponent>(HttpStatus.BAD_REQUEST);
            }


            return new ResponseEntity<HotTickersComponent>(this.hotTickers, HttpStatus.OK);
        }
        catch(UnirestException e){
            return new ResponseEntity<HotTickersComponent>(HttpStatus.BAD_REQUEST);
        }
    }

    // TODO
    public ResponseEntity<Ticker> getTickerInfo(String symbol, String interval){
        return new ResponseEntity<Ticker>(HttpStatus.FORBIDDEN);
    }

    // TODO
    public ResponseEntity<List<TickerNews>>getTickerNews(String symbol){
        return new ResponseEntity<List<TickerNews>>(HttpStatus.FORBIDDEN);
    }
    private Boolean isValidInterval(String interval){
        if (interval == null){
            return true;
        }
        List<String> validIntervals = new ArrayList<>(Arrays.asList("30m", "h", "d"));
        return validIntervals.contains(interval);
    }
}
