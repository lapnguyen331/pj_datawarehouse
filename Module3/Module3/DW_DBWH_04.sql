CREATE DATABASE  IF NOT EXISTS `dbstaging` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dbstaging`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbstaging
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `staging`
--

DROP TABLE IF EXISTS `staging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staging` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `model_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `price_range` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `name` varchar(500) DEFAULT NULL,
  `engine_capacity` varchar(255) DEFAULT NULL,
  `engine_type` varchar(255) DEFAULT NULL,
  `transmission_type` varchar(255) DEFAULT NULL,
  `features` text,
  `image_url` text,
  `source_url` text,
  `source_pid` int DEFAULT NULL,
  `source_SkuId` text,
  `source_name` text,
  `status` varchar(255) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staging`
--

LOCK TABLES `staging` WRITE;
/*!40000 ALTER TABLE `staging` DISABLE KEYS */;
INSERT INTO `staging` VALUES (241,'','XE SỐ','N/A',16000000,'N/A','DAELIM','','DAELIM SIRIUS','','','','','https://product.hstatic.net/200000281285/product/daelim-sirius-trang_50c38e0b00da4f53a7feb57daa622b90_master.jpg','https://hoangcau.com/products/daelim-sirius',0,'N/A','hoangcau.com','active','2024-12-05 06:03:14'),(242,'','XE TAY GA','N/A',23000000,'N/A','DAELIM','','DAELIM CANELY','','','','','https://product.hstatic.net/200000281285/product/daelim-canely-denmo_a842ea4a0a1f476f84d393289d6eb781_master.jpg','https://hoangcau.com/products/dealim-canely',0,'N/A','hoangcau.com','active','2024-12-05 06:03:14'),(243,'','XE ĐIỆN','N/A',21490000,'N/A','YADEA','','YADEA ODORA PRO','','','','','https://product.hstatic.net/200000281285/product/11_8ff3c7977f19467c9fa7fb47e3a64daa_master.png','https://hoangcau.com/products/yadea-odora-pro',0,'N/A','hoangcau.com','active','2024-12-05 06:03:14'),(244,'','XE ĐIỆN','N/A',17490000,'N/A','YADEA','','YADEA XMEN NEO','','','','','https://product.hstatic.net/200000281285/product/13_49bb009058e349ddbd34cdf59c343bfa_master.png','https://hoangcau.com/products/yadea-xmen-neo',0,'N/A','hoangcau.com','active','2024-12-05 06:03:14'),(245,'','XE ĐIỆN','N/A',22990000,'N/A','YADEA','','YADEA ORIS','','','','','https://product.hstatic.net/200000281285/product/12_b0da07eaad804df9a10c6c3fc7ca2819_master.png','https://hoangcau.com/products/yadea-oris',0,'N/A','hoangcau.com','active','2024-12-05 06:03:14'),(246,'','XE ĐIỆN','N/A',21990000,'N/A','YADEA','','YADEA OSSY','','','','','https://product.hstatic.net/200000281285/product/12_2da2b6cfffc04f85b40ab2c77621df34_master.png','https://hoangcau.com/products/yadea-ossy',0,'N/A','hoangcau.com','active','2024-12-05 06:03:14'),(247,'','XE TAY GA','N/A',23000000,'N/A','DAELIM','','DAELIM VENUS','','','','','https://product.hstatic.net/200000281285/product/daelim-venus-xanhanhkim_a3e11ac486d6429c9c2d4f87796d45a5_master.jpg','https://hoangcau.com/products/venus',0,'N/A','hoangcau.com','active','2024-12-05 06:03:14'),(248,'','XE SỐ','N/A',18000000,'N/A','DAELIM','','DAELIM C6 PLUS','','','','','https://product.hstatic.net/200000281285/product/18_7ba20a7192ba44bab669e2c2bc27707c_master.png','https://hoangcau.com/products/daelim-c6-plus',0,'N/A','hoangcau.com','active','2024-12-05 06:03:14'),(249,'','XE TAY GA','Xám Nhám',45900000,'N/A','YAMAHA','','LEXI 155VVA','','','','','https://product.hstatic.net/200000281285/product/14_eafd795d76444c868a352541d4f5c4cb_master.png','https://hoangcau.com/products/lexi-155vva',0,'N/A','hoangcau.com','active','2024-12-05 06:03:14'),(250,'','XE ĐIỆN','N/A',34000000,'N/A','YAMAHA','','NEO\'S','','','','','https://product.hstatic.net/200000281285/product/5_314c6810f2c44bfca604237533c79a5f_master.png','https://hoangcau.com/products/neo-s',0,'N/A','hoangcau.com','active','2024-12-05 06:03:14'),(251,'','XE SỐ','N/A',77000000,'N/A','YAMAHA','','XS155R','','','','','https://product.hstatic.net/200000281285/product/2_5b60169850884198a8661b80cfb87073_master.png','https://hoangcau.com/products/xs155r',0,'N/A','hoangcau.com','active','2024-12-05 06:03:14'),(252,'','XE SỐ','N/A',29000000,'N/A','YAMAHA','','PG - 1','','','','','https://product.hstatic.net/200000281285/product/z4935871594034_a26fbe43b3e108f2aa16d365c49752c9_5dd867beb1d84ffcac90be66aa12aa71_master.jpg','https://hoangcau.com/products/pg-1',0,'N/A','hoangcau.com','active','2024-12-05 06:03:14');
/*!40000 ALTER TABLE `staging` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staging_temp`
--

