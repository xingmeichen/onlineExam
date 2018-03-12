/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : online_exam

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-03-21 15:25:15
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `teacherId` varchar(10) NOT NULL,
  `teacherPassword` varchar(30) NOT NULL,
  `teacherName` varchar(255) NOT NULL,
  `teacherSex` varchar(255) DEFAULT NULL,
  `teacherDept` varchar(255) DEFAULT NULL,
  `teacherPost` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`teacherId`),
  UNIQUE KEY `teacherId` (`teacherId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO teacher VALUES ('1', 't1', 't12345', '李', '男', '计算机学院', '教授');
INSERT INTO teacher VALUES ('2', 't2', 't12345', '勒', '女', '计算机学院', '教授');
INSERT INTO teacher VALUES ('3', 't3', 't12345', '蒙', '男', '计算机学院', '教授');
INSERT INTO teacher VALUES ('4', 't4', 't12345', '郝', '女', '计算机学院', '教授');
INSERT INTO teacher VALUES ('5', 't5', 't12345', '罗', '男', '计算机学院', '副教授');
