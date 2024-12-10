/*
 Navicat Premium Dump SQL

 Source Server         : thao
 Source Server Type    : MySQL
 Source Server Version : 100432 (10.4.32-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : dbmart2

 Target Server Type    : MySQL
 Target Server Version : 100432 (10.4.32-MariaDB)
 File Encoding         : 65001

 Date: 10/12/2024 05:49:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fact
-- ----------------------------
DROP TABLE IF EXISTS `fact`;
CREATE TABLE `fact`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_vehicle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price_current` double NULL DEFAULT NULL,
  `price_min` double NULL DEFAULT NULL,
  `price_max` double NULL DEFAULT NULL,
  `price_avg` double NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fact
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
