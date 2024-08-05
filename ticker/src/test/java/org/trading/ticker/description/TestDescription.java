package org.trading.ticker.description;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.trading.api.description.DescriptionDTO;
import org.trading.ticker.TestTickerApplication;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestTickerApplication.class)
public class TestDescription {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private DescriptionRepository descriptionRepository;

    @BeforeEach
    public void setup() {
        descriptionRepository.deleteAll();
    }

    @Test
    public void testSaveDescription() {
        // Arrange
        var description = new DescriptionDTO("IBM", "IBM", "International Business Machines Corporation", "NASDQ");

        webTestClient.post()
                .uri("/description/symbol/IBM")
                .bodyValue(description)
                .exchange()
                .expectStatus().isOk()
                .expectBody(DescriptionDTO.class)
                .value(res -> assertDescription(description, res));
    }

    @Test
    public void testGetDescription() {
        // Arrange
        var description = new DescriptionDTO("IBM", "IBM", "International Business Machines Corporation", "NASDQ");

        webTestClient.post()
                .uri("/description/symbol/IBM")
                .bodyValue(description)
                .exchange()
                .expectStatus().isOk()
                .expectBody(DescriptionDTO.class)
                .value(res -> assertDescription(description, res));

        webTestClient.get()
                .uri("/description/symbol/IBM")
                .exchange()
                .expectStatus().isOk()
                .expectBody(DescriptionDTO.class)
                .value(res -> assertDescription(description, res));
    }

    @Test
    public void testUpdateDescription() {
        // Arrange
        var description = new DescriptionDTO("IBM", "IBM", "International Business Machines Corporation", "NASDQ");

        webTestClient.post()
                .uri("/description/symbol/IBM")
                .bodyValue(description)
                .exchange()
                .expectStatus().isOk()
                .expectBody(DescriptionDTO.class)
                .value(res -> assertDescription(description, res));

        var updatedDescription = new DescriptionDTO("IBM", "IBM", "International Business Machines Corporation", "NYSE");

        webTestClient.put()
                .uri("/description/symbol/IBM")
                .bodyValue(updatedDescription)
                .exchange()
                .expectStatus().isOk()
                .expectBody(DescriptionDTO.class)
                .value(res -> assertDescription(updatedDescription, res));
    }

    @Test
    public void testDeleteDescription() {
        // Arrange
        var description = new DescriptionDTO("IBM", "IBM", "International Business Machines Corporation", "NASDQ");

        webTestClient.post()
                .uri("/description/symbol/IBM")
                .bodyValue(description)
                .exchange()
                .expectStatus().isOk()
                .expectBody(DescriptionDTO.class)
                .value(res -> assertDescription(description, res));

        webTestClient.delete()
                .uri("/description/symbol/IBM")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void testGetDescriptionNotFound() {
        // Arrange
        webTestClient.get()
                .uri("/description/symbol/IBM")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void testDeleteDescriptionNotFound() {
        // Arrange
        webTestClient.delete()
                .uri("/description/symbol/IBM")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void testUpdateDescriptionNotFound() {
        // Arrange
        var description = new DescriptionDTO("IBM", "IBM", "International Business Machines Corporation", "NASDQ");

        webTestClient.put()
                .uri("/description/symbol/IBM")
                .bodyValue(description)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void testSaveDescriptionAlreadyExists() {
        // Arrange
        var description = new DescriptionDTO("IBM", "IBM", "International Business Machines Corporation", "NASDQ");

        webTestClient.post()
                .uri("/description/symbol/IBM")
                .bodyValue(description)
                .exchange()
                .expectStatus().isOk()
                .expectBody(DescriptionDTO.class)
                .value(res -> assertDescription(description, res));

        webTestClient.post()
                .uri("/description/symbol/IBM")
                .bodyValue(description)
                .exchange()
                .expectStatus().isBadRequest();
    }

    private void assertDescription(DescriptionDTO expected, DescriptionDTO actual) {
        Assertions.assertEquals(expected.getSymbol(), actual.getSymbol());
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getDescription(), actual.getDescription());
        Assertions.assertEquals(expected.getExchange(), actual.getExchange());
    }
}
