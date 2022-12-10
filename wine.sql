--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4
-- Dumped by pg_dump version 14.4

-- Started on 2022-12-10 15:16:54

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
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
-- TOC entry 210 (class 1259 OID 16707)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16706)
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.category ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 212 (class 1259 OID 16713)
-- Name: image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.image (
    id integer NOT NULL,
    file_name character varying(255),
    product_id integer NOT NULL
);


ALTER TABLE public.image OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16712)
-- Name: image_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.image ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 214 (class 1259 OID 16719)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    count integer NOT NULL,
    date_time timestamp without time zone,
    number character varying(255),
    price real NOT NULL,
    status_info character varying(255),
    person_id integer NOT NULL,
    product_id integer NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16718)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.orders ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 216 (class 1259 OID 16727)
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    id integer NOT NULL,
    login character varying(100),
    password character varying(255),
    role character varying(255)
);


ALTER TABLE public.person OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16726)
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.person ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 218 (class 1259 OID 16735)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    date_time timestamp without time zone,
    description text NOT NULL,
    price real NOT NULL,
    seller text NOT NULL,
    status integer,
    title text NOT NULL,
    warehouse character varying(255) NOT NULL,
    category_id integer NOT NULL,
    minidescription text NOT NULL,
    CONSTRAINT product_price_check CHECK ((price >= (1)::double precision))
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16744)
-- Name: product_cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_cart (
    id integer NOT NULL,
    person_id integer,
    product_id integer
);


ALTER TABLE public.product_cart OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16743)
-- Name: product_cart_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.product_cart ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.product_cart_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 217 (class 1259 OID 16734)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.product ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3350 (class 0 OID 16707)
-- Dependencies: 210
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.category (id, name) VALUES (1, 'Белые вина');
INSERT INTO public.category (id, name) VALUES (2, 'Красные вина');
INSERT INTO public.category (id, name) VALUES (3, 'Игристые вина');
INSERT INTO public.category (id, name) VALUES (4, 'Подарочные корзины');


