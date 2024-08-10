package org.trading.ticker.timeseries;

import org.springframework.data.repository.ListCrudRepository;


public interface TimeSeriesRepository extends ListCrudRepository<TickerData, String> {
}
