--
-- PostgreSQL database dump
--

\restrict 3SghR4bWW7B6fuR9Wuv7LBGreycu9BFSTwhAeKFbqgn4iYG03u9ITRVLysQPU1Q

-- Dumped from database version 18.1
-- Dumped by pg_dump version 18.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: meter_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meter_category (
    id uuid NOT NULL,
    name character varying(255) NOT NULL,
    line_id uuid NOT NULL
);


ALTER TABLE public.meter_category OWNER TO postgres;

--
-- Name: meter_line; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meter_line (
    id uuid NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.meter_line OWNER TO postgres;

--
-- Name: meter_model; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meter_model (
    id uuid NOT NULL,
    name character varying(255) NOT NULL,
    category_id uuid NOT NULL
);


ALTER TABLE public.meter_model OWNER TO postgres;

--
-- Data for Name: meter_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meter_category (id, name, line_id) FROM stdin;
e10b4f9d-0c2c-45ec-b040-5367b2e8d94a	Cronos Old	5b66eccd-4c79-40c3-a339-057be02d0da9
b8f2de9a-e83f-4dcb-b042-f11131cb04df	Cronos L	5b66eccd-4c79-40c3-a339-057be02d0da9
de3a0522-f861-4ce5-8e37-4e40b81988cb	Cronos-NG	5b66eccd-4c79-40c3-a339-057be02d0da9
a3763c96-2f78-42d9-b73a-5cd8182bdfb5	Ares TB	88971889-d034-4f79-a0ca-5e444680f5f5
50496c42-187b-4dfe-9cdc-73d57efa93c2	Ares THS	88971889-d034-4f79-a0ca-5e444680f5f5
\.


--
-- Data for Name: meter_line; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meter_line (id, name) FROM stdin;
5b66eccd-4c79-40c3-a339-057be02d0da9	Cronos
88971889-d034-4f79-a0ca-5e444680f5f5	Ares
\.


--
-- Data for Name: meter_model; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meter_model (id, name, category_id) FROM stdin;
baa911be-91ab-4496-8085-fb738054b9b4	Cronos 6001-A	e10b4f9d-0c2c-45ec-b040-5367b2e8d94a
cdf02f23-c66e-4158-925e-75b2247bcf70	Cronos 6003	e10b4f9d-0c2c-45ec-b040-5367b2e8d94a
204cb2ac-e375-4e0e-843b-645ef47a06ee	Cronos 7023	e10b4f9d-0c2c-45ec-b040-5367b2e8d94a
f3c10757-4d0a-4ecb-b5b7-c0c416f283f8	Cronos 6021L	b8f2de9a-e83f-4dcb-b042-f11131cb04df
8e68193f-91f0-4eb0-8707-ac76a4a42b55	Cronos 7023L	b8f2de9a-e83f-4dcb-b042-f11131cb04df
289c50b0-8b27-4cae-b3c6-a68d1ccc9070	Cronos 6001-NG	de3a0522-f861-4ce5-8e37-4e40b81988cb
26c348b9-d27d-4ecd-bf80-69a092dbcc11	Cronos 6003-NG	de3a0522-f861-4ce5-8e37-4e40b81988cb
310266ec-71ac-4242-b2cc-5ecb01faddec	Cronos 6021-NG	de3a0522-f861-4ce5-8e37-4e40b81988cb
e117db56-8381-42e1-b2ae-7e81c43fd0a1	Cronos 6031-NG	de3a0522-f861-4ce5-8e37-4e40b81988cb
bfccefe6-8574-446d-914f-78e02186d813	Cronos 7021-NG	de3a0522-f861-4ce5-8e37-4e40b81988cb
5cb5ed84-dc38-4d70-84bd-ef545d8197bd	Cronos 7023-NG	de3a0522-f861-4ce5-8e37-4e40b81988cb
7f85fef1-163b-4d7f-8186-977c2930b151	ARES 7021	a3763c96-2f78-42d9-b73a-5cd8182bdfb5
24d6521b-a499-47dc-a594-072d55fc3fe3	ARES 7031	a3763c96-2f78-42d9-b73a-5cd8182bdfb5
d8c58e5c-9309-45bc-9f56-5e0a5db939ae	ARES 7023	a3763c96-2f78-42d9-b73a-5cd8182bdfb5
9a807d77-00aa-4f72-a96a-4199802b01b8	ARES 8023 15	50496c42-187b-4dfe-9cdc-73d57efa93c2
994bf3d2-ce71-4128-8b63-d8ceebf4a236	ARES 8023 200	50496c42-187b-4dfe-9cdc-73d57efa93c2
887e4f85-f427-43a4-8034-9dd884bb7abf	ARES 8023 2,5	50496c42-187b-4dfe-9cdc-73d57efa93c2
\.


--
-- Name: meter_category meter_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meter_category
    ADD CONSTRAINT meter_category_pkey PRIMARY KEY (id);


--
-- Name: meter_line meter_line_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meter_line
    ADD CONSTRAINT meter_line_pkey PRIMARY KEY (id);


--
-- Name: meter_model meter_model_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meter_model
    ADD CONSTRAINT meter_model_pkey PRIMARY KEY (id);


--
-- Name: meter_line uk_1wpxyo7g944v8be3jwo1qbcr2; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meter_line
    ADD CONSTRAINT uk_1wpxyo7g944v8be3jwo1qbcr2 UNIQUE (name);


--
-- Name: meter_model uk_iwv1tc61xt5fbc271hkvxr6dj; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meter_model
    ADD CONSTRAINT uk_iwv1tc61xt5fbc271hkvxr6dj UNIQUE (name);


--
-- Name: meter_category uk_s68bgfsgegy7y6clmmurgexkv; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meter_category
    ADD CONSTRAINT uk_s68bgfsgegy7y6clmmurgexkv UNIQUE (name);


--
-- Name: meter_model fkg2mndol2eywsw6up2wgw3g8sx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meter_model
    ADD CONSTRAINT fkg2mndol2eywsw6up2wgw3g8sx FOREIGN KEY (category_id) REFERENCES public.meter_category(id);


--
-- Name: meter_category fkmekm839mx1h2xwejgef236iep; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meter_category
    ADD CONSTRAINT fkmekm839mx1h2xwejgef236iep FOREIGN KEY (line_id) REFERENCES public.meter_line(id);


--
-- PostgreSQL database dump complete
--

\unrestrict 3SghR4bWW7B6fuR9Wuv7LBGreycu9BFSTwhAeKFbqgn4iYG03u9ITRVLysQPU1Q

