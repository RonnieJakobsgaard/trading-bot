package org.trading.ticker.common.crud;

import org.trading.ticker.common.exception.ResourceNotFound;

import java.util.List;

public abstract class BaseCrudService<T> {

    private final BaseCrudRepository<T> repository;

    public BaseCrudService(BaseCrudRepository<T> repository) {
        this.repository = repository;
    }

    // Create
    public T create(T entity) {
        return repository.save(entity);
    }

    public List<T> create(List<T> entities) {
        return repository.saveAll(entities);
    }

    // Update
    public T update(String id, T data) {
        if(repository.existsById(id)) {
            return repository.save(data);
        } else {
            throw new ResourceNotFound(id);
        }
    }

    // Read
    public T readById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFound(id));
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    // Delete
    public void deleteById(String id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFound(id);
        }
    }
}
