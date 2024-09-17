CREATE TABLE IF NOT EXISTS `stadium` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `capacity` int NOT NULL,
  `city` varchar(128) DEFAULT NULL,
  `game_rental` double DEFAULT NULL,
  PRIMARY KEY (`id`)
)