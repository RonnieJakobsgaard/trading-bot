package org.trading.ticker.common.crud;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseCrudRepository<T> extends ListCrudRepository<T, String> {
}