--
-- TOC entry 3352 (class 0 OID 16713)
-- Dependencies: 212
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.image (id, file_name, product_id) VALUES (4, '3fff9ab3-0144-43a0-a93d-878000503da2.0_0_cat.jpg', 3);
INSERT INTO public.image (id, file_name, product_id) VALUES (5, '18f328b0-82e8-4203-8f6d-91c38066dcb3.glass-of-wine-with-green-grapes-close-up_23-2148261648.png', 3);
INSERT INTO public.image (id, file_name, product_id) VALUES (6, 'caf0efda-59c3-4ea1-ad28-fcf5390add76.pouring-white-wine-into-the-wine-glass-on-dark-surface_114579-33941.png', 3);
INSERT INTO public.image (id, file_name, product_id) VALUES (7, '028fc430-c5d3-4f57-8b8e-487260456221.d455a9af5c218a370705a1c70a700555.png', 4);
INSERT INTO public.image (id, file_name, product_id) VALUES (8, '07c4f972-81ed-413d-b21c-87e443c6797c.8vILWufyDgc.jpg', 4);
INSERT INTO public.image (id, file_name, product_id) VALUES (9, '632fde6d-0f46-452a-a8f5-eb0f8e29abaf.9d80ca8504eefd728baaee9896e08e42.jpg', 4);
INSERT INTO public.image (id, file_name, product_id) VALUES (10, '71aa3664-a47f-4c45-8f76-af2e5fe38f49.0_0_orig.jpg', 5);
INSERT INTO public.image (id, file_name, product_id) VALUES (11, '6d874688-103f-4591-a711-f292807d4633.close-up-of-tasty-white-wine-glass_23-2148261644.png', 5);
INSERT INTO public.image (id, file_name, product_id) VALUES (12, '6b6052f8-4abc-4e2a-920f-5270b281e3c2.Beli_Binograd_Opisanie_6.png', 5);
INSERT INTO public.image (id, file_name, product_id) VALUES (13, 'f5e21dda-69e5-427d-ab70-3b956d74ebd5.0_0_prod_desktop.jpg', 6);
INSERT INTO public.image (id, file_name, product_id) VALUES (14, 'dff6ea11-f278-4218-b1c5-1ebde9de6853.1AAABC9B-7126-435F-9.jpeg', 6);
INSERT INTO public.image (id, file_name, product_id) VALUES (15, '3a95af03-4959-4139-8611-3aab7c11cd74.man-holding-red-glass-of-wine_23-2148261655.png', 6);
INSERT INTO public.image (id, file_name, product_id) VALUES (16, '281a9ec6-22e4-4be1-bd87-235692d1eb12.0_0_prod_desktop.jpg', 7);
INSERT INTO public.image (id, file_name, product_id) VALUES (17, 'ba02fb01-db8d-4b3f-b035-3fc5495168c1.a-front-view-glasses-of-wine-along-with-crisps-inside-plate-on-wooden-desk-and-dark_140725-23979.jpg', 7);
INSERT INTO public.image (id, file_name, product_id) VALUES (18, '06b9a1c8-6508-4cdb-bfcb-15736629b098.bartender-holding-glass-of-red-wine_107420-65841.png', 7);
INSERT INTO public.image (id, file_name, product_id) VALUES (19, 'e1c077c9-b255-4510-9bc8-976497843ecc.0_0_prod_desktop (1).jpg', 8);
INSERT INTO public.image (id, file_name, product_id) VALUES (20, 'd826f9e4-7a09-4bb3-b7ec-28f7bdcea56b.i.png', 8);
INSERT INTO public.image (id, file_name, product_id) VALUES (21, '127b6b8e-db9b-433c-8ecf-91bdde651366.photo56267.jpeg', 8);
INSERT INTO public.image (id, file_name, product_id) VALUES (22, 'a7c83aff-14d3-41aa-988c-72a163e98a3d.399307843a00085b50173ec15523e28e.png', 9);
INSERT INTO public.image (id, file_name, product_id) VALUES (23, '06a9a810-305a-4a58-98ab-4ff77999ed28.0_0_orig.jpg', 9);
INSERT INTO public.image (id, file_name, product_id) VALUES (24, '170e9ec8-09ad-49f8-a5d3-5e99afffeec2.igristoe-vino-canti-rose_3.png', 9);
INSERT INTO public.image (id, file_name, product_id) VALUES (25, 'bccff236-712f-4ce4-95c6-c36afd71bfe8.596a3d9dd54776d121264fe3a31b9313.png', 10);
INSERT INTO public.image (id, file_name, product_id) VALUES (26, '12fab22b-38f2-4ecd-96d9-e5b991d00e9c.champagne-types.png', 10);
INSERT INTO public.image (id, file_name, product_id) VALUES (27, '401e63ed-5b3d-42cb-bc5d-9ffa247261f9.splashing-bottle-of-champagne_23-2147992604.png', 10);
INSERT INTO public.image (id, file_name, product_id) VALUES (28, '99e2ebcb-9f4d-475b-a6b0-5dae08c2d9c9.0_0_prod_desktop (2).jpg', 11);
INSERT INTO public.image (id, file_name, product_id) VALUES (29, '5cc104af-c8fe-4e28-bd28-8fd66f3f19bb.02171222-webpnet-resizeimage-3_cover_1920x1200.jpg', 11);
INSERT INTO public.image (id, file_name, product_id) VALUES (30, '0ac935c2-1b91-4623-9654-e7f1b9a49eb7.DooEkuiXgAEXUF6.jpg', 11);
INSERT INTO public.image (id, file_name, product_id) VALUES (31, '611453c2-f99c-4d6e-9836-71a222af4b14.UT-00001174_Подарочная_Корзина_Морской_Бриз.jpg', 12);
INSERT INTO public.image (id, file_name, product_id) VALUES (32, 'b7285e57-f1ae-43f6-973c-090284526dde.UT-00001174_Подарочная_Корзина_Морской_Бриз__2_.jpg', 12);
INSERT INTO public.image (id, file_name, product_id) VALUES (33, '2987f70d-3565-4022-9fff-d9b9cee6964c.vino-bellafonte-trebbiano-spoletino-arneto-beloe-suhoe-13_-075l-ut000001385.jpg', 12);
INSERT INTO public.image (id, file_name, product_id) VALUES (34, 'dd809435-62a6-4740-9ebd-b0bf75903da4._0017_Слой_15.jpg', 13);
INSERT INTO public.image (id, file_name, product_id) VALUES (35, '06343fb3-19d8-4587-97ea-930253c08505._0016_Слой_0_копия.jpg', 13);
INSERT INTO public.image (id, file_name, product_id) VALUES (36, 'f457cfcf-b9e4-46f2-9a7f-f517053cdaec.vino-iberica-charming-vorld-kriansa-krasnoe-suhoe-2016-gu-135_-075-l-UT-00000041.jpg', 13);
INSERT INTO public.image (id, file_name, product_id) VALUES (37, '91a5dc6c-cc26-45dd-a1e1-d9af13593bd1.UT-00001177_Подарочная_Корзина_Итальянская_Сказка.jpg', 14);
INSERT INTO public.image (id, file_name, product_id) VALUES (38, '4fa1a3e2-6fc7-4a14-9d53-28df0e1aad74.UT-00001177_Подарочная_Корзина_Итальянская_Сказка__2_.jpg', 14);
INSERT INTO public.image (id, file_name, product_id) VALUES (39, '1548bcdf-e083-41f2-b675-755549d85f8c.vino-agricolle-gussalli-beretta-kastello-di-radda-granbruno-krasnoe-suhoe-2018-gu-135_-075-l-UT-.jpg', 14);


