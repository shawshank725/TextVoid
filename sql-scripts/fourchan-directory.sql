CREATE DATABASE  IF NOT EXISTS `fourchan_directory`;
USE `fourchan_directory`;

CREATE TABLE if not exists `fourchan`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `message` varchar(1000) DEFAULT NULL,
  `ip_address` varchar(45) DEFAULT NULL,
  `genre` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
