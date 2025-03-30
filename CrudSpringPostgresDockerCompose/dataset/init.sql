CREATE TABLE customer (
    id INT,
    last_name TEXT,
    first_name TEXT
);

COPY customer
FROM '/docker-entrypoint-initdb.d/init.csv'
DELIMITER ','
CSV HEADER;