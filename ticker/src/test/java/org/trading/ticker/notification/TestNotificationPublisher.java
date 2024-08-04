package org.trading.ticker.notification;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.trading.ticker.TestTickerApplication;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestTickerApplication.class)
public class TestNotificationPublisher {

    public static String routingKey = "test.key";

    @Mock
    private Worker workerMock = Mockito.mock(Worker.class);

    FakeNotificationReceiver fakeNotificationReceiver = new FakeNotificationReceiver(workerMock);

    @Autowired
    private NotificationPublisher notificationPublisher;

    @Test
    public void testPublish() {
        // Arrange
        String message = "test message";

        // Act
        notificationPublisher.publish(routingKey, "test message");

        // Assert
        Mockito.verify(workerMock, Mockito.times(1)).doWork(message);
    }

    static class Worker {
        public void doWork(String in) {
            // Do nothing
        }
    }

    static class FakeNotificationReceiver {

        private final Worker worker;

        public FakeNotificationReceiver(Worker worker) {
            this.worker = worker;
        }

        @RabbitListener(queues = "queue1")
        public void receiver(String in) throws InterruptedException {
            worker.doWork(in);
        }
    }


    @TestConfiguration
    static class RegistrationTestConfiguration {

        @Bean
        Queue queue1() {
            return new Queue("queue1", false);
        }

        @Bean
        Binding binding(Queue queue, TopicExchange exchange) {
            return BindingBuilder.bind(queue).to(exchange).with("*." + routingKey + ".*");
        }
    }
}
