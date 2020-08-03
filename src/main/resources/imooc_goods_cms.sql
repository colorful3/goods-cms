/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : imooc_goods_cms

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 22/07/2020 15:15:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for imooc_category
-- ----------------------------
DROP TABLE IF EXISTS `imooc_category`;
CREATE TABLE `imooc_category`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for imooc_goods
-- ----------------------------
DROP TABLE IF EXISTS `imooc_goods`;
CREATE TABLE `imooc_goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `category_id` int(0) NULL DEFAULT 0,
  `price` decimal(10, 2) NOT NULL,
  `stock` int(0) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of imooc_goods
-- ----------------------------
INSERT INTO `imooc_goods` VALUES (1, '测试商品1', '', 0, 12.30, 3, '2020-07-20 16:53:19', '2020-07-20 16:53:19', NULL);
INSERT INTO `imooc_goods` VALUES (2, '测试商品2', '', 0, 33.20, 10, '2020-07-20 17:17:53', '2020-07-20 17:17:53', NULL);
INSERT INTO `imooc_goods` VALUES (3, '测试商品3', '', 0, 20.00, 50, '2020-07-20 17:18:09', '2020-07-20 17:18:09', NULL);

-- ----------------------------
-- Table structure for imooc_user
-- ----------------------------
DROP TABLE IF EXISTS `imooc_user`;
CREATE TABLE `imooc_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of imooc_user
-- ----------------------------
INSERT INTO `imooc_user` VALUES (1, '小慕', 'admin', '123456', '2020-07-20 16:53:19', '2020-07-20 16:53:19', NULL);

SET FOREIGN_KEY_CHECKS = 1;
