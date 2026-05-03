CREATE TABLE IF NOT EXISTS daily_reports (

    id BIGSERIAL PRIMARY KEY,

    plant_id BIGINT NOT NULL REFERENCES plants(id),

    report_date DATE NOT NULL,

    notes TEXT,

    temp_tent DECIMAL(5,2),

    temp_water DECIMAL(5,2),

    humidity DECIMAL(5,2),

    ec DECIMAL(6,2),

    ppm DECIMAL(10,2),

    ph DECIMAL(4,2),

    photo_url TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT NOW(),

    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT uk_plant_report_date
        UNIQUE (plant_id, report_date)

);