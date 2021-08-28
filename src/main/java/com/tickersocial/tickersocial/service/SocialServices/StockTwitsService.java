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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        this.populateTrendingTickerInfo();
//        this.populateTrending();
//        this.populateTickerInfo();
        return this.hotTickers;
    }
    private void populateTrendingTickerInfo(){
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        WebDriverManager.getInstance(CHROME).setup();
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        // Open browser, search
        driver.navigate().to("https://stocktwits.com/rankings/most-active");

        By tablePath = By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div/div/div[2]/div/div/div[2]/div/div/div/div[3]/div[1]/div/table/tbody");
        WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(tablePath));

        List<WebElement> posts = table.findElements(By.xpath(".//*"));
        for (WebElement row: posts){
            String symbol=row.findElement(By.xpath("./td[2]/span/a/span")).getText();
            String count=row.findElement(By.xpath("./td[4]")).getText();

            Ticker ticker = new Ticker();
            ticker.setSymbol(symbol);
            ticker.setTitle(count);
            this.hotTickers.add(ticker);
        }
    }

//    private void populateTrending() throws UnirestException {
//        HttpResponse<JsonNode> jsonResponse = Unirest
//                .get("https://api.stocktwits.com/api/2/trending/symbols.json")
//                .queryString("limit", 10)
//                .asJson();
//
//        JSONObject bodyObj = jsonResponse.getBody().getObject();
//        JSONArray symbolsObj = bodyObj.getJSONArray("symbols");
//
//        for (int i = 0; i < symbolsObj.length(); i++) {
//            Ticker ticker = new Ticker();
//            ticker.setSymbol(symbolsObj.getJSONObject(i).getString("symbol"));
//            ticker.setTitle(symbolsObj.getJSONObject(i).getString("title"));
//            this.hotTickers.add(ticker);
//        }
//    }
//
//    private void populateTickerInfo(){
//        //Disable opening the browser + set path to chrome driver
//        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(true);
//        WebDriverManager.getInstance(CHROME).setup();
//        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver");
//        ChromeDriver driver = new ChromeDriver();
//        WebDriverWait wait = new WebDriverWait(driver, 3);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        for (Ticker ticker: this.hotTickers.getTickers()){
//            String tickerId = ticker.getSymbol();
//
//            //Open browser, search
//            driver.navigate().to("https://stocktwits.com/symbol/"+tickerId);
//
////            LocalDate currentDateTime = LocalDate.now();
////            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YY"); // dd/mm/yy
////            String currentDateFormatt/**/
//            int count = 0;
//            WebElement postsParentDiv = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[4]/div/div/div[1]/div[2]/div/div/div[3]/div[3]/div"));
//            List<WebElement> posts = postsParentDiv.findElements(By.xpath(".//*"));
//            WebElement current = posts.get(count);
//            String postTime = current.findElement(By.className("st_28bQfzV st_1E79qOs st_3TuKxmZ st_1VMMH6S")).getText();
//            while (postTime.matches("\\b((1[0-2]|0?[1-9]):([0-5][0-9]) ([AaPp][Mm]))")){
//                if (count==posts.size()){
//                    js.executeScript("arguments[0].scrollIntoView();", posts.get(count));
//                }
//                count+=1;
//                current = posts.get(count);
//                postTime = current.findElement(By.className("st_28bQfzV st_1E79qOs st_3TuKxmZ st_1VMMH6S")).getText();
//            }
//
//            ticker.getActivity().addStockTwitzMentions(count);
//
//        }
//        driver.quit();
//    }
}
