/*
Navicat MySQL Data Transfer

Source Server         : localhost@mysql
Source Server Version : 50625
Source Host           : 127.0.0.1:3306
Source Database       : exchange_platform

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2018-07-09 17:57:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ep_permission
-- ----------------------------
DROP TABLE IF EXISTS `ep_permission`;
CREATE TABLE `ep_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限表id',
  `name` varchar(255) NOT NULL COMMENT '权限名称',
  `desc` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) NOT NULL COMMENT '权限url',
  `pid` bigint(20) DEFAULT NULL COMMENT '父节点id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ep_permission
-- ----------------------------
INSERT INTO `ep_permission` VALUES ('1', 'ROLE_USER', 'home', '/user', null);
INSERT INTO `ep_permission` VALUES ('2', 'ROLE_ADMIN', 'ABel', '/admin', null);

-- ----------------------------
-- Table structure for ep_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `ep_permission_role`;
CREATE TABLE `ep_permission_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限角色表id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ep_permission_role
-- ----------------------------
INSERT INTO `ep_permission_role` VALUES ('1', '1', '1');
INSERT INTO `ep_permission_role` VALUES ('2', '1', '2');
INSERT INTO `ep_permission_role` VALUES ('3', '2', '1');

-- ----------------------------
-- Table structure for ep_role
-- ----------------------------
DROP TABLE IF EXISTS `ep_role`;
CREATE TABLE `ep_role` (
  `id` int(11) NOT NULL COMMENT '用户id',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ep_role
-- ----------------------------
INSERT INTO `ep_role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `ep_role` VALUES ('2', 'ROLE_USER');

-- ----------------------------
-- Table structure for ep_role_user
-- ----------------------------
DROP TABLE IF EXISTS `ep_role_user`;
CREATE TABLE `ep_role_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色用户表',
  `ep_user_id` int(11) NOT NULL COMMENT '用户id',
  `ep_role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ep_role_user
-- ----------------------------
INSERT INTO `ep_role_user` VALUES ('1', '1', '1');
INSERT INTO `ep_role_user` VALUES ('2', '2', '2');

-- ----------------------------
-- Table structure for ep_service_registry
-- ----------------------------
DROP TABLE IF EXISTS `ep_service_registry`;
CREATE TABLE `ep_service_registry` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `service_cn_name` varchar(255) NOT NULL COMMENT '接口服务中文名',
  `service_en_name` varchar(255) NOT NULL COMMENT '接口服务英文名',
  `service_desc` varchar(255) DEFAULT NULL COMMENT ' 服务内容描述',
  `service_type` varchar(10) NOT NULL COMMENT '服务类型',
  `service_method` varchar(10) NOT NULL COMMENT '请求方法（get，post等）',
  `service_url` varchar(255) DEFAULT NULL COMMENT '代理接口的url',
  `enable_status` varchar(10) NOT NULL COMMENT '开启状态',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `user_password` varchar(500) NOT NULL COMMENT '用户密码',
  `access_ip` varchar(255) NOT NULL COMMENT '可接入ip，多个用英文逗号隔开',
  `corporate_name` varchar(50) NOT NULL COMMENT '公司名称',
  `contacts` varchar(10) NOT NULL COMMENT '联系人',
  `phone` varchar(30) NOT NULL COMMENT '联系号码',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`,`service_en_name`),
  UNIQUE KEY `index_service_en_name` (`service_en_name`) USING BTREE COMMENT '接口服务中文名字段添加索引'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ep_service_registry
-- ----------------------------
INSERT INTO `ep_service_registry` VALUES ('1', '测试', 'service_test', '服务测试', 'http', 'get', null, 'enable', 'admin', '$2a$10$dIY0XGDZcVDoZ0OE50Ufuucu9FD4EMjaUcypVLF6L/30oq9JZ7H62', '*', 'XXX公司', '张三', '123213213', '2018-07-09 17:48:45', null);

-- ----------------------------
-- Table structure for ep_user
-- ----------------------------
DROP TABLE IF EXISTS `ep_user`;
CREATE TABLE `ep_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户唯一id',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ep_user
-- ----------------------------
INSERT INTO `ep_user` VALUES ('1', 'admin', '$2a$10$QI8tSgAufwXcz98nwlhcresEk160C3QVwG2CvT49XD4vS1Wx3t.kK');
INSERT INTO `ep_user` VALUES ('2', 'user', '$2a$10$QI8tSgAufwXcz98nwlhcresEk160C3QVwG2CvT49XD4vS1Wx3t.kK');
