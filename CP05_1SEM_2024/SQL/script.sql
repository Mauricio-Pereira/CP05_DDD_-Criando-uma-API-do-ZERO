DECLARE
    v_count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM USER_SEQUENCES
    WHERE SEQUENCE_NAME = 'CP_COLLECTIONS_SEQ';

    IF v_count = 1 THEN
        EXECUTE IMMEDIATE 'DROP SEQUENCE cp_collections_seq';
    END IF;
END;

DECLARE
    v_count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM USER_TABLES
    WHERE TABLE_NAME = 'CP_COLLECTIONS';

    IF v_count = 1 THEN
        EXECUTE IMMEDIATE 'DROP TABLE cp_collections CASCADE CONSTRAINTS';
    END IF;
END;

DECLARE
    v_count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM USER_TABLES
    WHERE TABLE_NAME = 'CP_CARD';

    IF v_count = 1 THEN
        EXECUTE IMMEDIATE 'DROP TABLE CP_CARD CASCADE CONSTRAINTS';
    END IF;
END;

CREATE TABLE cp_collections (
                                id NUMBER PRIMARY KEY,
                                name VARCHAR2(50) NOT NULL
);

CREATE SEQUENCE cp_collections_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER cp_collections_bir
    BEFORE INSERT ON cp_collections
    FOR EACH ROW
BEGIN
    SELECT cp_collections_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
END;


CREATE TABLE cp_card (
                         cod_card         VARCHAR2(2) NOT NULL,
                         collectionsid    NUMBER,
                         supertrunfo      char(1),
                         nome             VARCHAR2(50),
                         nome_de_cavaleiro   VARCHAR2(100),
                         grupo_de_cavaleiros VARCHAR2(100),
                         soco             NUMBER,
                         chute            NUMBER,
                         tecnica          NUMBER,
                         conhecimento     NUMBER,
                         forca            NUMBER
);

ALTER TABLE cp_card ADD CONSTRAINT cp_card_pk PRIMARY KEY ( cod_card );
ALTER TABLE cp_card ADD FOREIGN KEY (collectionsid) REFERENCES cp_collections(id);
commit;

