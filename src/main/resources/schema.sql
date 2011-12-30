CREATE TABLE IF NOT EXISTS `city` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
    PRIMARY KEY(`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `observation` (
    `cityId` INT NOT NULL,
    `date` DATETIME NOT NULL,
    `year` INT,
    `month` SMALLINT,
    `dayOfMonth` SMALLINT,
    `type` ENUM('DETAIL', 'SUMMARY') NOT NULL,
    `windSpeed` DECIMAL(5, 2),
    `temperature` DECIMAL(4, 2),
    `windchillTemperature` DECIMAL(5, 2),
    `humidity` SMALLINT,
    `visibility` DECIMAL(10, 2),
    `pressure` INT NOT,
    `fog` BIT NOT,
    `rain` BIT NOT,
    `snow` BIT NOT,
    `hail` BIT NOT,
    `thunder` BIT NOT,
    `tornado` BIT NOT,
    PRIMARY KEY(`cityId`, `date`, `type`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `forecast` (
    `cityId` INT NOT NULL,
    `date` DATETIME NOT NULL,
    `maxTemperature` DECIMAL(5, 2),
    `minTemperature` DECIMAL(5, 2),
    `windSpeed` DECIMAL(5, 2),
    `humidity` SMALLINT,
    `snowAll` DECIMAL(5, 2),
    `snowDay` DECIMAL(5, 2),
    `snowNight` DECIMAL(5, 2),
    `rainAll` DECIMAL(5, 2),
    `rainDay` DECIMAL(5, 2),
    `rainNight` DECIMAL(5, 2),
    PRIMARY KEY (`cityId`, `date`)
) ENGINE=InnoDB;

ALTER TABLE `observation` ADD CONSTRAINT `city_idx` FOREIGN KEY (`cityId`) REFERENCES `city` (`id`) ON DELETE RESTRICT;
ALTER TABLE `forecast` ADD CONSTRAINT `city_idx2` FOREIGN KEY (`cityId`) REFERENCES `city` (`id`) ON DELETE RESTRICT;

INSERT INTO `city` (`name`) VALUES ("Kraków"), ("Warszawa"), ("Wrocław"), ("Bydgoszcz"), ("Łódź"), ("Opole"), ("Rzeszów"), ("Białystok"), ("Gdańsk"), ("Katowice"), ("Kielce"), ("Olsztyn"), ("Poznań"), ("Szczecin");