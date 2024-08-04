package org.trading.ticker.description;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.trading.ticker.exception.DuplicateResourceException;
import org.trading.ticker.exception.ResourceNotFound;

@Service
public class DescriptionService {

    private final DescriptionRepository descriptionRepository;

    public DescriptionService(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    public Description getByTickerSymbol(String symbol) {
        return descriptionRepository.findBySymbol(symbol).orElseThrow(() -> new ResourceNotFound("No description found for symbol " + symbol));
    }

    public Description saveDescription(String symbol, Description description) {
        description.setSymbol(symbol);
        try {
            return descriptionRepository.save(description);
        } catch (DataIntegrityViolationException e) {
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
}

