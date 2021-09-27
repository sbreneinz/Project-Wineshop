-- Wine Shop Data Model

-- Delete content from tables 

DELETE FROM Category;
DELETE FROM Customer;
DELETE FROM Employee;
DELETE FROM Invoice;
DELETE FROM Invoice_Line;
DELETE FROM Product;
DELETE FROM Product_Category;
DELETE FROM User_Role;
DELETE FROM Inventory;



-- Clear out any old tables
DROP TABLE Category;
DROP TABLE Customer;
DROP TABLE Employee;
DROP TABLE Invoice;
DROP TABLE Invoice_Line;
DROP TABLE Product;
DROP TABLE Product_Category;
DROP TABLE Sub_Category;
DROP TABLE User_Role;
DROP TABLE Inventory;

--------------------------------------------------------------------------------
                        -- CREATE TABLES --

-- 
-- Product
--

CREATE TABLE Product (
    -- Primary Key --
    Product_Id      INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    -- Data Fields --
    Prod_Name       VARCHAR(45),
    Description     VARCHAR(3000),
    Price           DOUBLE,
    Prod_Type       VARCHAR(45),
    Prod_Year       INTEGER,
    Country         VARCHAR(45),
    Region          VARCHAR(45),
    Producer        VARCHAR(45)
); 


--
-- User_Role
--

CREATE TABLE User_Role (
    -- Primary Key --
    User_Role_Id    INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
-- Data Fields --
    Description     VARCHAR(60)
);

-- 
-- Category 
--

CREATE TABLE Category (
    -- Primary Key --
    Category_Id      INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    -- Data Fields --
    Description     VARCHAR(200)
);


-- 
-- Invoice
--

CREATE TABLE Invoice (
    -- Primary Key --
    Invoice_Id	 INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    -- Data Fields --
    Operation_Date VARCHAR(20),
    Total_Sale	 DOUBLE,
    Employee_Id   INTEGER
-- Employee_Id will become Foreign Key
);


-- 
-- Employee
--

CREATE TABLE Employee (
    -- Primary Key --
    Employee_Id      INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
-- Data Fields --
    Customer_Id      INTEGER
--     CONSTRAINT Employee_fk FOREIGN KEY (Role_Id) REFERENCES User_Role (User_Role_Id)
);


-- 
-- Invoice_Line
--

CREATE TABLE Invoice_Line (
    -- Primary Key --
    Invoice_Line_Id	 INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    -- Foreign Key --
    Invoice_Id      INTEGER,
    Product_Id      INTEGER,
    -- Data Fields --
    Product_Price   DOUBLE,
    Product_Quantity INTEGER
--     CONSTRAINT Invoice_Line_Invoice_Id_fk FOREIGN KEY (Invoice_Id) REFERENCES Invoice (Invoice_Id),
--     CONSTRAINT Invoice_Line_Product_Id_fk FOREIGN KEY (Product_Id) REFERENCES Product (Product_Id)
);

-- 
-- Customer
--

CREATE TABLE Customer (
    -- Primary Key --
    Customer_Id      INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    -- Data Fields --
    Username        VARCHAR(45),
    Password        VARCHAR(45),
    First_Name      VARCHAR(45),
    Last_Name       VARCHAR(45),
    Tax_Id          VARCHAR(9),
    Date_Of_Birth   VARCHAR(20),
    Address         VARCHAR(60),
    Postal_Code     VARCHAR(8),
    Role_Id         INTEGER
--     CONSTRAINT Customer_fk FOREIGN KEY (Role_Id) REFERENCES User_Role (User_Role_Id)
);


-- 
-- Product_Category
--

CREATE TABLE Product_Category (
    -- Primary Key --
    Product_Category_Id	 INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    -- Foreign Key --
    Product_Id      INTEGER,
    Category_Id     INTEGER
--     CONSTRAINT Product_Category_Product_Id_fk FOREIGN KEY (Product_Id) REFERENCES Product (Product_Id),
--     CONSTRAINT Product_Category_Category_Id_fk FOREIGN KEY (Category_Id) REFERENCES Category (Category_Id)
);

--
-- Sub_Category
--

