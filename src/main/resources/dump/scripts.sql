
-- DROP TABLE IF EXISTS public.role;

CREATE TABLE IF NOT EXISTS public.role
(
    role_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 1000000 CACHE 1 ),
    name character varying(300) COLLATE pg_catalog."default" NOT NULL,
    enabled boolean NOT NULL DEFAULT true,
    creation_at timestamp with time zone NOT NULL,
    modification_at timestamp with time zone NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (role_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.role
    OWNER to postgres;
}

-- Table: public.role_user

-- DROP TABLE IF EXISTS public.role_user;

CREATE TABLE IF NOT EXISTS public.role_user
(
    role_user_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 1000000 CACHE 1 ),
    role_id integer NOT NULL,
    user_id integer NOT NULL,
    enabled boolean NOT NULL DEFAULT true,
    creation_at timestamp with time zone NOT NULL,
    modification_at timestamp with time zone NOT NULL,
    CONSTRAINT role_user_pkey PRIMARY KEY (role_user_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.role_user
    OWNER to postgres;

-- Table: public.user

-- DROP TABLE IF EXISTS public."user";

CREATE TABLE IF NOT EXISTS public."user"
(
    user_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 1000000 CACHE 1 ),
    name character varying(300) COLLATE pg_catalog."default",
    lastname character varying(300) COLLATE pg_catalog."default",
    username character varying(300) COLLATE pg_catalog."default",
    email character varying(300) COLLATE pg_catalog."default",
    password character varying(300) COLLATE pg_catalog."default",
    age integer,
    phone bigint,
    enabled boolean DEFAULT true,
    creation_at timestamp with time zone,
    modification_at timestamp with time zone,
    CONSTRAINT user_pkey PRIMARY KEY (user_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."user"
    OWNER to postgres;

INSERT INTO public.role(name, enabled, creation_at, modification_at)
VALUES ('ROLE_ADMIN', true, NOW(), NOW());

INSERT INTO public.role(name, enabled, creation_at, modification_at)
VALUES ('ROLE_USER', true, NOW(), NOW());

INSERT INTO public.role(name, enabled, creation_at, modification_at)
VALUES ('ROLE_DEFAULT', true, NOW(), NOW());

INSERT INTO public.role_user(role_id, user_id, enabled, creation_at, modification_at)
VALUES (1, 1, true, NOW(), NOW());

INSERT INTO public.role_user(role_id, user_id, enabled, creation_at, modification_at)
VALUES (1, 1, true, NOW(), NOW());

INSERT INTO public.role_user(role_id, user_id, enabled, creation_at, modification_at)
VALUES (2, 1, true, NOW(), NOW());

INSERT INTO public.role_user(role_id, user_id, enabled, creation_at, modification_at)
VALUES (3, 1, true, NOW(), NOW());

SELECT user_id, name, lastname, username, email, password, age, phone, enabled, creation_at, modification_at
FROM public."user";


SELECT user_id, name, lastname, username, email, password, age, phone, enabled, creation_at, modification_at
FROM public."user";

UPDATE public.role
SET name = 'ROLE_ADMIN'
WHERE role_id = 1

UPDATE public.role
SET name = 'ROLE_USER'
WHERE role_id = 2

UPDATE public.role
SET name = 'ROLE_DEFAULT'
WHERE role_id = 3

SELECT role_user_id, role_id, user_id, enabled, creation_at, modification_at
FROM public.role_user;

INSERT INTO public.role_user(
role_id, user_id, enabled, creation_at, modification_at)
VALUES (1, 1, true, now(), now());
INSERT INTO public.role_user(
role_id, user_id, enabled, creation_at, modification_at)
VALUES (2, 1, true, now(), now());
INSERT INTO public.role_user(
role_id, user_id, enabled, creation_at, modification_at)
VALUES (3, 1, true, now(), now());