DROP TABLE IF EXISTS `staging_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staging_temp` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `model_name` text,
  `type` text,
  `color` text,
  `price` text,
  `price_range` text,
  `brand` text,
  `version` text,
  `name` text,
  `engine_capacity` text,
  `engine_type` text,
  `transmission_type` text,
  `features` text,
  `image_url` text,
  `source_url` text,
  `source_pid` text,
  `source_SkuId` text,
  `source_name` text,
  `status` text,
  `create_at` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staging_temp`
--

LOCK TABLES `staging_temp` WRITE;
/*!40000 ALTER TABLE `staging_temp` DISABLE KEYS */;
/*!40000 ALTER TABLE `staging_temp` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-10  5:22:25
CREATE DATABASE  IF NOT EXISTS `dbwarehouse` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dbwarehouse`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbwarehouse
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `averagepricebybrand`
--

DROP TABLE IF EXISTS `averagepricebybrand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `averagepricebybrand` (
  `brand_id` int NOT NULL,
  `average_price` float DEFAULT NULL,
  PRIMARY KEY (`brand_id`),
  CONSTRAINT `averagepricebybrand_ibfk_1` FOREIGN KEY (`brand_id`) REFERENCES `branddim` (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `averagepricebybrand`
--

LOCK TABLES `averagepricebybrand` WRITE;
/*!40000 ALTER TABLE `averagepricebybrand` DISABLE KEYS */;
INSERT INTO `averagepricebybrand` VALUES (1,20000000),(2,20990000),(3,46475000);
/*!40000 ALTER TABLE `averagepricebybrand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `averagepricebysource`
--

DROP TABLE IF EXISTS `averagepricebysource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `averagepricebysource` (
  `source_id` int NOT NULL,
  `average_price` float DEFAULT NULL,
  PRIMARY KEY (`source_id`),
  CONSTRAINT `averagepricebysource_ibfk_1` FOREIGN KEY (`source_id`) REFERENCES `sourcedim` (`source_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `averagepricebysource`
--

