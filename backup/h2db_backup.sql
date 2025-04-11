-- H2 2.2.224;
;             
CREATE USER IF NOT EXISTS "SA" SALT '5eb0458644a0368b' HASH 'f11a264147ec81bfa9e1ae5184f477b0964da1142107742911120bd540ad8319' ADMIN;         
CREATE CACHED TABLE "PUBLIC"."USERTABLE"(
    "ID" INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 7) NOT NULL,
    "USERNAME" CHARACTER VARYING(50) NOT NULL,
    "PASSWORD" CHARACTER VARYING(255) NOT NULL,
    "EMAIL" CHARACTER VARYING(100) NOT NULL,
    "ROLE" CHARACTER VARYING(50) DEFAULT 'USER'
);       
ALTER TABLE "PUBLIC"."USERTABLE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_B" PRIMARY KEY("ID");    
-- 6 +/- SELECT COUNT(*) FROM PUBLIC.USERTABLE;               
INSERT INTO "PUBLIC"."USERTABLE" VALUES
(1, 'hamitmizrak', '$2a$10$Mi37ww9dSBWt0ENrRQu0DeDlWVzBZ.DV4sqpXOQwxqPXnuHIF9.9.', 'hamitmizrak@gmail.com', 'USER'),
(2, 'admin', '$2a$10$9dAGY0D9t.I1QiJlEiRML.zrZAlIvJRNMgpHgbikZ87s3GAzHmuZO', 'admin@gmail.com', 'ADMIN'),
(3, 'root', '$2a$10$itSGnqs18UMQgGYxThPA7On2XaQzkBnSR9pSyL5j4GEraTEJK4hcy', 'root', 'ADMIN'),
(4, 'waddaw', '$2a$10$apGZarn/wisB1ypUBpn5P.Da7nbqM423.vOC5ZH9KVLPlnwEKEx/e', U&'jdqwj\0131wq@gmail.com', 'USER'),
(5, U&'j\0131adwj', '$2a$10$9ggA1alk1R9dpm/rb7kQJO6wCDl8TtaCs9DzBxhE8nmFJA.eWcOYi', U&'djowa\0131kqwdoq@gmail.com', 'USER'),
(6, 'okanks', '$2a$10$S6gw5IFOvu4KpJW2Vocy6.3NHPxanLFcTQR/9iq3B5lZVwhr19kw.', 'okanks@gmail.com', 'MODERATOR');    
CREATE CACHED TABLE "PUBLIC"."KDV_TABLE"(
    "ID" INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "AMOUNT" DOUBLE PRECISION NOT NULL,
    "KDVRATE" DOUBLE PRECISION NOT NULL,
    "KDVAMOUNT" DOUBLE PRECISION NOT NULL,
    "TOTALAMOUNT" DOUBLE PRECISION NOT NULL,
    "RECEIPTNUMBER" CHARACTER VARYING(100) NOT NULL,
    "TRANSACTIONDATE" DATE NOT NULL,
    "DESCRIPTION" CHARACTER VARYING(255),
    "EXPORTFORMAT" CHARACTER VARYING(50)
);        
ALTER TABLE "PUBLIC"."KDV_TABLE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_7" PRIMARY KEY("ID");    
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.KDV_TABLE;               
ALTER TABLE "PUBLIC"."USERTABLE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_BB" UNIQUE("USERNAME");  
ALTER TABLE "PUBLIC"."USERTABLE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_BB7" UNIQUE("EMAIL");    
