package org.trading.testdata.timeseries;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class TickerDataProducer {

    private final RabbitTemplate rabbitTemplate;
    private final Queue tickerDataQueue;

    public TickerDataProducer(RabbitTemplate rabbitTemplate, Queue tickerDataQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.tickerDataQueue = tickerDataQueue;
    }

    public void publish(String message) {
        rabbitTemplate.convertAndSend(tickerDataQueue.getName(), message);
    }
}
