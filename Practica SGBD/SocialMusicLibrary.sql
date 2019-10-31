SET DEFINE ?

DROP TABLE members cascade constraints
/
DROP TABLE songs cascade constraints
/
DROP TABLE favorites cascade constraints
/
DROP TABLE albums cascade constraints
/
DROP TABLE friends cascade constraints
/

CREATE TABLE members (
  id_member INT NOT NULL PRIMARY KEY,
  email VARCHAR2(30) NOT NULL UNIQUE,
  passwd VARCHAR2(50) NOT NULL,
  salt VARCHAR2(50) NOT NULL,
  lname VARCHAR2(30) NOT NULL,
  fname VARCHAR2(30) NOT NULL,
  avatar VARCHAR2(30)
)
/
CREATE SEQUENCE memb_seq START WITH 1;
/
CREATE OR REPLACE TRIGGER memb_bir 
BEFORE INSERT ON members 
FOR EACH ROW

BEGIN
  SELECT memb_seq.NEXTVAL
  INTO   :new.id_member
  FROM   dual;
END;
/
CREATE TABLE albums (
  id_album INT NOT NULL PRIMARY KEY,
  artist VARCHAR(30) NOT NULL,
  genre VARCHAR2(15) NOT NULL,
  release_date DATE NOT NULL,
  album_name VARCHAR2(30) NOT NULL,
  artwork VARCHAR2(30) NOT NULL
)
/
CREATE SEQUENCE alb_seq START WITH 1;
/
CREATE OR REPLACE TRIGGER alb_bir 
BEFORE INSERT ON albums 
FOR EACH ROW

BEGIN
  SELECT alb_seq.NEXTVAL
  INTO   :new.id_album
  FROM   dual;
END;
/
CREATE TABLE songs (
  id_song INT NOT NULL PRIMARY KEY,
  id_album INT NOT NULL,
  CONSTRAINT song_fk FOREIGN KEY (id_album) REFERENCES albums(id_album),
  song_name VARCHAR2(30) NOT NULL,
  song_length VARCHAR2(30) NOT NULL
)
/
CREATE SEQUENCE song_seq START WITH 1;
/
CREATE OR REPLACE TRIGGER song_seq 
BEFORE INSERT ON songs 
FOR EACH ROW

BEGIN
  SELECT song_seq.NEXTVAL
  INTO   :new.id_song
  FROM   dual;
END;
/
CREATE TABLE friends (
  id_member1 INT NOT NULL,
  id_member2 INT NOT NULL,
  CONSTRAINT no_duplicates UNIQUE (id_member1, id_member2),
  CONSTRAINT fk_friends FOREIGN KEY(id_member1) REFERENCES members(id_member),
  CONSTRAINT fk_friends2 FOREIGN KEY(id_member2) REFERENCES members(id_member)
)
/
CREATE TABLE favorites (
  id_member_favs INT NOT NULL,
  id_song_favs INT NOT NULL,
  CONSTRAINT fk_member FOREIGN KEY (id_member_favs) REFERENCES members(id_member),
  CONSTRAINT fk_favorites FOREIGN KEY (id_song_favs) REFERENCES songs(id_song)
)
/

CREATE OR REPLACE FUNCTION recentAlbums 
RETURN number IS 
   total VARCHAR(30); 
BEGIN 
   SELECT * into total 
   FROM (SELECT * FROM albums ORDER BY date) WHERE ROWNUM < 15; 
    
   RETURN total; 
END; 
/ 