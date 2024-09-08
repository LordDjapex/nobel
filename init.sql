CREATE TABLE game (
    id BIGSERIAL PRIMARY KEY,
    is_active BOOLEAN NOT NULL,
    name VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE round (
    id BIGSERIAL PRIMARY KEY,
    player_move VARCHAR(255),
    ai_move VARCHAR(255),
    player_result VARCHAR(255),
    game_id BIGINT REFERENCES game(id),
    created_at TIMESTAMP
);

CREATE INDEX idx_game_id ON round(game_id);