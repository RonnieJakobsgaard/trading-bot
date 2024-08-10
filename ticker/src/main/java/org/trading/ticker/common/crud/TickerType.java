package org.trading.ticker.common.crud;

public enum TickerType {

    STOCK("stock"),
    INDEX("index");

    private final String type;

    TickerType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static TickerType fromString(String type) {
        for (TickerType tickerType : TickerType.values()) {
            if (tickerType.type.equalsIgnoreCase(type)) {
                return tickerType;
            }
        }

        throw new IllegalArgumentException("Unknown ticker type: " + type);
    }
}
