-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: b9vcqk173yhmvlp7pkrj-mysql.services.clever-cloud.com    Database: b9vcqk173yhmvlp7pkrj
-- ------------------------------------------------------
-- Server version	8.0.15-5

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'f41d366d-91e5-11e9-8525-cecd028ee826:1-138973816';

--
-- Table structure for table `coder`
--

DROP TABLE IF EXISTS `coder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coder` (
  `id_coder` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `documento` varchar(45) NOT NULL,
  `cohorte` int(11) NOT NULL,
  `CV` text NOT NULL,
  `clan` varchar(60) NOT NULL,
  PRIMARY KEY (`id_coder`),
  UNIQUE KEY `documento` (`documento`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coder`
--

LOCK TABLES `coder` WRITE;
/*!40000 ALTER TABLE `coder` DISABLE KEYS */;
INSERT INTO `coder` VALUES (2,'luis','flaco','2233',2,'sql','meta'),(3,'p','s','2222',1,'java ','lovelace'),(4,'luis','sumares','222',1,'limpiador','meta'),(6,'miguel','torres','6666',1,'comercial','lovelace');
/*!40000 ALTER TABLE `coder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contratacion`
--

DROP TABLE IF EXISTS `contratacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contratacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vacante_id_fk` int(11) NOT NULL,
  `coder_id_fk` int(11) NOT NULL,
  `fecha_aplicacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` varchar(50) NOT NULL,
  `salario` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_vacante` (`vacante_id_fk`),
  KEY `fk_id_coder` (`coder_id_fk`),
  CONSTRAINT `fk_id_coder` FOREIGN KEY (`coder_id_fk`) REFERENCES `coder` (`id_coder`),
  CONSTRAINT `fk_id_vacante` FOREIGN KEY (`vacante_id_fk`) REFERENCES `vacante` (`id_vacante`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contratacion`
--

LOCK TABLES `contratacion` WRITE;
/*!40000 ALTER TABLE `contratacion` DISABLE KEYS */;
INSERT INTO `contratacion` VALUES (5,8,4,'2024-04-09 17:47:55','Activo',1555500.00),(6,5,6,'2024-04-09 18:02:16','Activo',2000000.00);
/*!40000 ALTER TABLE `contratacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `id_empresa` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_empresa` varchar(45) NOT NULL,
  `sector` varchar(60) NOT NULL,
  `ubicacion` varchar(80) NOT NULL,
  `contacto` varchar(50) NOT NULL,
  PRIMARY KEY (`id_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (2,'BanColombia','Economico','Industrialies','222222'),(3,'Exito','Ventas','Centro','21111');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacante`
--

DROP TABLE IF EXISTS `vacante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vacante` (
  `id_vacante` int(11) NOT NULL AUTO_INCREMENT,
  `empresa_id_fk` int(11) NOT NULL,
  `titulo` varchar(70) NOT NULL,
  `descripcion` text NOT NULL,
  `duracion` varchar(50) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `tecnologia` varchar(60) NOT NULL,
  PRIMARY KEY (`id_vacante`),
  KEY `fk_id_empresa` (`empresa_id_fk`),
  CONSTRAINT `fk_id_empresa` FOREIGN KEY (`empresa_id_fk`) REFERENCES `empresa` (`id_empresa`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacante`
--

LOCK TABLES `vacante` WRITE;
/*!40000 ALTER TABLE `vacante` DISABLE KEYS */;
INSERT INTO `vacante` VALUES (2,3,'ventas','vende','1 años','Activo','ventas'),(3,2,'limpeza','lipia','6 meses','Activo','aseo'),(4,2,'','s','s','Inactivo','ss'),(5,3,'ventas','vender','1año','Inactivo','comercial'),(6,2,'aaa','aaa','aaa','Activo','aa'),(7,2,'no ','no','no','Inactivo','no'),(8,2,'limpieza','lipia','2','Inactivo','limpiador');
/*!40000 ALTER TABLE `vacante` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-09 13:23:35
