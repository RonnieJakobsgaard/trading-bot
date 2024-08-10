package org.trading.ticker.notification;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.trading.shared.utils.JsonUtil;


@Component
public class NotificationPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange topicExchange;

    public NotificationPublisher(RabbitTemplate rabbitTemplate, TopicExchange topicExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchange = topicExchange;
    }

    public void publish(String routingKey, String message) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, message);
    }

    public void publish(String routingKey, Object message) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, JsonUtil.toJsonString(message));
    }
}
