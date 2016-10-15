-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Directores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Directores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Peliculas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Peliculas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NULL,
  `genero` VARCHAR(45) NULL,
  `duracion` INT NULL,
  `director_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Pelicula_Director1_idx` (`director_id` ASC),
  CONSTRAINT `fk_Pelicula_Director1`
    FOREIGN KEY (`director_id`)
    REFERENCES `mydb`.`Directores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cintas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cintas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pelicula_id` INT NULL,
  `numero_copia` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Cintas_Pelicula1_idx` (`pelicula_id` ASC),
  CONSTRAINT `fk_Cintas_Pelicula1`
    FOREIGN KEY (`pelicula_id`)
    REFERENCES `mydb`.`Peliculas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Actores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Actores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Empleado` (
  `idEmpleado` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `direccion` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  `contraseña` VARCHAR(45) NULL,
  `videclub_id` INT NOT NULL,
  PRIMARY KEY (`idEmpleado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Peliculas_actores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Peliculas_actores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pelicula_id` INT NULL,
  `actor_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Pelicula_has_Actor_Actor1_idx` (`actor_id` ASC),
  INDEX `fk_Pelicula_has_Actor_Pelicula1_idx` (`pelicula_id` ASC),
  CONSTRAINT `fk_Pelicula_has_Actor_Pelicula1`
    FOREIGN KEY (`pelicula_id`)
    REFERENCES `mydb`.`Peliculas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pelicula_has_Actor_Actor1`
    FOREIGN KEY (`actor_id`)
    REFERENCES `mydb`.`Actores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Socios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Socios` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `direccion` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Fichas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Fichas` (
  `id` INT NOT NULL,
  `socio_id` INT NOT NULL,
  `fecha_prestamo` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Ficha_Socio1_idx` (`socio_id` ASC),
  CONSTRAINT `fk_Ficha_Socio1`
    FOREIGN KEY (`socio_id`)
    REFERENCES `mydb`.`Socios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cintas_fichas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cintas_fichas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ficha_id` INT NULL,
  `dvd_id` INT NULL,
  `fecha_entrega` DATE NULL,
  `estatus` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Ficha_has_Dvd_Dvd1_idx` (`dvd_id` ASC),
  INDEX `fk_Ficha_has_Dvd_Ficha1_idx` (`ficha_id` ASC),
  CONSTRAINT `fk_Ficha_has_Dvd_Ficha1`
    FOREIGN KEY (`ficha_id`)
    REFERENCES `mydb`.`Fichas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ficha_has_Dvd_Dvd1`
    FOREIGN KEY (`dvd_id`)
    REFERENCES `mydb`.`Cintas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Directores_socios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Directores_socios` (
  `socio_id` INT NOT NULL,
  `director_id` INT NOT NULL,
  `id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`socio_id`, `director_id`, `id`),
  INDEX `fk_Socio_has_Director_Director1_idx` (`director_id` ASC),
  INDEX `fk_Socio_has_Director_Socio1_idx` (`socio_id` ASC),
  CONSTRAINT `fk_Socio_has_Director_Socio1`
    FOREIGN KEY (`socio_id`)
    REFERENCES `mydb`.`Socios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Socio_has_Director_Director1`
    FOREIGN KEY (`director_id`)
    REFERENCES `mydb`.`Directores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Actores_socios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Actores_socios` (
  `id` INT NOT NULL,
  `socio_id` INT NOT NULL,
  `actor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Actor_has_Socio_Socio1_idx` (`socio_id` ASC),
  INDEX `fk_Actor_has_Socio_Actor1_idx` (`actor_id` ASC),
  CONSTRAINT `fk_Actor_has_Socio_Actor1`
    FOREIGN KEY (`actor_id`)
    REFERENCES `mydb`.`Actores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Actor_has_Socio_Socio1`
    FOREIGN KEY (`socio_id`)
    REFERENCES `mydb`.`Socios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Listas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Listas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Socio_id` INT NULL,
  `fecha` DATE NULL,
  `estatus` VARCHAR(45) NULL,
  `peliculas_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Listas_Socio1_idx` (`Socio_id` ASC),
  INDEX `fk_Listas_Pelicula1_idx` (`peliculas_id` ASC),
  CONSTRAINT `fk_Listas_Socio1`
    FOREIGN KEY (`Socio_id`)
    REFERENCES `mydb`.`Socios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Listas_Pelicula1`
    FOREIGN KEY (`peliculas_id`)
    REFERENCES `mydb`.`Peliculas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
