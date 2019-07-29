CREATE TYPE lesson15.enum AS ENUM
    ('Clients', 'Administration', 'Billing');

ALTER TYPE lesson15.enum
    OWNER TO postgres;