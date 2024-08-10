CREATE TABLE description(
    id UUID NOT NULL,
    symbol TEXT NOT NULL UNIQUE,
    type TEXT NOT NULL,
    name TEXT,
    description TEXT,
    exchange TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE ticker_data(
    id UUID NOT NULL,
    description_id UUID NOT NULL,
    time TIMESTAMPTZ NOT NULL,
    open DECIMAL(19, 2),
    high DECIMAL(19, 2),
    low DECIMAL(19, 2),
    close DECIMAL(19, 2),
    volume BIGINT,
    CONSTRAINT fk_stock_data_description_id FOREIGN KEY (description_id) REFERENCES description(id)
);

SELECT create_hypertable('ticker_data', 'time');