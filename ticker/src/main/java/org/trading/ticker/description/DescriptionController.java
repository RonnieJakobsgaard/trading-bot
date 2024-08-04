package org.trading.ticker.description;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trading.api.description.DescriptionDTO;

@RestController
@RequestMapping("/description")
class DescriptionController {

    private final DescriptionService descriptionService;

    public DescriptionController(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<DescriptionDTO> getByTickerSymbol(@PathVariable String symbol) {
        return ResponseEntity.ok(descriptionService.getByTickerSymbol(symbol).toDTO());
    }

    @PostMapping("/{symbol}")
    public ResponseEntity<DescriptionDTO> saveDescription(@PathVariable String symbol, @RequestBody DescriptionDTO description) {
        return ResponseEntity.ok(descriptionService.saveDescription(symbol, Description.of(description)).toDTO());
    }

    @PutMapping("/{symbol}")
    public ResponseEntity<DescriptionDTO> updateDescription(@PathVariable String symbol, @RequestBody DescriptionDTO description) {
        return ResponseEntity.ok(descriptionService.updateDescription(symbol, Description.of(description)).toDTO());
    }

    @DeleteMapping("/{symbol}")
    public ResponseEntity<Void> deleteDescription(@PathVariable String symbol) {
        descriptionService.deleteDescription(symbol);
        return ResponseEntity.noContent().build();
    }
}
