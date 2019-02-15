-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: chatdb
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `friends` (
  `User` varchar(11) NOT NULL,
  `Friend` varchar(11) NOT NULL,
  PRIMARY KEY (`User`,`Friend`),
  KEY `Friend` (`Friend`),
  CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`User`) REFERENCES `user` (`phonenum`),
  CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`Friend`) REFERENCES `user` (`phonenum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES ('01234567891','01234568795');
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `requests` (
  `Sender` varchar(11) NOT NULL,
  `Receiver` varchar(11) NOT NULL,
  PRIMARY KEY (`Sender`,`Receiver`),
  KEY `Receiver` (`Receiver`),
  CONSTRAINT `requests_ibfk_1` FOREIGN KEY (`Sender`) REFERENCES `user` (`phonenum`),
  CONSTRAINT `requests_ibfk_2` FOREIGN KEY (`Receiver`) REFERENCES `user` (`phonenum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `Name` varchar(50) NOT NULL,
  `PhoneNum` varchar(11) NOT NULL,
  `Gender` enum('F','M') NOT NULL,
  `Country` varchar(50) NOT NULL,
  `DOB` date NOT NULL,
  `Picture` blob,
  `Password` varchar(50) NOT NULL,
  `Status` varchar(10) NOT NULL,
  `ChatBotStatus` tinyint(4) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `BIO` varchar(200) NOT NULL,
  `Mode` enum('available','busy','away') NOT NULL,
  PRIMARY KEY (`PhoneNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('mai','000000','F','egypt','1995-06-03',NULL,'mai','offline',1,'mai','bioo','available'),('amrr','00000000','M','Egypt','1996-08-08',NULL,'0000','offline',1,'amr@gmail.com','heloooooooo','available'),('amr','0111','M','egypt','1999-02-02',NULL,'0000','offline',1,'amrhesham@yahoo.com','hello','available'),('Amr Hesham','01234567891','M','Egypt','1995-02-16',NULL,'123456789','Offline',0,'AmrHesham@gmail.com','Hello','available'),('Sahar','01234568795','F','Egypt','1996-07-08',NULL,'213456879','Offline',0,'SaharHany@gmail.com','Hiii','available'),('0202','0202','M','egypt','1996-02-02',NULL,'hesham','offline',1,'aa','biooo','available'),('020202','020202','M','egypt','1996-02-02',NULL,'hesham','offline',1,'aa','biooo','available'),('0333','0333','M','egypt','1996-02-02',NULL,'hesham','offline',1,'aa','biooo','available'),('066066','066066','F','egypt','1996-02-02',NULL,'mostafa','offline',1,'email','bioooo','available'),('0999','0999','M','country','1995-02-02',NULL,'password','offline',1,'email','bio','available'),('amr','12345','M','egypt','1999-02-02',NULL,'0000','offline',1,'amrhesham@yahoo.com','hello','available'),('kamal','8989','M','germany','1999-09-19',NULL,'123','offline',1,'admm@gmail.com','Hello','available');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-15  8:02:35