--
-- TOC entry 3354 (class 0 OID 16719)
-- Dependencies: 214
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders (id, count, date_time, number, price, status_info, person_id, product_id) VALUES (1, 1, '2022-12-10 13:44:00', '5852da4a-da03-4689-a60a-cde41632b235', 1690, 'Получен', 3, 4);


--
-- TOC entry 3356 (class 0 OID 16727)
-- Dependencies: 216
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.person (id, login, password, role) VALUES (1, 'admin', '$2a$10$.khyvEZ1CtVa1kuuQ2S76OF8NB7n4fI78mWNL2g1jr1r9HWEcFvQe', 'ROLE_ADMIN');
INSERT INTO public.person (id, login, password, role) VALUES (2, 'first_user', '$2a$10$6.45QEuAwNa./MyI05KtSe3dAYFolsFrgPfvBvunXqFhWZz.CnEPq', 'ROLE_USER');
INSERT INTO public.person (id, login, password, role) VALUES (3, 'second_user', '$2a$10$t3s4KPstyNhj2Jhqx.43g.5xmoHPZWjnoc0biNtwWFFTEJVnOEYp2', 'ROLE_USER');


--
-- TOC entry 3358 (class 0 OID 16735)
-- Dependencies: 218
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product (id, date_time, description, price, seller, status, title, warehouse, category_id, minidescription) VALUES (3, '2022-12-10 13:12:14.902623', '"Hoffman" Sauvignon Blanc — элегантное белое сухое вино, созданное из винограда сорта Совиньон Блан, который произрастает на виноградниках компании в регионе Мурсия. Сбор урожая проводится по достижении ягодами оптимальной спелости, на винодельне плоды подвергаются мягкому прессованию с последующей ферментацией со строгим температурным контролем. Вино является отличным аперитивом, а также гармонично сочетается с морепродуктами, блюдами из рыбы и легкими салатами.', 839, 'Сеть винных магазинов', NULL, 'Вино "Hoffman" Sauvignon Blanc, 2021', 'Москва', 1, 'Италия, белое, сухое, 0.75 л.');
INSERT INTO public.product (id, date_time, description, price, seller, status, title, warehouse, category_id, minidescription) VALUES (5, '2022-12-10 13:39:31.690036', '"Gabia" Vinho Verde DOC — изысканное белое полусухое вино, изготовленное из винограда сортов Лоурейру и Трейщадура. Лозы выращиваются в Лиме, в суб-регионе Виньо Верде. Виноградник площадью более 20 га имеет южную экспозицию. Урожай собирается вручную во второй половине сентября. После мацерации с виноградной кожицей муст прессуется. Полученный сок декантируется, ферментируется при контролируемой температуре, выдерживается в стальных чанах в течение 2 месяцев, фильтруется и разливается по бутылкам. ', 896, 'Сеть винных магазинов', NULL, 'Вино "Gabia" Vinho Verde DOC', 'Москва', 1, 'Португалия, белое, сухое, 0.75 л.');
INSERT INTO public.product (id, date_time, description, price, seller, status, title, warehouse, category_id, minidescription) VALUES (6, NULL, 'Cantine Risveglio, "Simposio" Primitivo, Salento IGT — красное полусухое вино, изготовленное из винограда сорта Примитиво, который произрастает на виноградниках компании в Саленто. Средний возраст виноградных лоз составляет 20-25 лет. Сбор урожая осуществляется вручную и механически по достижении ягодами оптимального уровня спелости. На винодельне плоды подвергаются бережному измельчению с последующей мацерацией на кожице в течение 6-7 дней. Ферментация проводится при контролируемой температуре 25-26 °C. Выдерживается вино в барриках на протяжении 4 месяцев', 980, 'Сеть винных магазинов', NULL, 'Вино "Simposio" Primitivo, Salento IGT, 2018', 'Москва', 2, ' Италия, красное, сухое, 0.75 л.');
INSERT INTO public.product (id, date_time, description, price, seller, status, title, warehouse, category_id, minidescription) VALUES (7, NULL, 'Дегустационные характеристики:
Это светло-рубиновое в цвете вино раскрывается в бокале ароматом свежей клубники и легким перечным тоном. Среднетелое и фруктовое во вкусе, с легкими ванильными оттенками в послевкусии.', 1290, 'Simple', NULL, 'Вино Carolina  Santa Carolina, 2020 г.', 'Москва', 2, 'Чили, красное, сухое, 0.75 л.');
INSERT INTO public.product (id, date_time, description, price, seller, status, title, warehouse, category_id, minidescription) VALUES (4, NULL, '"Medusa" Viognier — белое сухое вино премиум класса из винограда сорта Вионье, выращенного на виноградниках Лангедока. Винификация ягод, собранных вручную в стадии оптимальной спелости и отсортированных также вручную, проходит традиционным способом в стальных танкерах при контролируемой температуре. Вино обладает прозрачным светло-золотистым цветом, элегантным цветочно-фруктовым ароматом и освежающим фруктовым вкусом с чистым, свежим послевкусием. Вионье рекомендуется сочетать с блюдами из белого мяса и рыбы, морепродуктами, ризотто, грибами, сырами и пастой со сливочным соусом. ', 1690, 'Simple', NULL, 'Вино Medusa Albarino, 2021 г.', 'Москва', 1, 'Испания, белое, сухое, 0.75 л.');
INSERT INTO public.product (id, date_time, description, price, seller, status, title, warehouse, category_id, minidescription) VALUES (8, '2022-12-10 14:05:45.925029', '"Sena" — чилийское красное вино, произведенное из купажа 5 сортов отборного винограда, выращенного в регионе Аконкагуа. Урожай собирают вручную в небольшие коробки, затем дважды сортируют. Винификация проводится для каждого сорта по отдельности. Ферментируется сусло в стальных резервуарах при контролируемой температуре 24-30 °С, с ежедневной перекачкой. Для сортов Каберне Совиньон, Каберне Фран, Мальбек и Карменере ферментация длится около 19-25 дней, а для Пти Вердо — около 6-8 суток. Выдержка вина длится в течение 22 месяцев в новых бочках, сделанных из французского дуба, где также проходит яблочно-молочное брожение. ', 33055, 'Simple', NULL, 'Вино Sena, Vina Sena', 'Москва', 2, 'Чили, красное, сухое, 0.75 л.');
INSERT INTO public.product (id, date_time, description, price, seller, status, title, warehouse, category_id, minidescription) VALUES (9, '2022-12-10 14:10:10.169039', 'Canti, Rose Extra Dry — изысканное розовое сухое игристое вино, созданное из винограда сортов Глера и Пино Нуар. Ягоды произрастают на виноградниках компании на глинисто-известняковых почвах. Вино идеально в качестве аперитива, оно чудесно сочетается с различными блюдами из рыбы, моллюсков и других морепродуктов. ', 1340, 'Simple', NULL, 'Игристое вино Rose Extra Dry, Canti', 'Питер', 3, 'Италия, розовое, сухое, 0.75 л.');
INSERT INTO public.product (id, date_time, description, price, seller, status, title, warehouse, category_id, minidescription) VALUES (11, NULL, 'Шампанское Moet & Chandon, "Nectar Imperial" — гармоничный купаж трех виноградных сортов: Пино Нуар (40-50%), Пино Менье (30-40%) и Шардоне (10-20%). В купаж также добавляется 20-30% резервных вин. В приятном вкусе этого роскошного "нектара" сочетаются тона экзотических фруктов, сладостей и оттенки пряностей. Шампанское отлично подойдет к рыбе и белому мясу в кремовом соусе. ', 9957, 'Сеть винных магазинов', NULL, 'Шампанское Moet & Chandon,', 'Питер', 3, ' Франция, белое, полусладкое, 0.75 л.');
INSERT INTO public.product (id, date_time, description, price, seller, status, title, warehouse, category_id, minidescription) VALUES (10, NULL, 'Дегустационные характеристики:
Светло-соломенный цвет с золотистым оттенком, завораживающий перляж. Чувственный аромат с тонами фундука, меда, цитрусовых, выпечка и белых цветов. Свежее, утонченное вино демонстрирует изысканность шардоне и силу пино нуара в элегантном вкусе и длительном минеральным послевкусием.
', 3290, 'Simple', NULL, 'Игристое вино Cuvee Blanc Brut, 2019 г.', 'Питер', 3, 'Италия, белое, сухое, 0.75 л.');
INSERT INTO public.product (id, date_time, description, price, seller, status, title, warehouse, category_id, minidescription) VALUES (12, '2022-12-10 15:06:57.907276', 'Специально для любителей морепродуктов мы подготовили ризотто с морепродуктами, рис арборию для почитателей паэльи, консервированный тунец, оливковое масло, маслины, томатный соус по деревенски, бальзамический соус крем и добавили бутылочку изысканного белого сухого вина. Упаковали все в плетеную корзину ручной работы, обтянули прозрачной пленкой с подарочным дизайном.', 6706, 'Amelia', NULL, 'Подарочная Корзина Морской Бриз', 'Москва', 4, 'Подарочные корзины для женщин');
INSERT INTO public.product (id, date_time, description, price, seller, status, title, warehouse, category_id, minidescription) VALUES (13, '2022-12-10 15:09:53.698024', 'Белое и красное вино, органические греческие оливки, фаршированные тунцом испанские оливки, артишоки обжаренные на гриле в подсолнечном масле, маринованные мини шампиньоны, очищенные резанные томаты в томатном соке, соус песто, позволят быстро приготовить изысканные блюда для новогоднего стола, и будет желанным подарком для любой хозяйки. Все товары бережно сложены в коричневую плетеную корзину, обтянутую прозрачной пленкой и укарашенную в подарочном стиле.', 5302, 'Amelia', NULL, 'Подарочная Корзина Новогодний Стол', 'Москва', 4, 'Подарочные корзины с алкоголем');
INSERT INTO public.product (id, date_time, description, price, seller, status, title, warehouse, category_id, minidescription) VALUES (14, '2022-12-10 15:12:55.237399', 'Красное сухое вино, рис, красная консервированная фасоль, макаронные изделия феттучини, консервированные томаты черри в томатном соке, премиальные оливки и оливковое масло, в плетеной корзине, замечательная возможность приготовить всеми любимые итальянские блюда.', 4590, 'Amelia', NULL, 'Подарочная Корзина Итальянская Сказка', 'Москва', 4, 'Подарочные корзины для женщин');


--
-- TOC entry 3360 (class 0 OID 16744)
-- Dependencies: 220
-- Data for Name: product_cart; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3366 (class 0 OID 0)
-- Dependencies: 209
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_seq', 4, true);


--
-- TOC entry 3367 (class 0 OID 0)
-- Dependencies: 211
-- Name: image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.image_id_seq', 39, true);


--
-- TOC entry 3368 (class 0 OID 0)
-- Dependencies: 213
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 1, true);


--
-- TOC entry 3369 (class 0 OID 0)
-- Dependencies: 215
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_id_seq', 3, true);


--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 219
-- Name: product_cart_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_cart_id_seq', 1, true);


--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 217
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 14, true);


