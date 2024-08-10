package org.trading.testdata.timeseries;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TickerDataProducerConfiguration {

    public static final String QUEUE_NAME = "ticker-data";

    @Bean
    public Queue tickerDataQueue() {
        return new Queue(QUEUE_NAME);
    }
}
