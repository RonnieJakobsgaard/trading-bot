package org.trading.api.description;

public class DescriptionDTO {

    private String symbol;
    private String name;
    private String description;
    private String exchange;

    public DescriptionDTO() {
    }

    public DescriptionDTO(String symbol, String name, String description, String exchange) {
        this.symbol = symbol;
        this.name = name;
        this.description = description;
        this.exchange = exchange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
