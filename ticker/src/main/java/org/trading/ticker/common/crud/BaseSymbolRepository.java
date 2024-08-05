package org.trading.ticker.common.crud;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseSymbolRepository<T extends BaseSymbolEntity> extends BaseCrudRepository<T> {

    Optional<T> findBySymbol(String symbol);

    void deleteBySymbol(String symbol);
}
