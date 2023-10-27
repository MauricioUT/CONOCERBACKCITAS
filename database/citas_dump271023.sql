CREATE DATABASE  IF NOT EXISTS `citas` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `citas`;
-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (arm64)
--
-- Host: localhost    Database: citas
-- ------------------------------------------------------
-- Server version	8.2.0

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

--
-- Table structure for table `c_entidades`
--

DROP TABLE IF EXISTS `c_entidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `c_entidades` (
  `id` int NOT NULL,
  `entidades` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_entidades`
--

LOCK TABLES `c_entidades` WRITE;
/*!40000 ALTER TABLE `c_entidades` DISABLE KEYS */;
INSERT INTO `c_entidades` VALUES (1,'Aguascalientes'),(2,'Baja California'),(3,'Baja California Sur'),(4,'Campeche'),(5,'Coahuila'),(6,'Colima'),(7,'Chiapas'),(8,'Chihuahua'),(9,'Ciudad de México'),(10,'Durango'),(11,'Guanajuato'),(12,'Guerrero'),(13,'Estado de Hidalgo'),(14,'Jalisco'),(15,'Estado de México'),(16,'Michoacán'),(17,'Morelos'),(18,'Nayarit'),(19,'Nuevo León'),(20,'Oaxaca'),(21,'Puebla'),(22,'Querétaro'),(23,'Quintana Roo'),(24,'San Luis Potosí'),(25,'Sinaloa'),(26,'Sonora'),(27,'Tabasco'),(28,'Tamaulipas'),(29,'Tlaxcala'),(30,'Veracruz'),(31,'Yucatán'),(32,'Zacatecas');
/*!40000 ALTER TABLE `c_entidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_estatus_videollamadas`
--

DROP TABLE IF EXISTS `c_estatus_videollamadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `c_estatus_videollamadas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_estatus_videollamadas`
--

LOCK TABLES `c_estatus_videollamadas` WRITE;
/*!40000 ALTER TABLE `c_estatus_videollamadas` DISABLE KEYS */;
INSERT INTO `c_estatus_videollamadas` VALUES (1,'Registrada'),(2,'Atendida'),(3,'Cancelada');
/*!40000 ALTER TABLE `c_estatus_videollamadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_horarios`
--

DROP TABLE IF EXISTS `c_horarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `c_horarios` (
  `ID` int NOT NULL,
  `HORARIOS` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `lhorariosemanal` smallint DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_horarios`
--

LOCK TABLES `c_horarios` WRITE;
/*!40000 ALTER TABLE `c_horarios` DISABLE KEYS */;
INSERT INTO `c_horarios` VALUES (1,'09:00:00 - 09:40:00','0',0),(2,'10:00:00 - 10:40:00','1',0),(3,'11:00:00 - 11:40:00','1',1),(4,'12:00:00 - 12:40:00','1',1),(5,'13:00:00 - 13:40:00','1',0),(6,'15:00:00 - 15:40:00','1',0),(7,'16:00:00 - 16:40:00','1',0),(8,'17:00:00 - 17:40:00','1',0),(9,'18:00:00 - 18:40:00','0',0);
/*!40000 ALTER TABLE `c_horarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_medios_conocer`
--

DROP TABLE IF EXISTS `c_medios_conocer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `c_medios_conocer` (
  `id` int NOT NULL,
  `medio` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_medios_conocer`
--

LOCK TABLES `c_medios_conocer` WRITE;
/*!40000 ALTER TABLE `c_medios_conocer` DISABLE KEYS */;
INSERT INTO `c_medios_conocer` VALUES (1,'Trabajo'),(2,'Internet'),(3,'Secretarias de Gobierno'),(4,'Amistad'),(5,'Escuela'),(6,'Curso'),(7,'Tiene una Certificación'),(8,'Anuncio');
/*!40000 ALTER TABLE `c_medios_conocer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_ocupacion_actual`
--

DROP TABLE IF EXISTS `c_ocupacion_actual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `c_ocupacion_actual` (
  `id` int NOT NULL,
  `ocupacion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_ocupacion_actual`
--

LOCK TABLES `c_ocupacion_actual` WRITE;
/*!40000 ALTER TABLE `c_ocupacion_actual` DISABLE KEYS */;
INSERT INTO `c_ocupacion_actual` VALUES (1,'Alto Ejecutivo Sector Privado'),(2,'Alto Ejecutivo Sector Público'),(3,'Empleado Sector Privado'),(4,'Empleado Sector Público'),(5,'Dueño de Negocio'),(6,'Profesionista Independiente'),(7,'Estudiante'),(8,'Desempleado'),(9,'Jubilado'),(10,'Otro');
/*!40000 ALTER TABLE `c_ocupacion_actual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `k_citas`
--

DROP TABLE IF EXISTS `k_citas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `k_citas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(300) NOT NULL,
  `empresa` varchar(45) NOT NULL,
  `id_estado` int NOT NULL,
  `id_ocupacion` int NOT NULL,
  `id_medio` int NOT NULL,
  `descripcion_solicitud` varchar(500) DEFAULT NULL,
  `email` varchar(300) NOT NULL,
  `fecha` date NOT NULL,
  `id_hora` int NOT NULL,
  `id_meet` varchar(300) NOT NULL,
  `fecha_registro` date NOT NULL,
  `no_telefono` bigint NOT NULL,
  `id_calendar` varchar(100) NOT NULL,
  `id_estatus_videollamada` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_k_citas_c_entidades_idx` (`id_estado`),
  KEY `fk_k_citas_c_ocupacion_idx` (`id_ocupacion`),
  KEY `fk_k_citas_c_horarios_idx` (`id_hora`),
  KEY `fk_k_citas_c_medios_idx` (`id_medio`),
  CONSTRAINT `fk_k_citas_c_entidades` FOREIGN KEY (`id_estado`) REFERENCES `c_entidades` (`id`),
  CONSTRAINT `fk_k_citas_c_horarios` FOREIGN KEY (`id_hora`) REFERENCES `c_horarios` (`ID`),
  CONSTRAINT `fk_k_citas_c_medios` FOREIGN KEY (`id_medio`) REFERENCES `c_medios_conocer` (`id`),
  CONSTRAINT `fk_k_citas_c_ocupacion` FOREIGN KEY (`id_ocupacion`) REFERENCES `c_ocupacion_actual` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='						';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `k_citas`
--

LOCK TABLES `k_citas` WRITE;
/*!40000 ALTER TABLE `k_citas` DISABLE KEYS */;
/*!40000 ALTER TABLE `k_citas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `k_citas_observaciones`
--

DROP TABLE IF EXISTS `k_citas_observaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `k_citas_observaciones` (
  `id_citas` bigint NOT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  `fecha_registro` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_citas`),
  CONSTRAINT `fk_k_citas_observaciones_kcitas` FOREIGN KEY (`id_citas`) REFERENCES `k_citas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `k_citas_observaciones`
--

LOCK TABLES `k_citas_observaciones` WRITE;
/*!40000 ALTER TABLE `k_citas_observaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `k_citas_observaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'citas'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-27  0:48:41
