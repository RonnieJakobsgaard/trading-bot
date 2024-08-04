package org.trading.ticker.notification;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
public class NotificationTopic {

    public static final String TICKER_TOPIC = "ticker.notification";

    @Bean
    public TopicExchange tickerTopic() {
        return new TopicExchange(TICKER_TOPIC);
    }
}
