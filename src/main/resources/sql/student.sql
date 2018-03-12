/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : online_exam

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-03-21 15:25:11
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `studentId` varchar(10) NOT NULL,
  `studentPassword` varchar(30) NOT NULL,
  `studentName` varchar(255) NOT NULL,
  `studentSex` varchar(5) DEFAULT NULL,
  `studentDept` varchar(255) DEFAULT NULL,
  `studentMajor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`studentId`),
  UNIQUE KEY `studentId` (`studentId`),
  UNIQUE KEY `stu_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO student VALUES ('1', 's1', 's12345', '赵', '女', '计算机学院', '计算机科学与技术');
INSERT INTO student VALUES ('2', 's2', 's12345', '刘', '女', '计算机学院', '计算机科学与技术');
INSERT INTO student VALUES ('3', 's3', 's12345', '吴', '男', '计算机学院', '计算机科学与技术');
INSERT INTO student VALUES ('4', 's4', 's12345', '王', '女', '计算机学院', '软件工程');
INSERT INTO student VALUES ('5', 's5', 's12345', '马', '男', '计算机学院', '');
INSERT INTO student VALUES ('6', 's6', 's12345', '路', '男', '', '计算机科学与技术');
INSERT INTO student VALUES ('7', 's7', 's12345', '李', '女', '计算机学院', '');
INSERT INTO student VALUES ('8', 's8', 's12345', '张', '女', '计算机学院', '');
INSERT INTO student VALUES ('9', 's9', 's12345', '罗', null, null, null);
INSERT INTO student VALUES ('10', 's10', 's12345', '房', null, null, null);
