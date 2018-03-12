/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : online_exam

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-03-21 15:24:36
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `grade`
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `studentId` varchar(10) NOT NULL,
  `grade` int(4) NOT NULL DEFAULT '0',
  `paperId` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `g_studentId` (`studentId`),
  KEY `g_paperId` (`paperId`),
  CONSTRAINT `g_paperId` FOREIGN KEY (`paperId`) REFERENCES `paper` (`paperId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `g_studentId` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of grade
-- ----------------------------
