CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       first_name VARCHAR(100),
                       last_name VARCHAR(100),
                       email VARCHAR(150) UNIQUE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);