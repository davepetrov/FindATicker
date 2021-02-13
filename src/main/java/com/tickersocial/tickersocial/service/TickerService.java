package com.tickersocial.tickersocial.service;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tickersocial.tickersocial.Model.Activity;
import com.tickersocial.tickersocial.Model.HotTickersComponent;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TickerService{

    public ResponseEntity<Activity> getTopTickers() {
//        if (!isValidInterval(interval)){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }


//        HttpResponse<JsonNode> jsonResponse = Unirest.get("https://api.stocktwits.com/api/2/trending/symbols.json")
//                .routeParam("limit", Integer.toString(num))
//                .asJson();
//
//        JSONObject json = new JSONObject(jsonResponse);
//
//        jsonResponse.getBody().
        Activity test = new Activity();
        test.setTwitterHashtags(2);
        test.setStockTwitsMentions(2);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    private Boolean isValidInterval(String interval){
        List<String> validIntervals = new ArrayList<>(Arrays.asList("h", "d", "w", "m"));
        return validIntervals.contains(interval);
    }

}
