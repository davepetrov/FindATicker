package com.tickersocial.tickersocial.service.SocialServices;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tickersocial.tickersocial.Model.HotTickersComponent;
import com.tickersocial.tickersocial.Model.Ticker;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;

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
        for (Ticker ticker: this.hotTickers.getTickers()){
            String tickerId = ticker.getSymbol();

            //Disable actually opening the browser + set path to chrome driver
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);

            WebDriverManager.getInstance(CHROME).setup();
            ChromeDriver driver = new ChromeDriver();

            //Open browser, search
            WebDriverWait wait = new WebDriverWait(driver, 30);
            driver.navigate().to("https://stocktwits.com/symbol/"+tickerId);

//            LocalDate currentDateTime = LocalDate.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YY"); // dd/mm/yy
//            String currentDateFormatt/**/

            List<WebElement> postList = driver.findElements(
                    By.xpath("//*[@id=\"app\"]/div/div/div[3]/div/div/div[1]/div[2]/div/div/div[2]/div[3]/div"));
            ticker.getActivity().addStockTwitzMentions(postList.size());

            driver.quit();
        }
    }
}
