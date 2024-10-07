package org.trading.ticker.common.crud;

import jakarta.transaction.Transactional;
import org.trading.api.dtos.ResourceEventDTO;
import org.trading.ticker.common.exception.DuplicateResourceException;
import org.trading.ticker.common.exception.ResourceNotFound;
import org.trading.ticker.notification.NotificationPublisher;

import java.util.UUID;

public abstract class BaseSymbolService<T extends BaseSymbolEntity> extends BaseCrudService<T> {

    private final BaseSymbolRepository<T> baseSymbolRepository;
    private final NotificationPublisher notificationPublisher;

    public BaseSymbolService(BaseSymbolRepository<T> baseSymbolRepository, NotificationPublisher notificationPublisher) {
        super(baseSymbolRepository);
        this.baseSymbolRepository = baseSymbolRepository;
        this.notificationPublisher = notificationPublisher;
    }

    public T getBySymbol(String symbol) {
        return baseSymbolRepository.findBySymbol(symbol).orElseThrow(() -> new ResourceNotFound("No description found for symbol " + symbol));
    }

    public UUID getIdBySymbol(String symbol) {
        return baseSymbolRepository.findIdBySymbol(symbol).orElseThrow(() -> new ResourceNotFound("No description found for symbol " + symbol));
    }

    public T saveBySymbol(String symbol, T entity) {
        entity.setSymbol(symbol);
        try {
            T createdEntity = baseSymbolRepository.save(entity);
            notificationPublisher.publish("description.created", createCreatedEvent(entity));
            return createdEntity;
        } catch (Exception e) {
            if(e.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new DuplicateResourceException("Description already exists for symbol " + symbol);
            }

            throw e;
        }
    }

    public T updateBySymbol(String symbol, T entity) {
        return baseSymbolRepository.findBySymbol(symbol)
                .map(d -> {
                    entity.setId(d.getId());
                    T updatedEntity = baseSymbolRepository.save(entity);
                    notificationPublisher.publish("description.updated", createUpdatedEvent(updatedEntity));
                    return updatedEntity;
                })
                .orElseThrow(() -> new ResourceNotFound("No description found for symbol " + symbol));
    }

    @Transactional
    public void deleteBySymbol(String symbol) {
        baseSymbolRepository.findBySymbol(symbol).map(s -> {
            baseSymbolRepository.delete(s);
            notificationPublisher.publish("description.deleted", createDeletedEvent(s));
            return s;
        });
    }

    public abstract ResourceEventDTO createCreatedEvent(T createdEntity);
    public abstract ResourceEventDTO createUpdatedEvent(T updatedEntity);
    public abstract ResourceEventDTO createDeletedEvent(T deletedEntity);
}
