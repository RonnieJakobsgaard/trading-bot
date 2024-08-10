package org.trading.ticker.timeseries;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class TimeSeriesController {

    private final TimeSeriesService timeSeriesService;

    public TimeSeriesController(TimeSeriesService timeSeriesService) {
        this.timeSeriesService = timeSeriesService;
    }

    @GetMapping("/{interval}")
    public List getAllInterval(@PathVariable TimeInterval interval) {
        return timeSeriesService.getAllInterval(interval);
    }
}