LOCK TABLES `averagepricebysource` WRITE;
/*!40000 ALTER TABLE `averagepricebysource` DISABLE KEYS */;
INSERT INTO `averagepricebysource` VALUES (1,16000000),(2,23000000),(3,21490000),(4,17490000),(5,22990000),(6,21990000),(7,23000000),(8,18000000),(9,45900000),(10,34000000),(11,77000000),(12,29000000);
/*!40000 ALTER TABLE `averagepricebysource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branddim`
--

DROP TABLE IF EXISTS `branddim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branddim` (
  `brand_id` int NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) NOT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branddim`
--

LOCK TABLES `branddim` WRITE;
/*!40000 ALTER TABLE `branddim` DISABLE KEYS */;
INSERT INTO `branddim` VALUES (1,'DAELIM'),(2,'YADEA'),(3,'YAMAHA');
/*!40000 ALTER TABLE `branddim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datedim`
--

DROP TABLE IF EXISTS `datedim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datedim` (
  `date_sk` int NOT NULL AUTO_INCREMENT,
  `full_date` date NOT NULL,
  `day_since_2010` int NOT NULL,
  `month_since_2010` int NOT NULL,
  `day_of_week` varchar(10) NOT NULL,
  `calendar_month` varchar(10) NOT NULL,
  `calendar_year` int NOT NULL,
  `calendar_year_month` varchar(10) NOT NULL,
  `day_of_month` int NOT NULL,
  `day_of_year` int NOT NULL,
  `week_of_year_sunday` int NOT NULL,
  `year_week_sunday` varchar(10) NOT NULL,
  `week_sunday_start` date NOT NULL,
  `week_of_year_monday` int NOT NULL,
  `year_week_monday` varchar(10) NOT NULL,
  `week_monday_start` date NOT NULL,
  `holiday` varchar(15) NOT NULL DEFAULT 'Weekday',
  `day_type` varchar(10) NOT NULL,
  PRIMARY KEY (`date_sk`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datedim`
--

LOCK TABLES `datedim` WRITE;
/*!40000 ALTER TABLE `datedim` DISABLE KEYS */;
INSERT INTO `datedim` VALUES (1,'2024-12-05',5452,180,'THURSDAY','DECEMBER',2024,'202412',5,340,49,'202449','2024-12-08',49,'202449','2024-12-02','Weekday','Workday');
/*!40000 ALTER TABLE `datedim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factprice`
--

DROP TABLE IF EXISTS `factprice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factprice` (
  `fact_price_id` int NOT NULL,
  `motobike_id` int DEFAULT NULL,
  `price_id` int DEFAULT NULL,
  `date_sk` int DEFAULT NULL,
  `source_id` int DEFAULT NULL,
  `brand_id` int DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  PRIMARY KEY (`fact_price_id`),
  KEY `motobike_id` (`motobike_id`),
  KEY `price_id` (`price_id`),
  KEY `date_sk` (`date_sk`),
  KEY `source_id` (`source_id`),
  KEY `brand_id` (`brand_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `factprice_ibfk_1` FOREIGN KEY (`motobike_id`) REFERENCES `motobikedim` (`id`),
  CONSTRAINT `factprice_ibfk_2` FOREIGN KEY (`price_id`) REFERENCES `pricedim` (`price_id`),
  CONSTRAINT `factprice_ibfk_3` FOREIGN KEY (`date_sk`) REFERENCES `datedim` (`date_sk`),
  CONSTRAINT `factprice_ibfk_4` FOREIGN KEY (`source_id`) REFERENCES `sourcedim` (`source_id`),
  CONSTRAINT `factprice_ibfk_5` FOREIGN KEY (`brand_id`) REFERENCES `branddim` (`brand_id`),
  CONSTRAINT `factprice_ibfk_6` FOREIGN KEY (`type_id`) REFERENCES `typedim` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factprice`
--

LOCK TABLES `factprice` WRITE;
/*!40000 ALTER TABLE `factprice` DISABLE KEYS */;
INSERT INTO `factprice` VALUES (1,1,1,1,1,1,1),(2,2,2,1,2,1,2),(3,3,3,1,3,2,3),(4,4,4,1,4,2,3),(5,5,5,1,5,2,3),(6,6,6,1,6,2,3),(7,7,2,1,7,1,2),(8,8,7,1,8,1,1),(9,9,8,1,9,3,2),(10,10,9,1,10,3,3),(11,11,10,1,11,3,1),(12,12,11,1,12,3,1);
/*!40000 ALTER TABLE `factprice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `motobikedim`
--

DROP TABLE IF EXISTS `motobikedim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `motobikedim` (
  `id` int NOT NULL AUTO_INCREMENT,
  `model_name` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `name` varchar(500) DEFAULT NULL,
  `engine_capacity` varchar(255) DEFAULT NULL,
  `engine_type` varchar(255) DEFAULT NULL,
  `transmission_type` varchar(255) DEFAULT NULL,
  `features` text,
  `image_url` text,
  `status` varchar(255) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `motobikedim`
--

LOCK TABLES `motobikedim` WRITE;
/*!40000 ALTER TABLE `motobikedim` DISABLE KEYS */;
INSERT INTO `motobikedim` VALUES (1,'','N/A','DAELIM SIRIUS','','','','','https://product.hstatic.net/200000281285/product/daelim-sirius-trang_50c38e0b00da4f53a7feb57daa622b90_master.jpg','active','2024-12-05 06:03:14'),(2,'','N/A','DAELIM CANELY','','','','','https://product.hstatic.net/200000281285/product/daelim-canely-denmo_a842ea4a0a1f476f84d393289d6eb781_master.jpg','active','2024-12-05 06:03:14'),(3,'','N/A','YADEA ODORA PRO','','','','','https://product.hstatic.net/200000281285/product/11_8ff3c7977f19467c9fa7fb47e3a64daa_master.png','active','2024-12-05 06:03:14'),(4,'','N/A','YADEA XMEN NEO','','','','','https://product.hstatic.net/200000281285/product/13_49bb009058e349ddbd34cdf59c343bfa_master.png','active','2024-12-05 06:03:14'),(5,'','N/A','YADEA ORIS','','','','','https://product.hstatic.net/200000281285/product/12_b0da07eaad804df9a10c6c3fc7ca2819_master.png','active','2024-12-05 06:03:14'),(6,'','N/A','YADEA OSSY','','','','','https://product.hstatic.net/200000281285/product/12_2da2b6cfffc04f85b40ab2c77621df34_master.png','active','2024-12-05 06:03:14'),(7,'','N/A','DAELIM VENUS','','','','','https://product.hstatic.net/200000281285/product/daelim-venus-xanhanhkim_a3e11ac486d6429c9c2d4f87796d45a5_master.jpg','active','2024-12-05 06:03:14'),(8,'','N/A','DAELIM C6 PLUS','','','','','https://product.hstatic.net/200000281285/product/18_7ba20a7192ba44bab669e2c2bc27707c_master.png','active','2024-12-05 06:03:14'),(9,'','Xám Nhám','LEXI 155VVA','','','','','https://product.hstatic.net/200000281285/product/14_eafd795d76444c868a352541d4f5c4cb_master.png','active','2024-12-05 06:03:14'),(10,'','N/A','NEO\'S','','','','','https://product.hstatic.net/200000281285/product/5_314c6810f2c44bfca604237533c79a5f_master.png','active','2024-12-05 06:03:14'),(11,'','N/A','XS155R','','','','','https://product.hstatic.net/200000281285/product/2_5b60169850884198a8661b80cfb87073_master.png','active','2024-12-05 06:03:14'),(12,'','N/A','PG - 1','','','','','https://product.hstatic.net/200000281285/product/z4935871594034_a26fbe43b3e108f2aa16d365c49752c9_5dd867beb1d84ffcac90be66aa12aa71_master.jpg','active','2024-12-05 06:03:14');
/*!40000 ALTER TABLE `motobikedim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricedim`
--

DROP TABLE IF EXISTS `pricedim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricedim` (
  `price_id` int NOT NULL AUTO_INCREMENT,
  `price_range` varchar(255) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`price_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricedim`
--

LOCK TABLES `pricedim` WRITE;
/*!40000 ALTER TABLE `pricedim` DISABLE KEYS */;
INSERT INTO `pricedim` VALUES (1,'N/A',16000000),(2,'N/A',23000000),(3,'N/A',21490000),(4,'N/A',17490000),(5,'N/A',22990000),(6,'N/A',21990000),(7,'N/A',18000000),(8,'N/A',45900000),(9,'N/A',34000000),(10,'N/A',77000000),(11,'N/A',29000000);
/*!40000 ALTER TABLE `pricedim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricetrendaggregation`
--

DROP TABLE IF EXISTS `pricetrendaggregation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricetrendaggregation` (
  `date_sk` int NOT NULL,
  `average_price` float DEFAULT NULL,
  `min_price` float DEFAULT NULL,
  `max_price` float DEFAULT NULL,
  PRIMARY KEY (`date_sk`),
  CONSTRAINT `pricetrendaggregation_ibfk_1` FOREIGN KEY (`date_sk`) REFERENCES `datedim` (`date_sk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricetrendaggregation`
--

LOCK TABLES `pricetrendaggregation` WRITE;
/*!40000 ALTER TABLE `pricetrendaggregation` DISABLE KEYS */;
INSERT INTO `pricetrendaggregation` VALUES (1,29155000,16000000,77000000);
/*!40000 ALTER TABLE `pricetrendaggregation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sourcedim`
--

DROP TABLE IF EXISTS `sourcedim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sourcedim` (
  `source_id` int NOT NULL AUTO_INCREMENT,
  `source_pid` int DEFAULT NULL,
  `source_name` text,
  `source_type` text,
  `source_url` text,
  `source_SkuID` text,
  `description` text,
  PRIMARY KEY (`source_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sourcedim`
--

LOCK TABLES `sourcedim` WRITE;
/*!40000 ALTER TABLE `sourcedim` DISABLE KEYS */;
INSERT INTO `sourcedim` VALUES (1,0,'hoangcau.com','','https://hoangcau.com/products/daelim-sirius','N/A',''),(2,0,'hoangcau.com','','https://hoangcau.com/products/dealim-canely','N/A',''),(3,0,'hoangcau.com','','https://hoangcau.com/products/yadea-odora-pro','N/A',''),(4,0,'hoangcau.com','','https://hoangcau.com/products/yadea-xmen-neo','N/A',''),(5,0,'hoangcau.com','','https://hoangcau.com/products/yadea-oris','N/A',''),(6,0,'hoangcau.com','','https://hoangcau.com/products/yadea-ossy','N/A',''),(7,0,'hoangcau.com','','https://hoangcau.com/products/venus','N/A',''),(8,0,'hoangcau.com','','https://hoangcau.com/products/daelim-c6-plus','N/A',''),(9,0,'hoangcau.com','','https://hoangcau.com/products/lexi-155vva','N/A',''),(10,0,'hoangcau.com','','https://hoangcau.com/products/neo-s','N/A',''),(11,0,'hoangcau.com','','https://hoangcau.com/products/xs155r','N/A',''),(12,0,'hoangcau.com','','https://hoangcau.com/products/pg-1','N/A','');
/*!40000 ALTER TABLE `sourcedim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typedim`
--

DROP TABLE IF EXISTS `typedim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typedim` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typedim`
--

LOCK TABLES `typedim` WRITE;
/*!40000 ALTER TABLE `typedim` DISABLE KEYS */;
INSERT INTO `typedim` VALUES (1,'XE SỐ'),(2,'XE TAY GA'),(3,'XE ĐIỆN');
/*!40000 ALTER TABLE `typedim` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-10  5:22:25
