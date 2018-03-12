/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : online_exam

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-03-21 15:24:52
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `paper`
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `paperId` varchar(10) NOT NULL,
  `paperName` varchar(100) NOT NULL,
  `courseId` varchar(10) NOT NULL,
  `paperSubject` varchar(100) NOT NULL,
  `paperMaker` varchar(10) DEFAULT NULL,
  `paperStartTime` datetime NOT NULL,
  `paperEndTime` datetime NOT NULL,
  `paperDuration` varchar(100) NOT NULL,
  `mark` int(3) NOT NULL,
  PRIMARY KEY (`id`,`paperId`),
  UNIQUE KEY `paperId` (`paperId`),
  KEY `p_paperMaker` (`paperMaker`),
  KEY `p_courseID` (`courseId`),
  KEY `p_courseName` (`paperSubject`),
  CONSTRAINT `p_courseID` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `p_paperMaker` FOREIGN KEY (`paperMaker`) REFERENCES `teacher` (`teacherId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO paper VALUES ('1', 'p1', 'java期末考试', 'c1', 'Java', 't1', '2016-03-18 08:00:35', '2016-03-20 09:00:00', '120分钟', '100');
INSERT INTO paper VALUES ('2', 'p2', '2015学年秋季学期数据库原理期末考试', 'c2', '数据库原理', 't1', '2016-03-19 08:00:00', '2016-03-21 08:00:00', '120分钟', '100');
INSERT INTO paper VALUES ('3', 'p3', '2015学年秋季学期计算机组成原理期末考试', 'c3', '计算机组成原理', 't1', '2015-12-24 08:00:00', '2015-12-24 10:00:00', '120分钟', '100');
INSERT INTO paper VALUES ('4', 'p4', 'C语言期末考试', 'c1', '高级语言程序设计', 't1', '2016-03-21 08:00:00', '2016-03-21 10:00:00', '120分钟', '100');
INSERT INTO paper VALUES ('5', 'p5', '2015学年秋季学期计算机网络期末考试', 'c8', '计算机网络', 't1', '2016-01-15 08:00:00', '2016-03-21 10:00:00', '120分钟', '100');
INSERT INTO paper VALUES ('6', 'p90', 'dfjo', 'c7', '数据结构', 't1', '2016-03-23 11:22:41', '2016-04-06 11:22:48', '120fenzhong ', '100');
