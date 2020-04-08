package me.antonyderham

import com.fasterxml.jackson.annotation.JsonProperty

class GameSalesRecord {
    @JsonProperty("Rank")
    private val rank = 0.0

    @JsonProperty("Name")
    private val name: String? = null

    @JsonProperty("Platform")
    private val platform: String? = null

    @JsonProperty("Year")
    private val year: String? = null

    @get:JsonProperty("Genre")
    val genre: String? = null

    @JsonProperty("Publisher")
    private val publisher: String? = null

    @JsonProperty("NA_Sales")
    private val naSales = 0.0

    @JsonProperty("EU_Sales")
    private val euSales = 0.0

    @JsonProperty("JP_Sales")
    private val jpSales = 0.0

    @JsonProperty("Other_Sales")
    private val otherSales = 0.0

    @get:JsonProperty("Global_Sales")
    val globalSales = 0.0
}
