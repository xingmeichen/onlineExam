/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : online_exam

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-03-21 15:24:14
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `essay`
-- ----------------------------
DROP TABLE IF EXISTS `essay`;
CREATE TABLE `essay` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `paperId` varchar(10) NOT NULL,
  `questionNumber` int(8) NOT NULL,
  `question` longtext NOT NULL,
  `answser` longtext,
  `mark` int(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `e_paperId` (`paperId`) USING BTREE,
  CONSTRAINT `e_paperId` FOREIGN KEY (`paperId`) REFERENCES `paper` (`paperId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of essay
-- ----------------------------
INSERT INTO essay VALUES ('1', 'p1', '19', '什么是socket?如何建立socket连接？', null, '10');
INSERT INTO essay VALUES ('2', 'p1', '20', '编写一个程序，要求：使用Java的输入、输出流将一个文本文件的内容按行读出，每读出一行就顺序添加行号，并写入到另一个文件中', null, '10');
INSERT INTO essay VALUES ('3', 'p2', '19', '简要描述关系数据模型的三要素的内容以及关系模型的优缺点。                            ', null, '10');
INSERT INTO essay VALUES ('4', 'p2', '20', '文件系统和数据库系统的区别和联系是什么？数据库系统的优缺点又是什么？                            ', null, '10');
