package org.trading.ticker.description;

import jakarta.persistence.Entity;
import org.trading.ticker.common.crud.BaseSymbolEntity;


import java.util.UUID;

@Entity
public class Description extends BaseSymbolEntity {

    private String name;
    private String description;
    private String exchange;

    public Description() {
    }

    public Description(UUID id, String symbol, String name, String description, String exchange) {
        super(id, symbol);
        this.name = name;
        this.description = description;
        this.exchange = exchange;
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
