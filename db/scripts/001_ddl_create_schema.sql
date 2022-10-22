CREATE TABLE IF NOT EXISTS category(
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE IF NOT EXISTS body(
    id SERIAL PRIMARY KEY,
    type TEXT,
    category_id INT NOT NULL REFERENCES category(id)
);

CREATE TABLE IF NOT EXISTS brand(
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE IF NOT EXISTS model(
    id SERIAL PRIMARY KEY,
    name TEXT,
    brand_id INT NOT NULL REFERENCES brand(id)
);

CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT UNIQUE,
    password TEXT
);

CREATE TABLE IF NOT EXISTS engine(
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE IF NOT EXISTS car(
    id SERIAL PRIMARY KEY,
    engine_id INT NOT NULL REFERENCES engine(id),
    brand_id INT NOT NULL REFERENCES brand(id),
    model_id INT NOT NULL REFERENCES model(id),
    body_id INT NOT NULL REFERENCES body(id),
    category_id INT NOT NULL REFERENCES category(id)
);

CREATE TABLE IF NOT EXISTS post(
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    car_id INT NOT NULL REFERENCES car(id),
    photo BYTEA,
    sold BOOLEAN,
    newCar BOOLEAN,
    user_id INT NOT NULL REFERENCES users(id),
    type TEXT
);