CREATE TABLE Sub_Category (
    -- Primary Key --
    Sub_Category_Id      INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    -- Foreign Key --
    Category_Id     INTEGER,
    -- Data Fields --
    Description     VARCHAR(200)
--     CONSTRAINT Sub_Category_fk FOREIGN KEY (Category_Id) REFERENCES Category (Category_Id)
);

--
-- Inventory 
--

CREATE TABLE Inventory (
    -- Data Fields --
    Product_Id      INTEGER,    
    Quantity       INTEGER
--     CONSTRAINT Inventory_fk FOREIGN KEY (Product_Id) REFERENCES Product (Product_Id)
);

--------------------------------------------------------------------------------
-- Reset Primary Key to 1 --

-- ALTER TABLE CUSTOMER ALTER COLUMN Customer_Id RESTART WITH 1;
-- ALTER TABLE EMPLOYEE ALTER COLUMN Employee_Id RESTART WITH 1;
-- ALTER TABLE CATEGORY ALTER COLUMN Category_Id RESTART WITH 1;
-- ALTER TABLE INVENTORY ALTER COLUMN Inventory_Id RESTART WITH 1;
-- ALTER TABLE INVOICE ALTER COLUMN Invoice_Id RESTART WITH 1;
-- ALTER TABLE INVOICE_LINE ALTER COLUMN Invoice_Line_Id RESTART WITH 1;
-- ALTER TABLE PRODUCT ALTER COLUMN Product_Id RESTART WITH 1;
-- ALTER TABLE PRODUCT_CATEGORY ALTER COLUMN Product_Category_Id RESTART WITH 1;
-- ALTER TABLE SUB_CATEGORY ALTER COLUMN Sub_Category_Id RESTART WITH 1;
-- ALTER TABLE USER_ROLE ALTER COLUMN User_Role_Id RESTART WITH 1;

--------------------------------------------------------------------------------

                -- POPULATE TABLES WITH DATA --

-- Category --

INSERT INTO CATEGORY (Description) VALUES ('Wine');
INSERT INTO CATEGORY (Description) VALUES ('Beer');

-- Invoice --

INSERT INTO Invoice (Operation_Date, Total_Sale, Employee_Id) VALUES ('2021-08-01', 179.8, 1);
INSERT INTO Invoice (Operation_Date, Total_Sale, Employee_Id) VALUES ('2021-08-02', 550, 1);
INSERT INTO Invoice (Operation_Date, Total_Sale, Employee_Id) VALUES ('2021-09-11', 54.90, 1);

-- Invoice_Line --

INSERT INTO Invoice_Line (Invoice_Id, Product_Id, Product_Price, Product_Quantity) VALUES (1, 6, 89.90, 2);
INSERT INTO Invoice_Line (Invoice_Id, Product_Id, Product_Price, Product_Quantity) VALUES (2, 4, 550, 1);
INSERT INTO Invoice_Line (Invoice_Id, Product_Id, Product_Price, Product_Quantity) VALUES (3, 12, 12, 1);
INSERT INTO Invoice_Line (Invoice_Id, Product_Id, Product_Price, Product_Quantity) VALUES (3, 11, 42.90, 1);
-- Products -- 

