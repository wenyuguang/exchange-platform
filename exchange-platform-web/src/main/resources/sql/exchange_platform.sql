/*
Navicat MySQL Data Transfer

Source Server         : localhost@mysql
Source Server Version : 50625
Source Host           : 127.0.0.1:3306
Source Database       : exchange_platform

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2018-07-05 17:44:14
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `user_password` varchar(50) NOT NULL COMMENT '用户密码',
  `access_ip` varchar(255) NOT NULL COMMENT '可接入ip，多个用英文逗号隔开',
  `corporate_name` varchar(50) NOT NULL COMMENT '公司名称',
  `contacts` varchar(10) NOT NULL COMMENT '联系人',
  `phone` varchar(30) NOT NULL COMMENT '联系号码',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`,`service_en_name`),
  UNIQUE KEY `index_service_en_name` (`service_en_name`) USING BTREE COMMENT '接口服务中文名字段添加索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ep_service_registry
-- ----------------------------

-- ----------------------------
-- Table structure for ep_user
-- ----------------------------
DROP TABLE IF EXISTS `ep_user`;
CREATE TABLE `ep_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户唯一id',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `role` varchar(50) NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ep_user
-- ----------------------------
INSERT INTO `ep_user` VALUES ('1', 'admin', '$2a$10$QI8tSgAufwXcz98nwlhcresEk160C3QVwG2CvT49XD4vS1Wx3t.kK', 'ADMIN');
