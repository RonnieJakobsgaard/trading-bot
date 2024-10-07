package org.trading.ticker.common.crud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseSymbolRepository<T extends BaseSymbolEntity> extends BaseCrudRepository<T> {

    Optional<T> findBySymbol(String symbol);

    @Query("SELECT d.id FROM Description d WHERE d.symbol = ?1")
    Optional<UUID> findIdBySymbol(String symbol);

    void deleteBySymbol(String symbol);
}
