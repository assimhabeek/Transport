CREATE TABLE reservation (
  id               NUMBER PRIMARY KEY,
  reservation_date DATE,
  rue              VARCHAR2(100),
  code_postal      VARCHAR2(40),
  ville            VARCHAR2(100)
);

CREATE TABLE voyageur (
  id             NUMBER PRIMARY KEY,
  nom            VARCHAR2(25),
  prenom         VARCHAR2(50),
  naissance_date DATE,
  reservation_id NUMBER NOT NULL,
  CONSTRAINT voyageur_reservation FOREIGN KEY (reservation_id) REFERENCES reservation (id)
);


CREATE TABLE facture (
  id             NUMBER PRIMARY KEY,
  emission_date  DATE,
  total          FLOAT,
  reglee         NUMBER(1),
  reservation_id NUMBER UNIQUE NOT NULL,
  CONSTRAINT facture_reservation FOREIGN KEY (reservation_id) REFERENCES reservation (id)
);



CREATE TABLE transport (
  id                    NUMBER PRIMARY KEY,
  depart_date           DATE,
  arrivee_date          DATE,
  nombre_sieges_occupes NUMBER,
  nombre_sieges_total   NUMBER,
  prix                  FLOAT
);


CREATE TABLE avoin (
  id            NUMBER PRIMARY KEY,
  compagnie     VARCHAR2(40),
  type_appareil VARCHAR2(40),
  CONSTRAINT avoin_transport FOREIGN KEY (id) REFERENCES transport (id)
);

CREATE TABLE train (
  id                NUMBER PRIMARY KEY,
  nombre_wagons     NUMBER,
  voiture_cafeteria NUMBER(1),
  CONSTRAINT train_transport FOREIGN KEY (id) REFERENCES transport (id)
);


CREATE TABLE transport_reservation (
  transport_id   NUMBER,
  reservation_id NUMBER,
  PRIMARY KEY (transport_id, reservation_id),
  CONSTRAINT tr_transport FOREIGN KEY (transport_id) REFERENCES transport (id),
  CONSTRAINT rt_reservation FOREIGN KEY (reservation_id) REFERENCES reservation (id)
);

CREATE SEQUENCE reservation_sequence
  START WITH 1
  INCREMENT BY 1;


CREATE OR REPLACE TRIGGER reservation_on_insert
  BEFORE INSERT
  ON reservation
  FOR EACH ROW
  BEGIN
    SELECT reservation_sequence.nextval
        INTO :new.id FROM dual;
  END;


CREATE SEQUENCE voyageur_sequence
  START WITH 1
  INCREMENT BY 1;


CREATE OR REPLACE TRIGGER voyageur_on_insert
  BEFORE INSERT
  ON voyageur
  FOR EACH ROW
  BEGIN
    SELECT voyageur_sequence.nextval
        INTO :new.id FROM dual;
  END;

CREATE SEQUENCE facture_sequence
  START WITH 1
  INCREMENT BY 1;

CREATE OR REPLACE TRIGGER facture_on_insert
  BEFORE INSERT
  ON facture
  FOR EACH ROW
  BEGIN
    SELECT facture_sequence.nextval
        INTO :new.id FROM dual;
  END;




CREATE SEQUENCE transport_sequence
  START WITH 1
  INCREMENT BY 1;


CREATE OR REPLACE TRIGGER transport_on_insert
  BEFORE INSERT
  ON transport
  FOR EACH ROW
  BEGIN
    SELECT transport_sequence.nextval
        INTO :new.id FROM dual;
  END;




