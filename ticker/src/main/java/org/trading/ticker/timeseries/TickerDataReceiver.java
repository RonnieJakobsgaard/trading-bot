package org.trading.ticker.timeseries;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.trading.api.dtos.TickerDataDTO;
import org.trading.shared.utils.JsonUtil;
import org.trading.ticker.description.DescriptionService;

import java.util.UUID;

@Component
@RabbitListener(queues = "ticker-data")
public class TickerDataReceiver {

    private final TimeSeriesService timeSeriesService;
    private final DescriptionService descriptionService;

    public TickerDataReceiver(TimeSeriesService timeSeriesService, DescriptionService descriptionService) {
        this.timeSeriesService = timeSeriesService;
        this.descriptionService = descriptionService;
    }

    @RabbitHandler
    public void receive(String in) {
        TickerDataDTO tickerDataDTO = JsonUtil.toObject(in, TickerDataDTO.class);
        UUID id = descriptionService.getBySymbol(tickerDataDTO.getSymbol()).getId();

        TickerData tickerData = new TickerData();
        tickerData.setDescriptionId(id);
        tickerData.setTime(tickerDataDTO.getTime());
        tickerData.setOpen(tickerDataDTO.getOpen());
        tickerData.setClose(tickerDataDTO.getClose());
        tickerData.setHigh(tickerDataDTO.getHigh());
        tickerData.setLow(tickerDataDTO.getLow());
        tickerData.setVolume(tickerDataDTO.getVolume());

        timeSeriesService.save(tickerData);
    }
}
