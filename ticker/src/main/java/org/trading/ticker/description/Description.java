package org.trading.ticker.description;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.trading.api.description.DescriptionDTO;


import java.util.UUID;

@Entity
public class Description {

    @Id
    @GeneratedValue
    private UUID id;
    private String symbol;
    private String name;
    private String description;
    private String exchange;

    public static Description of(DescriptionDTO descriptionDTO) {
        return new Description(null, descriptionDTO.getSymbol(), descriptionDTO.getName(), descriptionDTO.getDescription(), descriptionDTO.getExchange());
    }

    public static DescriptionDTO toDTO(Description description) {
        return new DescriptionDTO(description.getSymbol(), description.getName(), description.getDescription(), description.getExchange());
    }

    public DescriptionDTO toDTO() {
        return new DescriptionDTO(this.symbol, this.name, this.description, this.exchange);
    }

    public Description() {
    }

    public Description(UUID id, String symbol, String name, String description, String exchange) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.description = description;
        this.exchange = exchange;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
