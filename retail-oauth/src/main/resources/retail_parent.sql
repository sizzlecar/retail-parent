/*
 Navicat MySQL Data Transfer

 Source Server         : 132
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 10.0.0.132:3306
 Source Schema         : retail_parent

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 07/01/2021 19:44:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for retail_auth
-- ----------------------------
DROP TABLE IF EXISTS `retail_auth`;
CREATE TABLE `retail_auth`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `type` tinyint(4) NOT NULL COMMENT '登陆方式',
  `identifier` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登陆方式唯一标识',
  `token` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登陆方式登陆令牌',
  `creator_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人id',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `updater_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人id',
  `updated_time` datetime(0) NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ra_unique_index`(`user_id`, `type`, `identifier`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for retail_group
-- ----------------------------
DROP TABLE IF EXISTS `retail_group`;
CREATE TABLE `retail_group`  (
  `id` int(11) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '类型',
  `parent_id` int(11) NOT NULL COMMENT '父id，0代表根节点',
  `creator_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `updater_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人',
  `updated_time` datetime(0) NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `rg_index`(`parent_id`, `type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for retail_menu
-- ----------------------------
DROP TABLE IF EXISTS `retail_menu`;
CREATE TABLE `retail_menu`  (
  `id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL COMMENT '模块id',
  `parent_id` int(11) NOT NULL COMMENT '父id，为0代表根节点',
  `type` tinyint(4) NOT NULL COMMENT '类型',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'url',
  `creator_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人id',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `updater_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人id',
  `updated_time` datetime(0) NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `rm_fk`(`module_id`) USING BTREE,
  INDEX `rm_parent_type_index`(`parent_id`, `type`) USING BTREE,
  CONSTRAINT `rm_fk` FOREIGN KEY (`module_id`) REFERENCES `retail_module` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for retail_module
-- ----------------------------
DROP TABLE IF EXISTS `retail_module`;
CREATE TABLE `retail_module`  (
  `id` int(11) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `icon_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'icon url',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '类型',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parent_id` int(11) NOT NULL COMMENT '父id，为0时为顶层module',
  `creator_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人id',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `updater_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人id',
  `updated_time` datetime(0) NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `rmo_type_index`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for retail_role
-- ----------------------------
DROP TABLE IF EXISTS `retail_role`;
CREATE TABLE `retail_role`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `creator_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人id',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `updater_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人',
  `updated_time` datetime(0) NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for retail_role_group_mapping
-- ----------------------------
DROP TABLE IF EXISTS `retail_role_group_mapping`;
CREATE TABLE `retail_role_group_mapping`  (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '状态,0 正常',
  `creator_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_time` datetime(0) NOT NULL,
  `updater_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `updated_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `rrgm_group_id_fk`(`group_id`) USING BTREE,
  INDEX `rrgm_role_id_fk`(`role_id`) USING BTREE,
  INDEX `rrgm_status_index`(`status`) USING BTREE,
  CONSTRAINT `rrgm_group_id_fk` FOREIGN KEY (`group_id`) REFERENCES `retail_group` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rrgm_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `retail_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for retail_role_module_menu_mapping
-- ----------------------------
DROP TABLE IF EXISTS `retail_role_module_menu_mapping`;
CREATE TABLE `retail_role_module_menu_mapping`  (
  `id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL COMMENT 'group表id',
  `role_id` int(11) NOT NULL COMMENT 'role表id',
  `module_id` int(11) NOT NULL COMMENT 'module表id',
  `menu_id` int(11) NOT NULL COMMENT 'menu表id',
  `status` tinyint(4) NOT NULL COMMENT '状态,0 正常',
  `creator_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人id',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `updater_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人id',
  `updated_time` datetime(0) NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `rrmmm_menu_id_fk`(`menu_id`) USING BTREE,
  INDEX `rrmmm_module_id_fk`(`module_id`) USING BTREE,
  INDEX `rrmmm_role_id_fk`(`role_id`) USING BTREE,
  INDEX `rrmmm_group_id_fk`(`group_id`) USING BTREE,
  INDEX `rrmmm_status_index`(`status`) USING BTREE,
  CONSTRAINT `rrmmm_group_id_fk` FOREIGN KEY (`group_id`) REFERENCES `retail_group` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rrmmm_menu_id_fk` FOREIGN KEY (`menu_id`) REFERENCES `retail_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rrmmm_module_id_fk` FOREIGN KEY (`module_id`) REFERENCES `retail_module` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rrmmm_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `retail_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for retail_user
-- ----------------------------
DROP TABLE IF EXISTS `retail_user`;
CREATE TABLE `retail_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `phone_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `age` tinyint(4) NULL DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `creator_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `updater_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人',
  `updated_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `rs_phone_unique_index`(`phone_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for retail_user_role_mapping
-- ----------------------------
DROP TABLE IF EXISTS `retail_user_role_mapping`;
CREATE TABLE `retail_user_role_mapping`  (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '状态,0 正常',
  `creator_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人id',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `updater_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人',
  `updated_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `rurm_role_id_fk`(`role_id`) USING BTREE,
  INDEX `rurm_user_id_fk`(`user_id`) USING BTREE,
  INDEX `rurm_status_index`(`status`) USING BTREE,
  CONSTRAINT `rurm_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `retail_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rurm_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `retail_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
