package me.antonyderham;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameSalesRecord {
    @JsonProperty("Rank")
    private double rank;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Platform")
    private String platform;

    @JsonProperty("Year")
    private String year;

    private String genre;

    @JsonProperty("Genre")
    public String getGenre() {
        return genre;
    }

    @JsonProperty("Publisher")
    private String publisher;

    @JsonProperty("NA_Sales")
    private double naSales;

    @JsonProperty("EU_Sales")
    private double euSales;

    @JsonProperty("JP_Sales")
    private double jpSales;

    @JsonProperty("Other_Sales")
    private double otherSales;

    private double globalSales;

    @JsonProperty("Global_Sales")
    public double getGlobalSales() {
        return globalSales;
    }
}
