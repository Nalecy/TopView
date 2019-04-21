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

 Date: 21/04/2019 17:41:29
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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (16, 7, 25, '1970-01-07', 50, 11, 1);
INSERT INTO `account` VALUES (17, 7, 25, '1970-01-07', 100, 12, 1);
INSERT INTO `account` VALUES (18, 7, 25, '1970-01-08', 0, 11, 1);
INSERT INTO `account` VALUES (19, 7, 25, '1970-01-07', -100, 12, 1);
INSERT INTO `account` VALUES (20, 7, 25, '1970-01-07', 100, 12, 1);
INSERT INTO `account` VALUES (21, 7, 25, '1970-01-07', -100, 12, 1);
INSERT INTO `account` VALUES (22, 7, 25, '1970-01-07', 100, 12, 1);
INSERT INTO `account` VALUES (23, 7, 25, '1970-01-07', -100, 12, 1);
INSERT INTO `account` VALUES (24, 7, 25, '1970-01-07', -50, 11, 1);
INSERT INTO `account` VALUES (25, 7, 25, '1970-01-07', 100, 12, 1);
INSERT INTO `account` VALUES (26, 7, 25, '1970-01-07', 100, 12, 1);
INSERT INTO `account` VALUES (27, 7, 25, '1970-01-07', 50, 11, 1);
INSERT INTO `account` VALUES (28, 7, 25, '1970-01-09', 50, 11, 1);

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
INSERT INTO `administrator` VALUES (23, 'CCCCC', '123456', '444444444444444444', '11111111111');

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
  `balance` int(255) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (25, 'aaaaa', '123456', '444444444452525123', '11111111111', 520);
INSERT INTO `customer` VALUES (26, 'aaaaaa', '123456', '665656566656566565', '19191919919', 0);

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `star` int(10) NULL DEFAULT NULL,
  `score` double(255, 0) NULL DEFAULT NULL,
  `numOfScore` int(10) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES (7, '牛逼', 5, 0, 0, '牛逼酒店');

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
INSERT INTO `hoteladmin` VALUES (24, 'BBBBBB', '123456', '222222222222222222', '11111111111', 7);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `level` int(10) NULL DEFAULT NULL,
  `logtext` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recordtime` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderr
-- ----------------------------
INSERT INTO `orderr` VALUES (50, 'aaaaa', '1970-01-07', 1, 7, 11, 50);
INSERT INTO `orderr` VALUES (51, 'aaaaa', '1970-01-09', 1, 7, 11, 50);

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
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (11, '牛逼一号房', 1, 50, 50, 7, 50);
INSERT INTO `room` VALUES (12, '牛逼二号房', 2, 100, 100, 7, 100);

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
INSERT INTO `system_date` VALUES ('1970-01-08');

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
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (23, 'CCCCC', '123456', 1);
INSERT INTO `user` VALUES (24, 'BBBBBB', '123456', 1);
INSERT INTO `user` VALUES (25, 'aaaaa', '123456', 1);
INSERT INTO `user` VALUES (26, 'aaaaaa', '123456', 0);

SET FOREIGN_KEY_CHECKS = 1;
