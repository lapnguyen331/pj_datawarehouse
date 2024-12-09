/*
 Navicat Premium Dump SQL

 Source Server         : thao
 Source Server Type    : MySQL
 Source Server Version : 100432 (10.4.32-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : dbmart

 Target Server Type    : MySQL
 Target Server Version : 100432 (10.4.32-MariaDB)
 File Encoding         : 65001

 Date: 09/12/2024 05:18:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for branddim
-- ----------------------------
DROP TABLE IF EXISTS `branddim`;
CREATE TABLE `branddim`  (
  `brand_id` int NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_at` timestamp(6) NULL DEFAULT current_timestamp(6),
  PRIMARY KEY (`brand_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for datedim
-- ----------------------------
DROP TABLE IF EXISTS `datedim`;
CREATE TABLE `datedim`  (
  `date_sk` int NOT NULL,
  `full_date` date NOT NULL COMMENT '2022-05-08 yyyy-mm-dd',
  `day_since_2010` int NOT NULL COMMENT '128',
  `month_since_2010` int NOT NULL COMMENT '5',
  `day_of_week` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'thứ trong tuaanf ( sunday,monday,..)',
  `calendar_month` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'tên tháng ( may,august,...) - may',
  `calendar_year` int NOT NULL COMMENT 'tên năm ( 2022,2023,..) - 2022',
  `day_of_month` int NOT NULL COMMENT 'ngay trong tháng đó - 8',
  `day_of_year` int NOT NULL COMMENT 'ngaỳ thứ bao nhiêu trong năm đó - 128',
  `week_of_year_sunday` int NOT NULL COMMENT 'so tuần trong năm (tính tư chủ nhật) - 19 ',
  `week_sunday_start` date NOT NULL COMMENT 'ngày bắt đầu tuần của ngày đó ( tính tư chủ nhật)',
  `week_of_year_monday` int NOT NULL COMMENT 'so tuần trong năm (tính từ thứ 2)',
  `week_monday_start` date NOT NULL COMMENT ' ngày bắt đầu tuần của ngày đó ( tính từ thứ 2)',
  `holiday` int NOT NULL COMMENT ' có phải ngày lễ không (0: không, 1: có) varchar-int',
  `day_type` int NOT NULL COMMENT ' ngày trong tuần hay cuối cuần ( 1_weekday ,0_weekend)',
  `create_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`date_sk`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for motobikedim
-- ----------------------------
DROP TABLE IF EXISTS `motobikedim`;
CREATE TABLE `motobikedim`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `engine_capacity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `image_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for priceaggre
-- ----------------------------
DROP TABLE IF EXISTS `priceaggre`;
CREATE TABLE `priceaggre`  (
  `date_sk` int NOT NULL,
  `average_price` float NULL DEFAULT NULL,
  `min_price` float NULL DEFAULT NULL,
  `max_price` float NULL DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`date_sk`) USING BTREE,
  CONSTRAINT `priceaggre_ibfk_1` FOREIGN KEY (`date_sk`) REFERENCES `datedim` (`date_sk`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pricedim
-- ----------------------------
DROP TABLE IF EXISTS `pricedim`;
CREATE TABLE `pricedim`  (
  `price_id` int NOT NULL AUTO_INCREMENT,
  `zone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'price_range - pricedim(wh)',
  `price` double NOT NULL,
  `create_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`price_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 673 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pricefact
-- ----------------------------
DROP TABLE IF EXISTS `pricefact`;
CREATE TABLE `pricefact`  (
  `fact_price_id` int NOT NULL AUTO_INCREMENT,
  `motobike_id` int NULL DEFAULT NULL,
  `price_id` int NULL DEFAULT NULL,
  `source_id` int NULL DEFAULT NULL,
  `brand_id` int NULL DEFAULT NULL,
  `type_id` int NULL DEFAULT NULL,
  `price_value` double NULL DEFAULT NULL,
  `date_sk` int NULL DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`fact_price_id`) USING BTREE,
  INDEX `motobike_id`(`motobike_id` ASC) USING BTREE,
  INDEX `price_id`(`price_id` ASC) USING BTREE,
  INDEX `date_sk`(`date_sk` ASC) USING BTREE,
  INDEX `source_id`(`source_id` ASC) USING BTREE,
  INDEX `brand_id`(`brand_id` ASC) USING BTREE,
  INDEX `type_id`(`type_id` ASC) USING BTREE,
  CONSTRAINT `pricefact_ibfk_1` FOREIGN KEY (`motobike_id`) REFERENCES `motobikedim` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pricefact_ibfk_2` FOREIGN KEY (`price_id`) REFERENCES `pricedim` (`price_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pricefact_ibfk_3` FOREIGN KEY (`date_sk`) REFERENCES `datedim` (`date_sk`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pricefact_ibfk_4` FOREIGN KEY (`source_id`) REFERENCES `sourcedim` (`source_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pricefact_ibfk_5` FOREIGN KEY (`brand_id`) REFERENCES `branddim` (`brand_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pricefact_ibfk_6` FOREIGN KEY (`type_id`) REFERENCES `typedim` (`type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sourcedim
-- ----------------------------
DROP TABLE IF EXISTS `sourcedim`;
CREATE TABLE `sourcedim`  (
  `source_id` int NOT NULL AUTO_INCREMENT,
  `source_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `source_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`source_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for typedim
-- ----------------------------
DROP TABLE IF EXISTS `typedim`;
CREATE TABLE `typedim`  (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
