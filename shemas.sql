SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `compet_bloc` DEFAULT CHARACTER SET utf8 ;
USE `compet_bloc` ;

-- -----------------------------------------------------
-- Table `compet_bloc`.`Bloc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compet_bloc`.`Bloc` (
  `idBloc` INT(11) NOT NULL AUTO_INCREMENT,
  `numbloc` VARCHAR(45) NOT NULL,
  `couleurdiff` VARCHAR(45) NOT NULL,
  `couleurvoie` VARCHAR(45) NOT NULL,
  `ouvreur` VARCHAR(45) NOT NULL,
  `nbreussi` INT(11) NOT NULL,
  `valeurinit` INT(11) NOT NULL,
  `valeurfinal` INT(11) NOT NULL,
  PRIMARY KEY (`idBloc`),
  UNIQUE INDEX `numbloc_UNIQUE` (`numbloc` ASC),
  UNIQUE INDEX `idBloc_UNIQUE` (`idBloc` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `compet_bloc`.`DefCategorie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compet_bloc`.`DefCategorie` (
  `idDefCategorie` INT(11) NOT NULL AUTO_INCREMENT,
  `ageConfigHomme` INT(11) NOT NULL,
  `ageConfigFemme` INT(11) NOT NULL,
  PRIMARY KEY (`idDefCategorie`),
  UNIQUE INDEX `idDefCategorie_UNIQUE` (`idDefCategorie` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `compet_bloc`.`Juge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compet_bloc`.`Juge` (
  `idJuge` INT(11) NOT NULL AUTO_INCREMENT,
  `pseudo` VARCHAR(60) NOT NULL,
  `nom` VARCHAR(20) NOT NULL,
  `prenom` VARCHAR(20) NOT NULL,
  `mdp` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idJuge`),
  UNIQUE INDEX `pseudo_UNIQUE` (`pseudo` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `compet_bloc`.`News`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compet_bloc`.`News` (
  `idNews` INT(11) NOT NULL,
  `Titre` VARCHAR(255) NULL DEFAULT NULL,
  `Contenus` LONGTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`idNews`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `compet_bloc`.`participant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compet_bloc`.`participant` (
  `idParticipant` INT(11) NOT NULL AUTO_INCREMENT,
  `dossard` VARCHAR(45) NOT NULL,
  `nom` VARCHAR(45) NOT NULL,
  `prenom` VARCHAR(45) NOT NULL,
  `age` VARCHAR(45) NOT NULL,
  `sex` VARCHAR(45) NOT NULL,
  `categorieparti` VARCHAR(45) NOT NULL,
  `resultat` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idParticipant`),
  UNIQUE INDEX `dossard_UNIQUE` (`dossard` ASC),
  UNIQUE INDEX `idParticipant_UNIQUE` (`idParticipant` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `compet_bloc`.`Participant_has_Bloc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compet_bloc`.`Participant_has_Bloc` (
  `Participant_idParticipant` INT(11) NOT NULL,
  `Bloc_idBloc` INT(11) NOT NULL,
  PRIMARY KEY (`Participant_idParticipant`, `Bloc_idBloc`),
  INDEX `fk_Participant_has_Bloc_Bloc1_idx` (`Bloc_idBloc` ASC),
  INDEX `fk_Participant_has_Bloc_Participant_idx` (`Participant_idParticipant` ASC),
  CONSTRAINT `fk_Participant_has_Bloc_Bloc1`
    FOREIGN KEY (`Bloc_idBloc`)
    REFERENCES `compet_bloc`.`Bloc` (`idBloc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Participant_has_Bloc_Participant`
    FOREIGN KEY (`Participant_idParticipant`)
    REFERENCES `compet_bloc`.`participant` (`idParticipant`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `compet_bloc`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compet_bloc`.`category` (
  `category_id` INT(11) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
