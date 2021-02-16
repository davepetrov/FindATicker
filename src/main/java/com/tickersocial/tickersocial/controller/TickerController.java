package com.tickersocial.tickersocial.controller;

import com.tickersocial.tickersocial.Model.HotTickersComponent;
import com.tickersocial.tickersocial.Model.Ticker;
import com.tickersocial.tickersocial.service.TickerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/ticker")
@Slf4j
@AllArgsConstructor
public class TickerController {
    private TickerService tickerService;

    @GetMapping("/ticker")
    public ResponseEntity<Ticker> getTickerInfo(@RequestParam(value="symbol") String symbol, @RequestParam(value="interval") String interval) {
        return tickerService.getTickerInfo(interval);
    }

    @GetMapping("/toptickers")
    public ResponseEntity<HotTickersComponent> getTopTickers() {
        return tickerService.getTopTickers();
    }
}
