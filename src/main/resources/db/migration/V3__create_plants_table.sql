CREATE TABLE IF NOT EXISTS plants (

    id BIGSERIAL PRIMARY KEY,

    grow_id BIGINT NOT NULL REFERENCES grows(id),

    name VARCHAR(255) NOT NULL,

    strain VARCHAR(255),

    stage VARCHAR(50) NOT NULL,

    germination_date DATE,

    notes TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT NOW(),

    updated_at TIMESTAMP NOT NULL DEFAULT NOW()

);