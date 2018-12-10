CREATE TABLE orders(
	orderId BIGINT PRIMARY KEY NOT NULL,
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
	foodType VARCHAR(30)
);

CREATE TABLE drinks(
	name VARCHAR(50) PRIMARY KEY NOT NULL,
	description VARCHAR(300),
	price DECIMAL(20,2) NOT NULL,
	tax INT NOT NULL,
	dateCreated LocalDateTime NOT NULL,
	dateEdited LocalDateTime,
	containsAlcohol BOOLEAN NOT NULL
	beverageType VARCHAR(20)
);

CREATE TABLE orderfood(
	orderId UUID NOT NULL,
	name VARCHAR(300) NOT NULL,
	amountOrdered INT NOT NULL,
	FOREIGN KEY (orderId) REFERENCES orders(orderId),
	FOREIGN KEY (name) references meals(name),
	UNIQUE (orderId, name)
);

CREATE TABLE orderbeverage(
	orderId UUID NOT NULL,
	name VARCHAR(300) NOT NULL,
	amountOrdered INT NOT NULL,
	FOREIGN KEY (orderId) REFERENCES orders(orderId),
	FOREIGN KEY (name) references drinks(name),
	UNIQUE (orderId, name)
);