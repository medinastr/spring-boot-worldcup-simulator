CREATE DATABASE IF NOT EXISTS `nation_db`;
USE `nation_db`;

DROP TABLE IF EXISTS `nation_db`;

CREATE TABLE `nation` (
	`id` int NOT NULL AUTO_INCREMENT,
    `nation_name` varchar(45) DEFAULT NULL,
    `wins` int DEFAULT NULL,
    `goals` int DEFAULT NULL,
    `goals_conceded` int DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;