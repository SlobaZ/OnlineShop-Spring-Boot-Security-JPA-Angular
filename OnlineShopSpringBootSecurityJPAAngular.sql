CREATE USER IF NOT EXISTS jwduser IDENTIFIED BY 'pass';

DROP DATABASE IF EXISTS OnlineShopSpringBootSecurityJPAAngular;
CREATE DATABASE OnlineShopSpringBootSecurityJPAAngular DEFAULT CHARACTER SET utf8;

USE OnlineShopSpringBootSecurityJPAAngular;

GRANT ALL ON OnlineShopSpringBootSecurityJPAAngular.* TO 'jwduser'@'%';

FLUSH PRIVILEGES;

