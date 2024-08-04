CREATE TABLE description(
    id UUID NOT NULL,
    symbol TEXT NOT NULL UNIQUE,
    name TEXT,
    description TEXT,
    exchange TEXT,
    PRIMARY KEY (id)
);