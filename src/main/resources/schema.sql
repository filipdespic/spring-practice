CREATE TABLE IF NOT EXISTS purchase (
 id INT AUTO_INCREMENT PRIMARY KEY,
 product varchar(50) NOT NULL,
 price double NOT NULL
);
create table account (
 id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(50) NOT NULL,
 amount DOUBLE NOT NULL
);