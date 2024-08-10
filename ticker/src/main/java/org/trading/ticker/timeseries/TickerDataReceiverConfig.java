package org.trading.ticker.timeseries;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TickerDataReceiverConfig {

    public static final String QUEUE_NAME = "ticker-data";

    @Bean
    public Queue tickerDataQueue() {
        return new Queue(QUEUE_NAME);
    }
}
