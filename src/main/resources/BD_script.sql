-- MySQL Script generated by MySQL Workbench
-- Thu Jul 25 00:14:52 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema it_project
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `it_project` ;

-- -----------------------------------------------------
-- Schema it_project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `it_project` DEFAULT CHARACTER SET utf8 ;
USE `it_project` ;

-- -----------------------------------------------------
-- Table `it_project`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `it_project`.`user` (
  `id` BINARY(16) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surnames` VARCHAR(45) NOT NULL,
  `role` ENUM('STUDENT', 'TEACHER', 'IT_ADMIN') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `it_project`.`itinerary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `it_project`.`itinerary` (
  `id` BINARY(16) NOT NULL,
  `name` ENUM('FRONTEND', 'JAVA', 'DOT_NET', 'ANDROID') NOT NULL,
  `teacher` BINARY(16) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_itinerary_teacher_idx` (`teacher` ASC),
  CONSTRAINT `FK_itinerary_teacher`
    FOREIGN KEY (`teacher`)
    REFERENCES `it_project`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `it_project`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `it_project`.`student` (
  `id` BINARY(16) NOT NULL,
  `mail` VARCHAR(45) NOT NULL,
  `sex` ENUM('M', 'F') NOT NULL,
  `conclusion` ENUM('ELIGIBLE', 'FINISHED', 'WORKING') NOT NULL,
  `start_date` DATE NOT NULL,
  `deadline` DATE NOT NULL,
  `desk` INT NULL DEFAULT '0',
  `itinerary` BINARY(16) NULL,
  `interview_result` VARCHAR(45) NULL DEFAULT 'NA',
  `interview_teacher` BINARY(16) NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_interview_teacher_idx` (`interview_teacher` ASC),
  INDEX `FK_itinerary_idx` (`itinerary` ASC),
  CONSTRAINT `FK_student_id`
    FOREIGN KEY (`id`)
    REFERENCES `it_project`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_interview_teacher`
    FOREIGN KEY (`interview_teacher`)
    REFERENCES `it_project`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_itinerary`
    FOREIGN KEY (`itinerary`)
    REFERENCES `it_project`.`itinerary` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `it_project`.`student_absences`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `it_project`.`student_absences` (
  `student` BINARY(16) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`student`, `date`),
  CONSTRAINT `FK_student_absences`
    FOREIGN KEY (`student`)
    REFERENCES `it_project`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `it_project`.`historic_student_itinerary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `it_project`.`historic_student_itinerary` (
  `student` BINARY(16) NOT NULL,
  `itinerary` BINARY(16) NOT NULL,
  `end_date` DATE NOT NULL,
  PRIMARY KEY (`student`, `itinerary`),
  INDEX `FK_historic_itinerary_idx` (`itinerary` ASC),
  CONSTRAINT `FK_historic_student_itinerary`
    FOREIGN KEY (`student`)
    REFERENCES `it_project`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_historic_itinerary`
    FOREIGN KEY (`itinerary`)
    REFERENCES `it_project`.`itinerary` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `it_project`.`exercise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `it_project`.`exercise` (
  `id` BINARY(16) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `is_common` TINYINT NOT NULL,
  `order` INT NOT NULL,
  `itinerary` BINARY(16) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `FK_exercise_itinerary_idx` (`itinerary` ASC),
  CONSTRAINT `FK_exercise_itinerary`
    FOREIGN KEY (`itinerary`)
    REFERENCES `it_project`.`itinerary` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `it_project`.`current_delivery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `it_project`.`current_delivery` (
  `student` BINARY(16) NOT NULL,
  `exercise` BINARY(16) NOT NULL,
  `state` ENUM('IN_REVISION', 'CORRECTED', 'WITH_ERRORS') NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`student`, `date`, `exercise`, `state`),
  INDEX `FK_exercise_current_delivery_idx` (`exercise` ASC),
  CONSTRAINT `FK_student_current_delivery`
    FOREIGN KEY (`student`)
    REFERENCES `it_project`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_exercise_current_delivery`
    FOREIGN KEY (`exercise`)
    REFERENCES `it_project`.`exercise` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `it_project`.`historic_delivery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `it_project`.`historic_delivery` (
  `student` BINARY(16) NOT NULL,
  `exercise` BINARY(16) NOT NULL,
  `state` ENUM('IN_REVISION', 'CORRECTED', 'WITH_ERRORS') NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`student`, `state`, `date`, `exercise`),
  INDEX `FK_exercise_historic_delivery_idx` (`exercise` ASC),
  CONSTRAINT `FK_student_historic_delivery`
    FOREIGN KEY (`student`)
    REFERENCES `it_project`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_exercise_historic_delivery`
    FOREIGN KEY (`exercise`)
    REFERENCES `it_project`.`exercise` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
