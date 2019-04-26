/*
 Navicat Premium Data Transfer

 Source Server         : DataBase
 Source Server Type    : MySQL
 Source Server Version : 80014
 Source Host           : localhost:3306
 Source Schema         : hotelsystem

 Target Server Type    : MySQL
 Target Server Version : 80014
 File Encoding         : 65001

 Date: 26/04/2019 23:40:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `hotelId` int(10) NULL DEFAULT NULL,
  `customerID` int(11) NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `balance` int(11) NULL DEFAULT 0,
  `roomid` int(11) NULL DEFAULT NULL,
  `roomperiod` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (68, 9, 32, '1970-01-19', 50, 16, 1);
INSERT INTO `account` VALUES (69, 9, 32, '1970-01-19', 500, 17, 1);
INSERT INTO `account` VALUES (70, 9, 32, '1970-01-20', 500, 17, 1);
INSERT INTO `account` VALUES (71, 9, 32, '1970-01-20', 50, 16, 1);
INSERT INTO `account` VALUES (72, 10, 32, '1970-01-19', 120, 14, 1);
INSERT INTO `account` VALUES (73, 10, 32, '1970-01-19', 130, 15, 1);
INSERT INTO `account` VALUES (74, 10, 32, '1970-01-20', 120, 14, 1);
INSERT INTO `account` VALUES (75, 10, 32, '1970-01-20', 130, 15, 1);

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `id` int(11) NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES (29, 'admin', '123456', '111111111111111111', '11111111111');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `score` int(10) NULL DEFAULT NULL,
  `orderId` int(10) NULL DEFAULT NULL,
  `hotelId` int(10) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (27, 5, 85, 9, '100');
INSERT INTO `comment` VALUES (28, 2, 89, 10, '1010');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `balance` int(11) UNSIGNED NULL DEFAULT NULL,
  `isVip` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (32, 'customer', '123456', '223232222222222212', '11111111111', 8400, 0);

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `star` int(10) NULL DEFAULT NULL,
  `score` double(255, 2) NULL DEFAULT NULL,
  `numOfScore` int(10) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES (9, '一号', 5, 5.00, 1, '一豪酒店');
INSERT INTO `hotel` VALUES (10, '二号', 2, 2.00, 1, '二号酒店');

-- ----------------------------
-- Table structure for hoteladmin
-- ----------------------------
DROP TABLE IF EXISTS `hoteladmin`;
CREATE TABLE `hoteladmin`  (
  `id` int(11) NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Hotelid` int(255) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hoteladmin
-- ----------------------------
INSERT INTO `hoteladmin` VALUES (30, 'hadmin', '123456', '111111111111111111', '11111111111', 9);
INSERT INTO `hoteladmin` VALUES (31, '2hadmin', '123456', '121212111122221111', '11111111111', 10);

-- ----------------------------
-- Table structure for orderr
-- ----------------------------
DROP TABLE IF EXISTS `orderr`;
CREATE TABLE `orderr`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `roomPeriod` int(10) NULL DEFAULT NULL,
  `hotelid` int(10) NULL DEFAULT NULL,
  `roomid` int(10) NULL DEFAULT NULL,
  `balance` int(10) NULL DEFAULT NULL,
  `isComment` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderr
-- ----------------------------
INSERT INTO `orderr` VALUES (85, 'customer', '1970-01-19', 1, 9, 16, 50, 1);
INSERT INTO `orderr` VALUES (86, 'customer', '1970-01-19', 1, 9, 17, 500, 0);
INSERT INTO `orderr` VALUES (87, 'customer', '1970-01-20', 1, 9, 17, 500, 0);
INSERT INTO `orderr` VALUES (88, 'customer', '1970-01-20', 1, 9, 16, 50, 0);
INSERT INTO `orderr` VALUES (89, 'customer', '1970-01-19', 1, 10, 14, 120, 1);
INSERT INTO `orderr` VALUES (90, 'customer', '1970-01-19', 1, 10, 15, 130, 0);
INSERT INTO `orderr` VALUES (91, 'customer', '1970-01-20', 1, 10, 14, 120, 0);
INSERT INTO `orderr` VALUES (92, 'customer', '1970-01-20', 1, 10, 15, 130, 0);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(10) NULL DEFAULT NULL,
  `area` int(10) NULL DEFAULT NULL,
  `bedwidth` int(10) NULL DEFAULT NULL,
  `hotelid` int(10) NULL DEFAULT NULL,
  `price` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (14, '一号', 1, 100, 100, 10, 120);
INSERT INTO `room` VALUES (15, '二号', 2, 100, 100, 10, 130);
INSERT INTO `room` VALUES (16, '葡萄糖', 1, 10, 210, 9, 50);
INSERT INTO `room` VALUES (17, '氨基酸', 2, 120, 100, 9, 500);

-- ----------------------------
-- Table structure for system_date
-- ----------------------------
DROP TABLE IF EXISTS `system_date`;
CREATE TABLE `system_date`  (
  `nowDate` date NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_date
-- ----------------------------
INSERT INTO `system_date` VALUES ('1970-01-19');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hasLogin` int(10) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (29, 'admin', '123456', 1);
INSERT INTO `user` VALUES (30, 'hadmin', '123456', 1);
INSERT INTO `user` VALUES (31, '2hadmin', '123456', 1);
INSERT INTO `user` VALUES (32, 'customer', '123456', 1);

SET FOREIGN_KEY_CHECKS = 1;
