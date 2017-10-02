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
  `nom_usuario` Text NOT NULL
)
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

-- Table Usuario_Sala

CREATE TABLE `Usuario_Sala`
(
  `cod_UsuSal` Int NOT NULL AUTO_INCREMENT,
  `nom_usuario` Text NOT NULL,
  `nom_sala` Text NOT NULL
)
;

ALTER TABLE `Usuario_Sala` ADD  PRIMARY KEY (`nom_usuario`,`nom_sala`,`cod_UsuSal`)
;

-- Table Mensagem

CREATE TABLE `Mensagem`
(
  `cod_msg` Int NOT NULL AUTO_INCREMENT,
  `nom_usuario` Text,
  `nom_sala` Text,
  `cod_UsuSal` Int,
  `des_conteudo` Text,
  PRIMARY KEY (`cod_msg`)
)
;

CREATE INDEX `IX_Relationship4` ON `Mensagem` (`nom_usuario`,`nom_sala`,`cod_UsuSal`)
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE `Usuario_Sala` ADD CONSTRAINT `Relationship1` FOREIGN KEY (`nom_usuario`) REFERENCES `Usuario` (`nom_usuario`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Usuario_Sala` ADD CONSTRAINT `Relationship2` FOREIGN KEY (`nom_sala`) REFERENCES `Sala` (`nom_sala`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Mensagem` ADD CONSTRAINT `Relationship4` FOREIGN KEY (`nom_usuario`, `nom_sala`, `cod_UsuSal`) REFERENCES `Usuario_Sala` (`nom_usuario`, `nom_sala`, `cod_UsuSal`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

