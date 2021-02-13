package com.tickersocial.tickersocial.controller;

import com.tickersocial.tickersocial.Model.Activity;
import com.tickersocial.tickersocial.Model.HotTickersComponent;
import com.tickersocial.tickersocial.service.TickerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/ticker")
@Slf4j
@AllArgsConstructor
public class TickerController {
    private TickerService tickerService;

    // Get top tickers -> gets top trending ticker on twitter, given some time interval
    // ie: want hourly stats -> interval="hr" + "top" tickers (ie: top 10 -> num=10)
    @GetMapping("/toptickers")
    public ResponseEntity<Activity> getTopTickers(){
        return tickerService.getTopTickers();
    }

}