--
-- TOC entry 3191 (class 2606 OID 16711)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 3193 (class 2606 OID 16717)
-- Name: image image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);


--
-- TOC entry 3195 (class 2606 OID 16725)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 3197 (class 2606 OID 16733)
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- TOC entry 3203 (class 2606 OID 16748)
-- Name: product_cart product_cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT product_cart_pkey PRIMARY KEY (id);


--
-- TOC entry 3199 (class 2606 OID 16742)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 3201 (class 2606 OID 16750)
-- Name: product uk_qka6vxqdy1dprtqnx9trdd47c; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT uk_qka6vxqdy1dprtqnx9trdd47c UNIQUE (title);


--
-- TOC entry 3205 (class 2606 OID 16756)
-- Name: orders fk1b0m4muwx1t377w9if3w6wwqn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk1b0m4muwx1t377w9if3w6wwqn FOREIGN KEY (person_id) REFERENCES public.person(id);


--
-- TOC entry 3207 (class 2606 OID 16766)
-- Name: product fk1mtsbur82frn64de7balymq9s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- TOC entry 3206 (class 2606 OID 16761)
-- Name: orders fk787ibr3guwp6xobrpbofnv7le; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk787ibr3guwp6xobrpbofnv7le FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3204 (class 2606 OID 16751)
-- Name: image fkgpextbyee3uk9u6o2381m7ft1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT fkgpextbyee3uk9u6o2381m7ft1 FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3209 (class 2606 OID 16776)
-- Name: product_cart fkhpnrxdy3jhujameyod08ilvvw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT fkhpnrxdy3jhujameyod08ilvvw FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3208 (class 2606 OID 16771)
-- Name: product_cart fksgnkc1ko2i1o9yr2p63ysq3rn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT fksgnkc1ko2i1o9yr2p63ysq3rn FOREIGN KEY (person_id) REFERENCES public.person(id);


-- Completed on 2022-12-10 15:16:55

--
-- PostgreSQL database dump complete
--

