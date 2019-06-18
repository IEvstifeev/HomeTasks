CREATE TABLE lesson15."ROLE"
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name lesson15."enum" NOT NULL,
    description text COLLATE pg_catalog."default",
    CONSTRAINT "ROLE_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE lesson15."ROLE"
    OWNER to postgres;