package org.trading.ticker.description;

import org.springframework.stereotype.Service;
import org.trading.api.events.ResourceEventDTO;
import org.trading.ticker.common.crud.BaseSymbolService;
import org.trading.ticker.notification.NotificationPublisher;

import java.time.Instant;

@Service
public class DescriptionService extends BaseSymbolService<Description> {

    public DescriptionService(DescriptionRepository descriptionRepository, NotificationPublisher notificationPublisher) {
        super(descriptionRepository, notificationPublisher);
    }

    @Override
    public ResourceEventDTO createCreatedEvent(Description createdEntity) {
        ResourceEventDTO event = new ResourceEventDTO();
        event.setTopic("ticker.notification");
        event.setRoutingKey("description.created");
        event.setId(createdEntity.getId().toString());
        event.setType("description");
        event.setResource("description");
        event.setMessage("Description created for symbol " + createdEntity.getSymbol());
        event.setTimestamp(Instant.now());
        return event;
    }

    @Override
    public ResourceEventDTO createUpdatedEvent(Description updatedEntity) {
        ResourceEventDTO event = new ResourceEventDTO();
        event.setTopic("ticker.notification");
        event.setRoutingKey("description.updated");
        event.setId(updatedEntity.getId().toString());
        event.setType("description");
        event.setResource("description");
        event.setMessage("Description created for symbol " + updatedEntity.getSymbol());
        event.setTimestamp(Instant.now());
        return event;
    }

    @Override
    public ResourceEventDTO createDeletedEvent(Description deletedEntity) {
        ResourceEventDTO event = new ResourceEventDTO();
        event.setTopic("ticker.notification");
        event.setRoutingKey("description.deleted");
        event.setId(deletedEntity.getId().toString());
        event.setType("description");
        event.setResource("description");
        event.setMessage("Description created for symbol " + deletedEntity.getSymbol());
        event.setTimestamp(Instant.now());
        return event;
    }
}

