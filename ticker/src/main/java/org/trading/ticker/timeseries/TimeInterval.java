package org.trading.ticker.timeseries;

public enum TimeInterval {

    ONE_MINUTE("1m"),
    FIVE_MINUTES("5m"),
    FIFTEEN_MINUTES("15m"),
    THIRTY_MINUTES("30m"),
    ONE_HOUR("1h"),
    FOUR_HOURS("4h"),
    ONE_DAY("1d"),
    ONE_WEEK("1w"),
    ONE_MONTH("1M");

    private final String value;

    TimeInterval(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TimeInterval fromValue(String value) {
        for (TimeInterval timeInterval : values()) {
            if (timeInterval.value.equals(value)) {
                return timeInterval;
            }
        }

        throw new IllegalArgumentException("Invalid time interval value: " + value);
    }
}
