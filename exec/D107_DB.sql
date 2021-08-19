-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ssafy_web_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafy_web_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafy_web_db` DEFAULT CHARACTER SET utf8mb4 ;
USE `ssafy_web_db` ;

-- -----------------------------------------------------
-- Table `ssafy_web_db`.`busking_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_web_db`.`busking_genre` ;

CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`busking_genre` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_web_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_web_db`.`user` ;

CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `user_id` VARCHAR(255) NULL DEFAULT NULL,
  `certified` TINYINT NULL DEFAULT NULL,
  `isAdmin` TINYINT NULL DEFAULT NULL,
  `phone` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_web_db`.`busking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_web_db`.`busking` ;

CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`busking` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `owner_id` BIGINT NOT NULL,
  `busking_genre` BIGINT NOT NULL,
  `start_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `end_time` TIMESTAMP NULL DEFAULT NULL,
  `thumbnail_url` VARCHAR(255) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `is_active` TINYINT(1) NULL DEFAULT NULL,
  `location` VARCHAR(255) NULL DEFAULT NULL,
  `likes` INT NULL DEFAULT NULL,
  `viewers` INT NULL DEFAULT NULL,
  `max_viewers` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_user_TO_busking_1` (`owner_id` ASC) VISIBLE,
  INDEX `fk_busking_busking_genre1_idx` (`busking_genre` ASC) VISIBLE,
  CONSTRAINT `fk_busking_busking_genre1`
    FOREIGN KEY (`busking_genre`)
    REFERENCES `ssafy_web_db`.`busking_genre` (`id`),
  CONSTRAINT `FK_user_TO_busking_1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `ssafy_web_db`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_web_db`.`busking_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_web_db`.`busking_history` ;

CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`busking_history` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `u_id` BIGINT NOT NULL,
  `b_id` BIGINT NOT NULL,
  `action` SMALLINT NULL DEFAULT NULL,
  `inserted_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_user_TO_busking_history_1` (`u_id` ASC) VISIBLE,
  INDEX `fk_busking_history_busking1_idx` (`b_id` ASC) VISIBLE,
  CONSTRAINT `fk_busking_history_busking1`
    FOREIGN KEY (`b_id`)
    REFERENCES `ssafy_web_db`.`busking` (`id`),
  CONSTRAINT `FK_user_TO_busking_history_1`
    FOREIGN KEY (`u_id`)
    REFERENCES `ssafy_web_db`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_web_db`.`fav_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_web_db`.`fav_genre` ;

CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`fav_genre` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `u_id` BIGINT NOT NULL,
  `g_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_fav_genre_user_idx` (`u_id` ASC) VISIBLE,
  INDEX `fk_fav_genre_busking_genre1_idx` (`g_id` ASC) VISIBLE,
  CONSTRAINT `fk_fav_genre_busking_genre1`
    FOREIGN KEY (`g_id`)
    REFERENCES `ssafy_web_db`.`busking_genre` (`id`),
  CONSTRAINT `fk_fav_genre_user`
    FOREIGN KEY (`u_id`)
    REFERENCES `ssafy_web_db`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_web_db`.`kickout_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_web_db`.`kickout_info` ;

CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`kickout_info` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `u_id` BIGINT NOT NULL,
  `b_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_kickout_info_user_idx` (`u_id` ASC) VISIBLE,
  INDEX `fk_kickout_info_busking1_idx` (`b_id` ASC) VISIBLE,
  CONSTRAINT `fk_kickout_info_busking1`
    FOREIGN KEY (`b_id`)
    REFERENCES `ssafy_web_db`.`busking` (`id`),
  CONSTRAINT `fk_kickout_info_user`
    FOREIGN KEY (`u_id`)
    REFERENCES `ssafy_web_db`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_web_db`.`liked`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_web_db`.`liked` ;

CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`liked` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `u_id` BIGINT NOT NULL,
  `b_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_liked_user1_idx` (`u_id` ASC) VISIBLE,
  INDEX `fk_liked_busking1_idx` (`b_id` ASC) VISIBLE,
  CONSTRAINT `fk_liked_busking1`
    FOREIGN KEY (`b_id`)
    REFERENCES `ssafy_web_db`.`busking` (`id`),
  CONSTRAINT `fk_liked_user1`
    FOREIGN KEY (`u_id`)
    REFERENCES `ssafy_web_db`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `ssafy_web_db`.`report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_web_db`.`report` ;

CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`report` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_web_db`.`user_busking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_web_db`.`user_busking` ;

CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`user_busking` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `u_id` BIGINT NOT NULL,
  `b_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_user_TO_user_busking_1` (`u_id` ASC) VISIBLE,
  INDEX `FK_busking_TO_user_busking_1` (`b_id` ASC) VISIBLE,
  CONSTRAINT `FK_busking_TO_user_busking_1`
    FOREIGN KEY (`b_id`)
    REFERENCES `ssafy_web_db`.`busking` (`id`),
  CONSTRAINT `FK_user_TO_user_busking_1`
    FOREIGN KEY (`u_id`)
    REFERENCES `ssafy_web_db`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_web_db`.`user_report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_web_db`.`user_report` ;

CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`user_report` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `report_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_report_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_user_report_report1_idx` (`report_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_report_report1`
    FOREIGN KEY (`report_id`)
    REFERENCES `ssafy_web_db`.`report` (`id`),
  CONSTRAINT `fk_user_report_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafy_web_db`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
