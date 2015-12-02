-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: twittermysql
-- ------------------------------------------------------
-- Server version	5.7.9-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `fav`
--

DROP TABLE IF EXISTS `fav`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fav` (
  `data` datetime DEFAULT NULL,
  `nork` varchar(20) DEFAULT NULL,
  `txioa` varchar(140) DEFAULT NULL,
  `idFav` varchar(300) NOT NULL,
  `userIzena` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idFav`),
  KEY `nameU` (`userIzena`),
  CONSTRAINT `nameU` FOREIGN KEY (`userIzena`) REFERENCES `user` (`izena`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jarraituak`
--

DROP TABLE IF EXISTS `jarraituak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jarraituak` (
  `id` varchar(100) NOT NULL,
  `userId` varchar(100) DEFAULT NULL,
  `userIzena` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `izena_idx` (`userId`),
  CONSTRAINT `izena` FOREIGN KEY (`userId`) REFERENCES `user` (`izena`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jarraitzaileak`
--

DROP TABLE IF EXISTS `jarraitzaileak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jarraitzaileak` (
  `userId` varchar(100) DEFAULT NULL,
  `userIzena` varchar(100) DEFAULT NULL,
  `id` varchar(300) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `izena_idx` (`userIzena`),
  KEY `izena_idx1` (`userId`),
  CONSTRAINT `izenUser` FOREIGN KEY (`userId`) REFERENCES `user` (`izena`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `md`
--

DROP TABLE IF EXISTS `md`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `md` (
  `data` datetime DEFAULT NULL,
  `nork` varchar(20) DEFAULT NULL,
  `mezua` varchar(140) DEFAULT NULL,
  `id` varchar(300) NOT NULL,
  `userId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `izena_idx` (`userId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`izena`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rt`
--

DROP TABLE IF EXISTS `rt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rt` (
  `data` datetime DEFAULT NULL,
  `ordua` time DEFAULT NULL,
  `nork` varchar(20) DEFAULT NULL,
  `txioa` varchar(140) DEFAULT NULL,
  `id` varchar(300) NOT NULL,
  `idRt` varchar(300) DEFAULT NULL,
  `userIzena` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `izena_idx` (`userIzena`),
  CONSTRAINT `nameUser` FOREIGN KEY (`userIzena`) REFERENCES `user` (`izena`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `accessToken` varchar(600) DEFAULT NULL,
  `accessTokenSecret` varchar(600) DEFAULT NULL,
  `consumerKey` varchar(600) DEFAULT NULL,
  `consumerKeySecret` varchar(600) DEFAULT NULL,
  `user` varchar(100) DEFAULT NULL,
  KEY `user_idx` (`user`),
  CONSTRAINT `user` FOREIGN KEY (`user`) REFERENCES `user` (`izena`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `txio`
--

DROP TABLE IF EXISTS `txio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `txio` (
  `nork` varchar(20) DEFAULT NULL,
  `txioa` varchar(140) DEFAULT NULL,
  `data` datetime DEFAULT NULL,
  `id` varchar(300) NOT NULL,
  `userI` varchar(100) DEFAULT NULL,
  `userIzena` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`userIzena`),
  CONSTRAINT `username` FOREIGN KEY (`userIzena`) REFERENCES `user` (`izena`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `izena` varchar(100) NOT NULL,
  PRIMARY KEY (`izena`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-02 10:47:30
