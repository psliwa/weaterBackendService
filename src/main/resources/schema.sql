CREATE TABLE IF NOT EXISTS `city` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
    PRIMARY KEY(`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `observation` (
    `cityId` INT NOT NULL,
    `date` DATETIME NOT NULL,
    `type` ENUM('DETAIL', 'SUMMARY') NOT NULL,
    `windSpeed` DECIMAL(5, 2) NOT NULL,
    `temperature` DECIMAL(4, 2) NOT NULL,
    `windchillTemperature` DECIMAL(5, 2) NULL,
    `humidity` SMALLINT NOT NULL,
    `visibility` DECIMAL(10, 2) NOT NULL,
    `pressure` INT NOT NULL,
    `fog` BIT NOT NULL,
    `rain` BIT NOT NULL,
    `snow` BIT NOT NULL,
    `hail` BIT NOT NULL,
    `thunder` BIT NOT NULL,
    `tornado` BIT NOT NULL,
    PRIMARY KEY(`cityId`, `date`, `type`)
) ENGINE=InnoDB;

ALTER TABLE `observation` ADD CONSTRAINT `city_idx` FOREIGN KEY (`cityId`) REFERENCES `city` (`id`) ON DELETE RESTRICT;

INSERT INTO `city` (`name`) VALUES ("Kraków"), ("Warszawa"), ("Wrocław"), ("Bydgoszcz"), ("Łódź"), ("Opole"), ("Rzeszów"), ("Białystok"), ("Gdańsk"), ("Katowice"), ("Kielce"), ("Olsztyn"), ("Poznań"), ("Szczecin");