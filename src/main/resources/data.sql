insert into user(id, username, password)
values
    (1, 'user', '$2a$12$h0HcS2QDb/7zPASbLa2GoOTSRP6CWK0oX7pCK.dPjkM6L5N4pNovi'), -- password = user
    (2, 'admin', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG'), -- password = admin
    (3, 'deleter', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG'); -- password = admin
insert into authority (id, authority_name)
values
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER'),
    (3, 'ROLE_DELETER');
insert into user_authority (user_id, authority_id)
values
    (1, 2),
    (2, 1),
    (3, 3);

DELETE FROM HARDWARE;
DELETE FROM REVIEWS;

INSERT INTO HARDWARE (ID, CODE, HARDWARE_NAME, PRICE, HARDWARE_TYPE, STOCK)
VALUES(1, 'AAA', 'INTEL PENTIUM GOLD', 533.07, 'CPU', 25);
INSERT INTO HARDWARE (ID, CODE, HARDWARE_NAME, PRICE, HARDWARE_TYPE, STOCK)
VALUES(2, 'AAB', 'INTEL CORE I3', 969.00, 'CPU', 500);
INSERT INTO HARDWARE (ID, CODE, HARDWARE_NAME, PRICE, HARDWARE_TYPE, STOCK)
VALUES(3, 'AAC', 'INTEL CORE I3',1234.00, 'CPU', 25);
INSERT INTO HARDWARE (ID, CODE, HARDWARE_NAME, PRICE, HARDWARE_TYPE, STOCK)
VALUES(4, 'ABA', 'KINGSTON FURY BEAST', 1999.0, 'LAPTOP', 320);
INSERT INTO HARDWARE (ID, CODE, HARDWARE_NAME, PRICE, HARDWARE_TYPE, STOCK)
VALUES(5, 'ABB', 'ASUS E210MA-GJ208TS', 1899.0, 'LAPTOP', 120);
INSERT INTO HARDWARE (ID, CODE, HARDWARE_NAME, PRICE, HARDWARE_TYPE, STOCK)
VALUES(6, 'ABC', 'ACER ASPIRE 3',  2649.0, 'LAPTOP', 150);
INSERT INTO HARDWARE (ID, CODE, HARDWARE_NAME, PRICE, HARDWARE_TYPE, STOCK)
VALUES(7, 'ACA','ACER SWIFT 1',2699.0, 'LAPTOP', 90);

INSERT INTO REVIEWS (ID, RATING, TITLE, TEXT, HARDWARE_ID)
VALUES(1, 'FIVE_STAR', 'SAVRŠENO ZA ROBLOX', 'NAPOKON MOGU UŽIVATI U ROBLOXU U 1440P KVALITETI', 1);
INSERT INTO REVIEWS (ID, RATING, TITLE, TEXT, HARDWARE_ID)
VALUES(2, 'TWO_STAR', 'NIJE DOBRO', 'MOGLI SU TO BOLJE NAPRAVITI', 1);
INSERT INTO REVIEWS (ID, RATING, TITLE, TEXT, HARDWARE_ID)
VALUES(3, 'FOUR_STAR', 'RAZOČARAN SAM', 'PRESPORO SE UČITAVA', 2);
INSERT INTO REVIEWS (ID, RATING, TITLE, TEXT, HARDWARE_ID)
VALUES(4, 'THREE_STAR', 'TAK-TAK', 'NIT SMRDI NIT MIRIŠE', 3);
INSERT INTO REVIEWS (ID, RATING, TITLE, TEXT, HARDWARE_ID)
VALUES(5, 'FIVE_STAR', 'PREDIVNO', 'SLOBODNO OVO KUPITE', 3);
INSERT INTO REVIEWS (ID, RATING, TITLE, TEXT, HARDWARE_ID)
VALUES(6, 'ONE_STAR', 'UŽASNO', 'NEMOJTE OVO KUPITI', 4);
INSERT INTO REVIEWS (ID, RATING, TITLE, TEXT, HARDWARE_ID)
VALUES(7, 'FOUR_STAR', 'UŽASNO', 'DOBRO JE', 5);
INSERT INTO REVIEWS (ID, RATING, TITLE, TEXT, HARDWARE_ID)
VALUES(8, 'THREE_STAR', 'OK', 'U REDU', 6);
INSERT INTO REVIEWS (ID, RATING, TITLE, TEXT, HARDWARE_ID)
VALUES(9, 'THREE_STAR', 'MEH', 'NISAM ZADOVOLJAN', 6);
INSERT INTO REVIEWS (ID, RATING, TITLE, TEXT, HARDWARE_ID)
VALUES(10, 'THREE_STAR', 'MEH', 'NISAM ZADOVOLJAN', 7);
INSERT INTO REVIEWS (ID, RATING, TITLE, TEXT, HARDWARE_ID)
VALUES(11, 'THREE_STAR', 'MOŽE I BOLJE', 'MOŽE I BOLJE', 7);
