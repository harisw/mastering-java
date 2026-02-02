CREATE TABLE expenses
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,
    PRIMARY KEY(id),
    user_id     BIGINT    NOT NULL,
    amount      NUMERIC NOT NULL,
    description TEXT NOT NULL,
    category_id uuid    NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);