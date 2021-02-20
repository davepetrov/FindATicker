package com.tickersocial.tickersocial.controller;

import com.tickersocial.tickersocial.Model.HotTickersComponent;
import com.tickersocial.tickersocial.Model.Ticker;
import com.tickersocial.tickersocial.Model.TickerNews;
import com.tickersocial.tickersocial.service.TickerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/ticker")
@Slf4j
@AllArgsConstructor
public class TickerController {
    private TickerService tickerService;

    // localhost:8080/ticker?symbol=XXX?interval=1h
    // Returns extra analytics about a particular ticker within given intervals (30m, h, d)
    @GetMapping("/ticker")
    public ResponseEntity<Ticker> getTickerInfo(@RequestParam(value="symbol") String symbol, @RequestParam(value="interval") String interval) {
        return tickerService.getTickerInfo(symbol, interval);
    }

    // localhost:8080/toptickers;
    // Returns top 10 tickers based on team's discussed secret algorithm
    @GetMapping("/toptickers")
    public ResponseEntity<HotTickersComponent> getTopTickers() {
        return tickerService.getTopTickers();
    }

    // localhost:8080/news?symbol=XXX;
    // Returns 3 most recent news posts from Yahoo/bloomberg
    @GetMapping("/news")
    public ResponseEntity<List<TickerNews>> getTickerNews(@RequestParam(value="symbol") String symbol) {
        return tickerService.getTickerNews(symbol);
    }

}
