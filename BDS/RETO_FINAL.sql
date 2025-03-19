CREATE DATABASE CONCESIONARIOS;
USE CONCESIONARIOS;

CREATE TABLE CAR_DEALERSHIP
(ID_CAR_DEALER INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
NAME_ VARCHAR (30), 
LOCATION VARCHAR (40));

CREATE TABLE WORKER
(USER_ VARCHAR (30) NOT NULL PRIMARY KEY,
PASSWORD_ VARCHAR (30),
ADMIN_ BOOLEAN,
ID_CAR_DEALER INT,
FOREIGN KEY (ID_CAR_DEALER) REFERENCES CAR_DEALERSHIP (ID_CAR_DEALER) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE MODEL
(NAME_MODEL VARCHAR (30) NOT NULL,
MARK VARCHAR (30),
STOCK INT CHECK (STOCK>=0),
PRICE DOUBLE,
ID_CAR_DEALER INT NOT NULL,
FOREIGN KEY (ID_CAR_DEALER) REFERENCES CAR_DEALERSHIP (ID_CAR_DEALER) ON UPDATE CASCADE ON DELETE CASCADE,
PRIMARY KEY (NAME_MODEL, ID_CAR_DEALER));

CREATE TABLE CLIENT_
(DNI CHAR (9) NOT NULL PRIMARY KEY, 
EMAIL VARCHAR (40), 
USER_ VARCHAR (30), 
PASSWORD_ VARCHAR (40));

CREATE TABLE PURCHASE
(DNI CHAR (9) NOT NULL,
NAME_MODEL VARCHAR (30) NOT NULL,
ID_CAR_DEALER INT NOT NULL,
DATE_PURCHASE DATE,
QUANTITY INT,
FOREIGN KEY (DNI) REFERENCES CLIENT_ (DNI) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (NAME_MODEL) REFERENCES MODEL (NAME_MODEL) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (ID_CAR_DEALER) REFERENCES MODEL (ID_CAR_DEALER) ON UPDATE CASCADE ON DELETE CASCADE,
PRIMARY KEY (DNI, NAME_MODEL));

INSERT INTO CAR_DEALERSHIP (NAME_, LOCATION) VALUES
('AutoMadrid', 'Madrid, España'),
('SuperCars', 'Barcelona, España'),
('Valencia Motors', 'Valencia, España');

INSERT INTO WORKER (USER_, PASSWORD_, ADMIN_, ID_CAR_DEALER) VALUES
('juan23', 'pass123', TRUE, 1),
('maria99', 'secure456', FALSE, 1),
('pedro78', 'mypass789', TRUE, 2),
('lauraX', 'key123', FALSE, 2),
('carlos21', 'password', TRUE, 3);

INSERT INTO MODEL (NAME_MODEL, MARK, STOCK, PRICE, ID_CAR_DEALER) VALUES
('Model S', 'Tesla', 5, 70000.00, 1),
('Model 3', 'Tesla', 8, 60000.00, 1),
('Civic', 'Honda', 10, 25000.00, 2),
('Accord', 'Honda', 6, 30000.00, 2),
('Mustang', 'Ford', 3, 55000.00, 3),
('Focus', 'Ford', 7, 22000.00, 3),
('Civic', 'Honda', 4, 26000.00, 3); 

INSERT INTO CLIENT_ (DNI, EMAIL, USER_, PASSWORD_) VALUES
('12345678A', 'cliente1@email.com', 'client1', 'pass1'),
('87654321B', 'cliente2@email.com', 'client2', 'pass2'),
('11223344C', 'cliente3@email.com', 'client3', 'pass3');

INSERT INTO PURCHASE (DNI, NAME_MODEL,ID_CAR_DEALER, DATE_PURCHASE, QUANTITY) VALUES
('12345678A', 'Model S', 1, '2024-03-01', 1),
('87654321B', 'Civic', 2, '2024-03-05', 2),
('11223344C', 'Focus', 3, '2024-03-10', 1);

DELIMITER //
CREATE PROCEDURE REGISTER_PURCHASE (DNI2 CHAR(9), NAME_MODEL2 VARCHAR (30), ID_CAR_DEALER2 INT, DATE_PURCHASE2 DATE, QUANTITY2 INT)
BEGIN
	DECLARE STOCK_BEFORE INT;
    DECLARE QUANTITY_PURCHASE INT;
    SELECT STOCK INTO STOCK_ANTERIOR FROM MODELO WHERE NAME_MODEL = NAME_MODEL2 AND ID_CAR_DEALER = ID_CAR_DEALER2 ;
    SELECT COUNT(*) INTO QUANTITY_PURCHASE FROM PURCHASE;
    
	INSERT INTO PURCHASE 
    VALUES 
    (DNI2, NAME_MODEL2, ID_CAR_DEALER2, DATE_PURCHASE2, QUANTITY2);
    IF(QUANTITY_PURCHASE=(SELECT COUNT(*) FROM PURCHASE)) THEN
		SELECT CONCAT('No se ha podido realizar la compra');
	ELSE 
		SELECT CONCAT('Compra realizada correctamente');
	END IF
    
    UPDATE MODEL
    SET STOCK = STOCK -1 WHERE NAME_MODEL = NAME_MODEL2 AND ID_CAR_DEALER = ID_CAR_DEALER2;
    
END //

DELIMITER ;
    
    
	
	





