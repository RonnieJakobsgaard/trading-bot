package org.trading.ticker.common.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseCrudController<T> {

    private final BaseCrudService<T> baseCrudService;

    protected BaseCrudController(BaseCrudService<T> baseCrudService) {
        this.baseCrudService = baseCrudService;
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity.ok(baseCrudService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable String symbol) {
        return ResponseEntity.ok(baseCrudService.readById(symbol));
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        return ResponseEntity.ok(baseCrudService.create(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable String id, @RequestBody T entity) {
        return ResponseEntity.ok(baseCrudService.create(baseCrudService.update(id ,entity)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        baseCrudService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
