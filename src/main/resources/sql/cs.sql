/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : online_exam

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-03-21 15:24:08
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `cs`
-- ----------------------------
DROP TABLE IF EXISTS `cs`;
CREATE TABLE `cs` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `courseId` varchar(10) NOT NULL,
  `studentId` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cs_coursId` (`courseId`) USING BTREE,
  KEY `cs_studentId` (`studentId`) USING BTREE,
  CONSTRAINT `cs_courseId` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cs_studentId` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of cs
-- ----------------------------
INSERT INTO cs VALUES ('1', 'c1', 's1');
INSERT INTO cs VALUES ('2', 'c1', 's2');
INSERT INTO cs VALUES ('3', 'c1', 's3');
INSERT INTO cs VALUES ('4', 'c2', 's1');
INSERT INTO cs VALUES ('5', 'c2', 's2');
INSERT INTO cs VALUES ('6', 'c2', 's3');
INSERT INTO cs VALUES ('7', 'c3', 's1');
INSERT INTO cs VALUES ('8', 'c1', 's4');
INSERT INTO cs VALUES ('9', 'c9', 's1');
INSERT INTO cs VALUES ('10', 'c9', 's2');
INSERT INTO cs VALUES ('11', 'c10', 's1');
INSERT INTO cs VALUES ('12', 'c10', 's4');
