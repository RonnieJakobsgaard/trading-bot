package org.trading.ticker.description;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.trading.api.events.ResourceEventDTO;
import org.trading.ticker.exception.DuplicateResourceException;
import org.trading.ticker.exception.ResourceNotFound;
import org.trading.ticker.notification.NotificationPublisher;

import java.time.Instant;

@Service
public class DescriptionService {

    private final DescriptionRepository descriptionRepository;
    private final NotificationPublisher notificationPublisher;

    public DescriptionService(DescriptionRepository descriptionRepository, NotificationPublisher notificationPublisher) {
        this.descriptionRepository = descriptionRepository;
        this.notificationPublisher = notificationPublisher;
    }

    public Description getByTickerSymbol(String symbol) {
        return descriptionRepository.findBySymbol(symbol).orElseThrow(() -> new ResourceNotFound("No description found for symbol " + symbol));
    }

    public Description saveDescription(String symbol, Description description) {
        description.setSymbol(symbol);
        try {
            Description createdDescription = descriptionRepository.save(description);
            notificationPublisher.publish("description.created", createCreatedEvent(createdDescription));
            return createdDescription;
        } catch (Exception e) {
            if(e.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new DuplicateResourceException("Description already exists for symbol " + symbol);
            }

            throw e;
        }
    }

    public Description updateDescription(String symbol, Description description) {
        return descriptionRepository.findBySymbol(symbol)
                .map(d -> {
                    d.setName(description.getName());
                    d.setDescription(description.getDescription());
                    d.setExchange(description.getExchange());
                    return descriptionRepository.save(d);
                })
                .orElseThrow(() -> new ResourceNotFound("No description found for symbol " + symbol));
    }

    @Transactional
    public void deleteDescription(String symbol) {
        descriptionRepository.deleteBySymbol(symbol);
    }

    private ResourceEventDTO createCreatedEvent(Description createdDescription) {
        ResourceEventDTO event = new ResourceEventDTO();
        event.setTopic("ticker.notification");
        event.setRoutingKey("description.created");
        event.setId(createdDescription.getId().toString());
        event.setType("description");
        event.setResource("description");
        event.setMessage("Description created for symbol " + createdDescription.getSymbol());
        event.setTimestamp(Instant.now());
        return event;
    }
}

