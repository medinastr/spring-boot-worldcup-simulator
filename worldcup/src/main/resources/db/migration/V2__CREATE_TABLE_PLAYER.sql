CREATE TABLE IF NOT EXISTS `player` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(128) NOT NULL,
  `last_name` varchar(128) NOT NULL,
  `shirt_number` int DEFAULT NULL,
  `nation_id` int DEFAULT NULL,

  PRIMARY KEY (`id`),

  CONSTRAINT `FK_NATION_idx`
  FOREIGN KEY (`nation_id`)
  REFERENCES `nation` (`id`)

  ON DELETE NO ACTION ON UPDATE NO ACTION
)