INSERT INTO PRODUCT (Prod_Name, Description, Price, Prod_Type, Prod_Year, Country, Region, Producer) VALUES ('Gloria Reynolds', 'Julian, son of Gloria, produces a quality wine, named after his mother Gloria Reynolds.', 69.90, 'Red', 2005, 'Portugal', 'Alentejo', 'Reynold Wine Growers');
INSERT INTO PRODUCT (Prod_Name, Description, Price, Prod_Type, Prod_Year, Country, Region, Producer) VALUES ('Chateau Petrus', 'Petrus is the biggest name in a star constellation of Bordeaux. Located on the right bank of Bordeaux in the small "Apellation" of "Pomerol", where the Merlot variety is queen. Until 1945 this wine was in the so-called "secret of the gods". In possession of Madame Loubat, which was joined by Jean Pierre Moueix. Wine began to be unveiled, whether or not it was the preferred wine of names like the Kennedys or Queen Elizabeth of England. Due to a relentless demand on the part of collectors and investors, combined with a very limited production, this wine is today one of the most expensive in the world. It is the wine that anyone would like to taste at least once in their life. A dream of wine.', 7490, 'Red', 1945, 'France', 'Bordeaux', 'Chateau Petrus');
INSERT INTO PRODUCT (Prod_Name, Description, Price, Prod_Type, Prod_Year, Country, Region, Producer) VALUES ('Barca Velha', 'Barca-Velha is the epitome, the first, unquestionable symbol of the highest quality of Douro wines. Classic, intense, complex, elegant and rich, there are few adjectives to describe what is, since its creation in 1952, the most celebrated Portuguese wine. Barca-Velha is the foundation on which the reputation of Casa Ferreirinha was built, the brand with the greatest quality tradition in the Douro and one of the main world references', 679, 'Red', 1999, 'Portugal', 'Douro', 'Sogrape Vinhos');
INSERT INTO PRODUCT (Prod_Name, Description, Price, Prod_Type, Prod_Year, Country, Region, Producer) VALUES ('Pera Manca', 'They are full-bodied, complex and elegant wines, with an aroma of raisins of fruits and essences from the aging woods. Due to the high quality of the tannins and woods used, these are wines that have great longevity, requiring some time to reveal their full potential.', 550, 'Red', 2011, 'Portugal', 'Alentejo', 'Fundacao Eugenio de Almeida');
INSERT INTO PRODUCT (Prod_Name, Description, Price, Prod_Type, Prod_Year, Country, Region, Producer) VALUES ('Dom Perignon', 'Dom Perignon is vintage champagne only. Each vintage is a creation, singular and unique, that expresses both the character of the year, and the character of Dom P�rignon. After at least eight years of elaboration in the cellars, the wine embodies the perfect balance of Dom P�rignon, the Pl�nitude of harmony.', 178.90, 'Champagne', 2010, 'France', 'Champagne', 'Moet & Chandon');
INSERT INTO PRODUCT (Prod_Name, Description, Price, Prod_Type, Prod_Year, Country, Region, Producer) VALUES ('Ruinart Rose', 'Presented in an elegant 18th century style bottle, this champagne reveals a fresh and distinctive rose color. Varieties: Premier Cru Chardonnay 45% and Premier Cru Pinot Noir 55%. Very fine fruity aroma (blackcurrant and blackberry). A very intense wine. Vigorous and well-balanced palate. A perfect, smooth wine with cherry notes. Quite long finish.', 89.90, 'Champagne', 2012, 'France', 'Champagne', 'Ruinart');
INSERT INTO PRODUCT (Prod_Name, Description, Price, Prod_Type, Prod_Year, Country, Region, Producer) VALUES ('Chablis Beauroy', 'All wines produced by Maison Louis Jadot are “Appellation dOrigine Contrôlée wines from across the region. Maison Louis Jadot now controls 240 hectares spread across Burgundy, from Côte dOr to Mâconnais and even Beaujolais. Thanks to the quality of their wines, they have forged strong links, both in France and around the world, with sommeliers, restaurateurs, wine merchants, importers, agents and wine lovers.', 43.90, 'White', 2017, 'France', 'Bourgogne', 'Maison Louis Jadot');
INSERT INTO PRODUCT (Prod_Name, Description, Price, Prod_Type, Prod_Year, Country, Region, Producer) VALUES ('Tapada do Chaves Vinhas Velhas', 'It is in the outskirts of Portalegre, in Alto Alentejo, that Tapada do Chaves wines are produced. For almost 100 years, this property has produced wines of recognized quality that are among the best in Portugal. Tapada do Chaves wines preserve the warmth and smoothness of the Alentejo region. Associated with a strong family tradition and a history of passion and dedication to the land, they originate from the vineyards of the property that gives them their name, aged between 15 and 85 years, from where red grapes of the Trincadeira, Aragonez, varieties come from. Castelão and Tinta Francesa and white varieties from Fernão Pires, Arinto, Alva and Tamarez.', 53.50, 'White', 2008, 'Portugal', 'Alentejo', 'Tapada do Chaves');
INSERT INTO PRODUCT (Prod_Name, Description, Price, Prod_Type, Prod_Year, Country, Region, Producer) VALUES ('Quinta da Leda', 'Sogrape Vinhos was founded in 1942 by Fernando van Zeller Guedes, with the ambition of making Portuguese wines known to the world and a long-term vision based on the quality of the wines to be marketed, the importance of brand novelty and the presentation of its products. wines. Led today by the third generation of the founding family, Sogrape Vinhos increasingly faithfully fulfills the objective assumed since its foundation: to be a family-oriented company with an international vocation, focused on the production of quality wines, innovation and development of Portuguese brands of global level. Sogrape Vinhos has around 830 hectares of vineyards in Portugal. Casa Ferreirinha and its wines are synonymous with time and art. This has happened since its foundation, in the 18th century, by the hand of Bernardo Ferreira, who saw the formula refined by his descendants, especially by his granddaughter Dona Antónia Adelaide Ferreira, who affectionately became known as "Ferreirinha" or "Ferreirinha-da-Régua" for the people of your land. Through the hands of Dona Antónia, who twice widowed herself at the head of a large company, Ferreira consolidated itself admirably.', 36.50, 'Red', 2017, 'Portugal', 'Douro', 'Casa Ferreirinha');
INSERT INTO PRODUCT (Prod_Name, Description, Price, Prod_Type, Prod_Year, Country, Region, Producer) VALUES ('Quinta Vale D. Maria', 'Quinta do Vale Dona Maria is a property situated by the mouth of the Rio Torto. Its history dates back to 1868 and was associated with names such as the Symingtons, who used their grapes to integrate into vintage Smith Woodhouse. When the Van Zeller family took over this farm, they found it almost completely abandoned, with about 10ha of old vines. The recovery work would be arduous, but nothing that would take Cristiano van Zeller away from this arduous task, or if he did not come from a superb "school" like Quinta do Noval, of which he was president. Work began on recovering the vineyard and houses that were almost reduced to dust. But what commanded Cristiano was to realize day after day that he had a pearl there that he had so much to give. They also acquired plots adjacent to the Quinta, until it reached 28ha of vineyard. The inaugural harvest was in 1996 and it never stopped producing great wines. Recently, the wines from this farm, CV, Vale Dona Maria, and the recent Vinha do Rio and Vinha Francisca have been among the best wines in Portugal for Wine Advocate, the most important and prestigious wine publication in the world.' , 43.50, 'Red', 2017, 'Portugal', 'Douro', 'Lemos & Van Zeller');
INSERT INTO PRODUCT (Prod_Name, Description, Price, Prod_Type, Prod_Year, Country, Region, Producer) VALUES ('Horta Osório Grande Escolha', 'The “H.O. – Horta Osório Wines” come exclusively from grapes produced on the farms of Casa Agrícola Horta Osório where, in addition to “old vineyards”, “new vineyards” were planted with the best traditional varieties of the Douro, in unique “terroirs”. The grapes are harvested when they have a perfect phenolic ripeness, which usually occurs on the 4th week of September. The entire harvest is done manually, which allows for a refined choice and selection. The transport of the grapes from the vineyard to the winery is done in small 20 kg containers. The entire winemaking process is accompanied, maintaining the fermentation at a controlled temperature, to ensure the high quality of the wines.', 42.90, 'Red', 2015, 'Portugal', 'Douro', 'Horta Osório Wines');
INSERT INTO PRODUCT (Prod_Name, Description, Price, Prod_Type, Prod_Year, Country, Region, Producer) VALUES ('Monólogo Avesso', 'Monólogo Avesso P67 is a single variety wine produced at Quinta de Santa Teresa, from the vineyards of the P67 plot, using sustainable and organic farming practices. The Avesso variety is the native variety of Baião. Finding in this region its expression of excellence, with a climate of transition between the typical Atlantic climate of Vinho Verde and the continental climate of the Douro, this variety has the opportunity to reach, in a balanced way, complete levels of maturation, resulting in complex wines, delicate and long-lasting.', 12, 'White', 2020, 'Portugal', 'Douro', 'A&D Wines');

