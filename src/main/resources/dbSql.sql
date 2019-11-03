SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema publication
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema publication
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fitness` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `fitness`;

-- client: table
CREATE TABLE IF NOT EXISTS `fitness`.`client` (
  `id_client`                   int(11)     NOT NULL AUTO_INCREMENT,
  `coach_id`                    int(11)              DEFAULT NULL,
  `name`                        varchar(45) NOT NULL,
  `surname`                     varchar(45) NOT NULL,
  `login`                       varchar(45) NOT NULL,
  `password`                    varchar(45) NOT NULL,
  `membership_purchased_number` int(11)     NOT NULL DEFAULT '0',
  `personal_discount`           float       NOT NULL DEFAULT '0',
  `program_id`                  int(11)              DEFAULT NULL,
  PRIMARY KEY (`id_client`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_client_program1_idx` (`program_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 40
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- coach: table
CREATE TABLE IF NOT EXISTS `fitness`.`coach` (
  `name`     varchar(45) NOT NULL,
  `surname`  varchar(45) NOT NULL,
  `login`    varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `id_coach` int(11)     NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_coach`),
  UNIQUE KEY `login_UNIQUE` (`login`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- comment: table
CREATE TABLE IF NOT EXISTS `fitness`.`comment` (
  `id_comment`      int(11)      NOT NULL AUTO_INCREMENT,
  `coach_id`        int(11)      NOT NULL,
  `client_id`       int(11)      NOT NULL,
  `comment_content` varchar(400) NOT NULL,
  PRIMARY KEY (`id_comment`),
  UNIQUE KEY `id_comment_UNIQUE` (`id_comment`),
  KEY `fk_comment_trainer1_idx` (`coach_id`),
  KEY `fk_comment_client1_idx` (`client_id`),
  CONSTRAINT `comment_client_id_client_fk` FOREIGN KEY (`client_id`) REFERENCES `client` (`id_client`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- exercise: table
CREATE TABLE IF NOT EXISTS `fitness`.`exercise` (
  `id_exercise` int(11)      NOT NULL AUTO_INCREMENT,
  `name`        varchar(100) NOT NULL,
  `description` varchar(300) NOT NULL,
  `image`       varchar(100) NOT NULL,
  PRIMARY KEY (`id_exercise`),
  UNIQUE KEY `id_UNIQUE` (`id_exercise`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- exercise_program: table
CREATE TABLE IF NOT EXISTS `fitness`.`exercise_program` (
  `id_exercise_program` int(11) NOT NULL AUTO_INCREMENT,
  `program_id`          int(11) NOT NULL,
  `exercise_id`         int(11) NOT NULL,
  `repeat_number`       int(11) NOT NULL,
  `set_number`          int(11) NOT NULL,
  `number_train_day`    int(11) NOT NULL,
  PRIMARY KEY (`id_exercise_program`),
  UNIQUE KEY `id_UNIQUE` (`id_exercise_program`),
  KEY `fk_table1_Program_idx` (`program_id`),
  KEY `fk_table1_exercises1_idx` (`exercise_id`),
  CONSTRAINT `exercise_program_program_id_program_fk` FOREIGN KEY (`program_id`) REFERENCES `program` (`id_program`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `exercise_program_program_id_program_fk_2` FOREIGN KEY (`program_id`) REFERENCES `program` (`id_program`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 144
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- nutrition: table
CREATE TABLE IF NOT EXISTS `fitness`.`nutrition` (
  `id_nutrition`      int(11) NOT NULL AUTO_INCREMENT,
  `name`              varchar(45)      DEFAULT NULL,
  `morning_nutrition` varchar(400),
  `dinner_nutrition`  varchar(400),
  `lunch_nutrition`   varchar(400),
  PRIMARY KEY (`id_nutrition`),
  UNIQUE KEY `idnutrition_id_UNIQUE` (`id_nutrition`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 63
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- order_information: table
CREATE TABLE IF NOT EXISTS `fitness`.`order_information` (
  `id_order_information` int(11)        NOT NULL AUTO_INCREMENT,
  `cost`                 decimal(10, 3) NOT NULL,
  `payment_data`         datetime(4)    NOT NULL,
  `membership_end_date`  date           NOT NULL,
  `client_id`            int(11)        NOT NULL,
  `card_number`          char(30)       NOT NULL,
  PRIMARY KEY (`id_order_information`),
  KEY `order_information_id_order_information_index` (`id_order_information`),
  KEY `order_information_client_id_client_fk` (`client_id`),
  CONSTRAINT `order_information_client_id_client_fk` FOREIGN KEY (`client_id`) REFERENCES `client` (`id_client`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 113
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- program: table
CREATE TABLE IF NOT EXISTS `fitness`.`program` (
  `id_program`      int(11) NOT NULL AUTO_INCREMENT,
  `nutrition_id`    int(11) NOT NULL,
  `trains_per_week` int(11) NOT NULL,
  PRIMARY KEY (`id_program`),
  KEY `program_id_program_nutrition_id_index` (`id_program`, `nutrition_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 37
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

