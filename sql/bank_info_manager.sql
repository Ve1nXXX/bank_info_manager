/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : bank_info_manager

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 27/04/2023 17:28:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `money` decimal(10, 2) NULL DEFAULT NULL COMMENT '当前余额',
  `addtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  `login_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户类型（0工作人员 1客户）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES (2, '李嘉诚', '11111111111', '000000', 150.00, '2023-04-17 17:38:15', '0');
INSERT INTO `sys_admin` VALUES (3, '111', '12222222222', '111111', 0.00, '2023-04-27 17:01:38', '1');

-- ----------------------------
-- Table structure for sys_cunqu_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_cunqu_record`;
CREATE TABLE `sys_cunqu_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型',
  `money` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户id',
  `addtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '存取款记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_cunqu_record
-- ----------------------------
INSERT INTO `sys_cunqu_record` VALUES (2, '李嘉诚', '存款', 200.00, '2', '2023-04-17 17:42:12');
INSERT INTO `sys_cunqu_record` VALUES (3, '李嘉诚', '取款', 150.00, '2', '2023-04-17 17:42:21');
INSERT INTO `sys_cunqu_record` VALUES (4, '111', '存款', 1.00, '3', '2023-04-27 17:12:01');
INSERT INTO `sys_cunqu_record` VALUES (6, '111', '存款', 1.00, '3', '2023-04-27 17:15:22');
INSERT INTO `sys_cunqu_record` VALUES (7, '111', '取款', 1.00, '3', '2023-04-27 17:15:28');

SET FOREIGN_KEY_CHECKS = 1;