-- Inventory -- 

INSERT INTO Inventory (Product_Id, Quantity) VALUES (1, 5);
INSERT INTO Inventory (Product_Id, Quantity) VALUES (2, 1);
INSERT INTO Inventory (Product_Id, Quantity) VALUES (3, 5);
INSERT INTO Inventory (Product_Id, Quantity) VALUES (4, 3);
INSERT INTO Inventory (Product_Id, Quantity) VALUES (5, 5);
INSERT INTO Inventory (Product_Id, Quantity) VALUES (6, 10);
INSERT INTO Inventory (Product_Id, Quantity) VALUES (7, 10);
INSERT INTO Inventory (Product_Id, Quantity) VALUES (8, 10);
INSERT INTO Inventory (Product_Id, Quantity) VALUES (9, 10);
INSERT INTO Inventory (Product_Id, Quantity) VALUES (10, 10);
INSERT INTO Inventory (Product_Id, Quantity) VALUES (11, 10);
INSERT INTO Inventory (Product_Id, Quantity) VALUES (12, 20);

-- Product_Category --

INSERT INTO Product_Category (Product_Id, Category_Id) VALUES (1, 1);
INSERT INTO Product_Category (Product_Id, Category_Id) VALUES (2, 1);
INSERT INTO Product_Category (Product_Id, Category_Id) VALUES (3, 1);
INSERT INTO Product_Category (Product_Id, Category_Id) VALUES (4, 1);
INSERT INTO Product_Category (Product_Id, Category_Id) VALUES (5, 1);
INSERT INTO Product_Category (Product_Id, Category_Id) VALUES (6, 1);
INSERT INTO Product_Category (Product_Id, Category_Id) VALUES (7, 1);
INSERT INTO Product_Category (Product_Id, Category_Id) VALUES (8, 1);
INSERT INTO Product_Category (Product_Id, Category_Id) VALUES (9, 1);
INSERT INTO Product_Category (Product_Id, Category_Id) VALUES (10, 1);
INSERT INTO Product_Category (Product_Id, Category_Id) VALUES (11, 1);
INSERT INTO Product_Category (Product_Id, Category_Id) VALUES (12, 1);

