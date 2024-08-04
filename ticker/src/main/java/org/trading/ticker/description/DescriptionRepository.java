package org.trading.ticker.description;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DescriptionRepository extends CrudRepository<Description, String> {

    Optional<Description> findBySymbol(String symbol);

    void deleteBySymbol(String symbol);
}
