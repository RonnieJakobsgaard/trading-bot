package org.trading.ticker.common.crud;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseSymbolEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String symbol;

    public BaseSymbolEntity() {
    }

    public BaseSymbolEntity(UUID id, String symbol) {
        this.id = id;
        this.symbol = symbol;
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
}
