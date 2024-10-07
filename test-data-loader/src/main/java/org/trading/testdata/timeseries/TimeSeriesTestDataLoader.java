package org.trading.testdata.timeseries;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.trading.api.dtos.TickerDataDTO;
import org.trading.testdata.TestDataProvider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class TimeSeriesTestDataLoader implements TestDataProvider, Ordered {

    private final TickerDataProducer tickerDataProducer;

    public TimeSeriesTestDataLoader(TickerDataProducer tickerDataProducer) {
        this.tickerDataProducer = tickerDataProducer;
    }

    @Override
    public void load() throws Exception {
        List<String> resourcesPath = List.of("index", "stock");
        for(String path : resourcesPath) {
            List<String> files = getResourceFiles(path);
            for(String file : files) {
                String data = readTestDataFromFile(path + "/" + file);
                JavaTimeModule module = new JavaTimeModule();
                LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
                ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                        .modules(module)
                        .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                        .build();


                Object tickerData = objectMapper.readValue(data, new TypeReference<Object>(){});
                List<LinkedHashMap<String, Object>> tickerDataList = (List<LinkedHashMap<String, Object>>) tickerData;
                for(LinkedHashMap<String, Object> tickerDataMap : tickerDataList) {
                    TickerDataDTO tickerDataDTO = new TickerDataDTO();

                    tickerDataDTO.setSymbol(file.split(".json")[0]);
                    tickerDataDTO.setOpen((double) tickerDataMap.get("open"));
                    tickerDataDTO.setHigh((double) tickerDataMap.get("high"));
                    tickerDataDTO.setLow((double) tickerDataMap.get("low"));
                    tickerDataDTO.setClose((double) tickerDataMap.get("close"));
                    tickerDataDTO.setVolume((int) tickerDataMap.get("volume"));

                    String time = (String) tickerDataMap.get("time");
                    LocalDateTime localDateTime = LocalDateTime.parse(time + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                    tickerDataDTO.setTime(localDateTime);
                    tickerDataProducer.publish(objectMapper.writeValueAsString(tickerDataDTO));
                }
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        load();
    }

    private List<String> getResourceFiles(String path) throws Exception {
        List<String> filenames = new ArrayList<>();
        try (InputStream in = getResourceAsStream(path);
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;
            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }

        return filenames;
    }

    private InputStream getResourceAsStream(String resource) {
        final InputStream in = getClass().getClassLoader().getResourceAsStream(resource);

        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private String readTestDataFromFile(String file) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        StringBuilder data = new StringBuilder();
        try (InputStream inputStream = classLoader.getResourceAsStream(file)) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                data.append(line);
            }
        }

        return data.toString();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
