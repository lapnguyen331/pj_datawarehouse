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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-10  5:28:32
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-10  5:28:32
