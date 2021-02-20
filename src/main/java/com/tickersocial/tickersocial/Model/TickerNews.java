package com.tickersocial.tickersocial.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TickerNews {
    private String symbol;
    private String title;
    private String source; // yahoo/bloomberg
    private String newsTitle;
    private String newsDesc;
    private String frontPicture;
    private String datePosted;
}
