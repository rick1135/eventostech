CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE event(
    ID UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(250),
    img_url VARCHAR(100) NOT NULL,
    event_url VARCHAR(100),
    date TIMESTAMP NOT NULL,
    remote BOOLEAN NOT NULL
)