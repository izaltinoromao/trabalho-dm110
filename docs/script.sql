--script do banco

CREATE TABLE PRODUCT (
                         productCode VARCHAR(255) PRIMARY KEY,
                         amountStored INT,
                         minimumAmount INT,
                         location VARCHAR(255),
                         enterDate INT
);