-- Sub Category --

INSERT INTO SUB_CATEGORY (Category_Id, Description) VALUES (1, 'Red Wine');
INSERT INTO SUB_CATEGORY (Category_Id, Description) VALUES (1, 'White Wine');
INSERT INTO SUB_CATEGORY (Category_Id, Description) VALUES (1, 'Rose Wine');
INSERT INTO SUB_CATEGORY (Category_Id, Description) VALUES (1, 'Sparkling Wine');
INSERT INTO SUB_CATEGORY (Category_Id, Description) VALUES (1, 'Champagne');

INSERT INTO SUB_CATEGORY (Category_Id, Description) VALUES (2, 'Weissbier');
INSERT INTO SUB_CATEGORY (Category_Id, Description) VALUES (2, 'Belgian Beer');

-- User Roles --

INSERT INTO USER_ROLE (Description) VALUES ('Admin');
INSERT INTO USER_ROLE (Description) VALUES ('Employee');
INSERT INTO USER_ROLE (Description) VALUES ('Customer');

-- Employee --
INSERT INTO EMPLOYEE (Customer_Id) VALUES (2);

-- Customer --

INSERT INTO CUSTOMER (Username, Password, First_Name, Last_Name, Tax_Id, Date_of_Birth, Address, Postal_Code, Role_Id) VALUES ('anafonseca', 'ana80', 'Ana', 'Fonseca', '202122222', '1980-12-31', 'Rua da Costa Nova', '2000-123', 3);
INSERT INTO CUSTOMER (Username, Password, First_Name, Last_Name, Tax_Id, Date_of_Birth, Address, Postal_Code, Role_Id) VALUES ('ruizargo','rui76', 'Rui', 'Zargo', '202122223','1976-04-16', 'Rua da Praia', '1600-100', 2);

--------------------------------------------------------------------------------
                        -- Rename Column Name -- 

-- RENAME COLUMN table-Name.simple-Column-Name TO simple-Column-Name 
