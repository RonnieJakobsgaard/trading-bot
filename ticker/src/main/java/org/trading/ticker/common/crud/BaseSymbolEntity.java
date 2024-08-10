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

    @Enumerated(EnumType.ORDINAL)
    private TickerType type;

    public BaseSymbolEntity() {
    }

    public BaseSymbolEntity(UUID id, String symbol, TickerType type) {
        this.id = id;
        this.symbol = symbol;
        this.type = type;
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

    public TickerType getType() {
        return type;
    }

    public void setType(TickerType type) {
        this.type = type;
    }
}
