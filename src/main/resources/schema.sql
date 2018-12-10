CREATE TABLE orders(
	orderId UUID PRIMARY KEY NOT NULL,
	dateCreated LocalDateTime NOT NULL,
	tableNumber INT NOT NULL,
	isOpen BOOLEAN NOT NULL,
	isOccupied BOOLEAN NOT NULL
);

CREATE TABLE meals(
	name VARCHAR(50) PRIMARY KEY NOT NULL,
	description VARCHAR(300),
	price DECIMAL(20,2) NOT NULL,
	tax INT NOT NULL,
	dateCreated LocalDateTime NOT NULL,
	dateEdited LocalDateTime,
	containsMeat BOOLEAN NOT NULL,
	foodCategory VARCHAR(30)
);

CREATE TABLE drinks(
	name VARCHAR(50) PRIMARY KEY NOT NULL,
	description VARCHAR(300),
	price DECIMAL(20,2) NOT NULL,
	tax INT NOT NULL,
	dateCreated LocalDateTime NOT NULL,
	dateEdited LocalDateTime,
	containsAlcohol BOOLEAN NOT NULL
	beverageTyoe VARCHAR(20)
);

CREATE TABLE orders_meals_drinks(
	orderId UUID 
);