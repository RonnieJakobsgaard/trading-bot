package org.trading.testdata.description;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.trading.api.dtos.DescriptionDTO;
import org.trading.shared.utils.JsonUtil;
import org.trading.testdata.TestDataProvider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class DescriptionTestDataLoader implements TestDataProvider, Ordered {

    private final RestClient restClient;

    public DescriptionTestDataLoader() {
        restClient = RestClient.builder().baseUrl("http://localhost:8090").build();
    }

    @Override
    public void load() throws Exception {
        List<String> files = getResourceFiles("description");
        for (String file : files) {
            String data = readTestDataFromFile("description/" + file);
            DescriptionDTO descriptionDTO = JsonUtil.toObject(data, DescriptionDTO.class);
            System.out.println(descriptionDTO.getName());
            restClient.post()
                    .uri("http://localhost:8090/ticker/description/symbol/" + descriptionDTO.getSymbol())
                    .body(descriptionDTO)
                    .retrieve()
                    .toBodilessEntity();
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
        return 0;
    }
}
