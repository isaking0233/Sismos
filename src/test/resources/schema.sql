CREATE TABLE earthquake (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    spatial_event VARCHAR(255),
    magnitude DOUBLE,
    intensity VARCHAR(255),
    depth VARCHAR(255),
    time VARCHAR(255),
    latitude DOUBLE,
    longitude DOUBLE,
    entity VARCHAR(255),
    municipality VARCHAR(255),
    locality VARCHAR(255),
    record_date VARCHAR(255),
    source VARCHAR(255),
    observations VARCHAR(255),
    representation VARCHAR(255)
);
