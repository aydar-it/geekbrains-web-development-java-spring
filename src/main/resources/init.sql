DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);
INSERT INTO users (name) VALUES
('User1'),
('User2'),
('User3'),
('User4'),
('User5'),
('User6'),
('User7'),
('User8');

DROP TABLE IF EXISTS lots CASCADE;
CREATE TABLE lots
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    bet INT4 DEFAULT 0,
    user_id BIGINT REFERENCES users (id) ON DELETE CASCADE,
    version INT DEFAULT 0
);
INSERT INTO lots (name) VALUES
('Lot1'),
('Lot2'),
('Lot3'),
('Lot4');
