-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: banco
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `ID_USER` varchar(9) COLLATE utf8mb4_unicode_ci NOT NULL,
  `NOMBRE` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `PASSWORD` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ACTIVO` tinyint(4) NOT NULL DEFAULT '0',
  `CODIGO_ACTIVACION` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `FECHA_ACTIVACION` datetime(6) NOT NULL,
  `EMAIL` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`ID_USER`),
  UNIQUE KEY `CODIGO_ACTIVACION_UNIQUE` (`CODIGO_ACTIVACION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('000000001','SuperAdmin','1000:d7d56e7ba1a936c87d2753a51dd0aa2085515ff43156a91c:78c64f4f040ef2a59bc1183b6a240c2e7e7b4dc60238f70a',1,'No_Necesita_Codigo','2017-12-24 14:30:30.000000','superadmin@admin.es'),('11111111A','Antonio Perez','nohay2sin3',1,'inicial1','2018-01-15 00:18:42.389000','tierno@galvan.es'),('11111112B','Santiago Alonso','nohay2sin3',1,'inicial2','2018-01-15 00:18:42.389000','tierno@galvan.es'),('12341234T','pepe','1000:ed93a5024fbdecbddb4944ac03dd0ceddc6d53f75b6485e9:e04a110eaa86d4944dbdc1680606696307308bd7072a219c',1,'AEB95ae24ca2c4ea7D6e','2018-03-08 00:40:29.602000','pepe@pepe.es'),('22222222C','Julian Orozco','nohay2sin3',1,'inicial3','2018-01-15 00:18:42.389000','tierno@galvan.es'),('33333333D','Pedro Aranguren','nohay2sin3',1,'inicial4','2018-01-15 00:18:42.389000','tierno@galvan.es'),('47531285Z','Ismael','1000:ddfe7caf6c5ba461321d8dc47c9254137ee0d53326963e25:2dd176752611fbe55c45c52a172b5cecc8cf628a47bf877a',1,'d65C6E92E3d0d17B638d','2018-03-07 18:23:58.782000','eduardogn11@gmail.com'),('47531286S','Eduardo Gonzalez','1234',1,'inicialPrueba','2018-01-15 00:18:42.389000','eduardo.gn.daw@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-10  7:07:26
