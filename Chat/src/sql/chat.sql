/*
Created: 04/10/2017
Modified: 04/10/2017
Model: MySQL 5.6
Database: MySQL 5.6
*/


-- Create tables section -------------------------------------------------

-- Table Sala

CREATE TABLE `Sala`
(
  `nom_sala` Char(20) NOT NULL
)
;

ALTER TABLE `Sala` ADD  PRIMARY KEY (`nom_sala`)
;

-- Table Usuario

CREATE TABLE `Usuario`
(
  `nom_usuario` Char(30) NOT NULL,
  `nom_sala` Char(20)
)
;

CREATE INDEX `IX_Relationship1` ON `Usuario` (`nom_sala`)
;

ALTER TABLE `Usuario` ADD  PRIMARY KEY (`nom_usuario`)
;

-- Table Mensagem

CREATE TABLE `Mensagem`
(
  `cod_mensagem` Int NOT NULL AUTO INCREMENT,
  `nom_sala` Char(20),
  `nom_usuario` Char(30),
  `des_conteudo` Text NOT NULL
)
;

CREATE INDEX `IX_Relationship2` ON `Mensagem` (`nom_sala`)
;

CREATE INDEX `IX_Relationship3` ON `Mensagem` (`nom_usuario`)
;

ALTER TABLE `Mensagem` ADD  PRIMARY KEY (`cod_mensagem`)
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE `Usuario` ADD CONSTRAINT `Relationship1` FOREIGN KEY (`nom_sala`) REFERENCES `Sala` (`nom_sala`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Mensagem` ADD CONSTRAINT `Relationship2` FOREIGN KEY (`nom_sala`) REFERENCES `Sala` (`nom_sala`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Mensagem` ADD CONSTRAINT `Relationship3` FOREIGN KEY (`nom_usuario`) REFERENCES `Usuario` (`nom_usuario`) ON DELETE RESTRICT ON UPDATE RESTRICT
;
