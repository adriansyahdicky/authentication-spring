-- Table: public.data_user

-- DROP TABLE public.data_user;

CREATE TABLE IF NOT EXISTS public.data_user
(
    id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    username character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT data_user_pkey PRIMARY KEY (id)
);

insert into data_user (id, name, username, password) values
('dcc62451-da9a-4d28-bd0e-2033383c9199', 'dicky', 'adriansyah.dicky',
'$2a$10$4c.Y/9qIG1caKoe.VMjxfeRKIDNVFGMFQbPpaUAb5GpIHAADl5hCa');