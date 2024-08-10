package org.trading.ticker.timeseries;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSeriesService {

    private final TimeSeriesRepository timeSeriesRepository;

    public TimeSeriesService(TimeSeriesRepository timeSeriesRepository) {
        this.timeSeriesRepository = timeSeriesRepository;
    }

    public void save(TickerData tickerData) {
        timeSeriesRepository.save(tickerData);
    }

    public List<TickerData> getAllInterval(TimeInterval interval) {
        return timeSeriesRepository.findAll();
    }
}
