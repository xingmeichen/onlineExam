/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : online_exam

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-03-21 15:24:02
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `courseId` varchar(10) NOT NULL,
  `courseName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`courseId`),
  UNIQUE KEY `c_courseId` (`courseId`),
  KEY `c_courseName` (`courseName`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO course VALUES ('1', 'c1', 'Java');
INSERT INTO course VALUES ('5', 'c5', '操作系统原理');
INSERT INTO course VALUES ('4', 'c4', '高级语言程序设计');
INSERT INTO course VALUES ('8', 'c8', '计算机网络');
INSERT INTO course VALUES ('3', 'c3', '计算机组成原理');
INSERT INTO course VALUES ('10', 'c9', '离散数学');
INSERT INTO course VALUES ('11', 'c10', '面向对象程序设计');
INSERT INTO course VALUES ('7', 'c7', '数据结构');
INSERT INTO course VALUES ('2', 'c2', '数据库原理');
INSERT INTO course VALUES ('6', 'c6', '算法设计与分析');
