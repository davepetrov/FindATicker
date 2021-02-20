package com.tickersocial.tickersocial.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Activity {
    private int stockTwitsMentions;
    private int twitterHashtags;

    public int getStockTwitsMentions() {
        return stockTwitsMentions;
    }

    public void setStockTwitsMentions(int stockTwitsMentions) {
        this.stockTwitsMentions = stockTwitsMentions;
    }

    public int getTwitterHashtags() {
        return twitterHashtags;
    }

    public void setTwitterHashtags(int twitterHashtags) {
        this.twitterHashtags = twitterHashtags;
    }

    public void incrementStockTwitzMentions(){
        this.twitterHashtags+=1;
    }

    public void addStockTwitzMentions(int c){
        this.twitterHashtags+=c;
    }





}
