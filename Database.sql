CREATE SCHEMA `lab1_db` ;
CREATE TABLE `lab1_db`.`hashtag` (
 `Name` VARCHAR(45) NOT NULL,
 `Date` DATETIME NOT NULL,
 PRIMARY KEY (`Name`));
CREATE TABLE `lab1_db`.`hashtagfeed` (
 `ID` INT NOT NULL AUTO_INCREMENT,
 `time` DATETIME NOT NULL,
 `parentFeedID` INT NOT NULL,
 `hashtag` VARCHAR(45) NOT NULL,
 `content` VARCHAR(45) NOT NULL,
 PRIMARY KEY (`ID`),
 INDEX `hashtag_idx` (`hashtag` ASC),
 CONSTRAINT `hashtag`
 FOREIGN KEY (`hashtag`)
 REFERENCES `lab1_db`.`hashtag` (`Name`)
 ON DELETE NO ACTION
 ON UPDATE NO ACTION);