package org.trading.ticker.common.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseSymbolController<T extends BaseSymbolEntity> extends BaseCrudController<T> {

    private final BaseSymbolService<T> baseSymbolService;

    public BaseSymbolController(BaseSymbolService<T> baseSymbolService) {
        super(baseSymbolService);
        this.baseSymbolService = baseSymbolService;
    }

    @GetMapping("/symbol/{symbol}")
    public ResponseEntity<T> getBySymbol(@PathVariable String symbol) {
        return ResponseEntity.ok(baseSymbolService.getBySymbol(symbol));
    }

    @PostMapping("/symbol/{symbol}")
    public ResponseEntity<T> saveBySymbol(@PathVariable String symbol, @RequestBody T entity) {
        return ResponseEntity.ok(baseSymbolService.saveBySymbol(symbol, entity));
    }

    @PutMapping("/symbol/{symbol}")
    public ResponseEntity<T> updateBySymbol(@PathVariable String symbol, @RequestBody T entity) {
        return ResponseEntity.ok(baseSymbolService.updateBySymbol(symbol,entity));
    }

    @DeleteMapping("/symbol/{symbol}")
    public ResponseEntity<Void> deleteBySymbol(@PathVariable String symbol) {
        baseSymbolService.deleteBySymbol(symbol);
        return ResponseEntity.noContent().build();
    }
}
