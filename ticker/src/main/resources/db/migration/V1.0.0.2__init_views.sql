CREATE MATERIALIZED VIEW ticker_daily
WITH (timescaledb.continuous) AS
SELECT
    time_bucket('1 day', time) as day,
    description_id,
    first(open, time) as open,
    max(high) as high,
    min(low) as low,
    last(close, time) as close,
    sum(volume) as volume
FROM ticker_data
GROUP BY day, description_id
ORDER BY day DESC;

CREATE MATERIALIZED VIEW ticker_weekly
WITH (timescaledb.continuous) AS
SELECT
    time_bucket('1 week', time) as week,
    description_id,
    first(open, time) as open,
    max(high) as high,
    min(low) as low,
    last(close, time) as close,
    sum(volume) as volume
FROM ticker_data
GROUP BY week, description_id
ORDER BY week DESC;