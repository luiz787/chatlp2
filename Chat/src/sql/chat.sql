/*
Created: 02/10/2017
Modified: 02/10/2017
Model: MySQL 5.6
Database: MySQL 5.6
*/


-- Create tables section -------------------------------------------------

-- Table Usuario

CREATE TABLE `Usuario`
(
  `nom_usuario` Text NOT NULL,
  `nom_sala` Text
)
;

CREATE INDEX `IX_Relationship1` ON `Usuario` (`nom_sala`)
;

ALTER TABLE `Usuario` ADD  PRIMARY KEY (`nom_usuario`)
;

-- Table Sala

CREATE TABLE `Sala`
(
  `nom_sala` Text NOT NULL
)
;

ALTER TABLE `Sala` ADD  PRIMARY KEY (`nom_sala`)
;

-- Table Mensagem

CREATE TABLE `Mensagem`
(
  `cod_mensagem` Int NOT NULL AUTO_INCREMENT,
  `des_conteudo` Text,
  `nom_usuario` Text,
  `nom_sala` Text,
  PRIMARY KEY (`cod_mensagem`)
)
;

CREATE INDEX `IX_Relationship2` ON `Mensagem` (`nom_usuario`)
;

CREATE INDEX `IX_Relationship3` ON `Mensagem` (`nom_sala`)
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE `Usuario` ADD CONSTRAINT `Relationship1` FOREIGN KEY (`nom_sala`) REFERENCES `Sala` (`nom_sala`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Mensagem` ADD CONSTRAINT `Relationship2` FOREIGN KEY (`nom_usuario`) REFERENCES `Usuario` (`nom_usuario`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Mensagem` ADD CONSTRAINT `Relationship3` FOREIGN KEY (`nom_sala`) REFERENCES `Sala` (`nom_sala`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

