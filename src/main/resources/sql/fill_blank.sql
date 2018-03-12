/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : online_exam

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-03-21 15:24:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `fill_blank`
-- ----------------------------
DROP TABLE IF EXISTS `fill_blank`;
CREATE TABLE `fill_blank` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `questionNumber` int(8) NOT NULL,
  `paperId` varchar(10) NOT NULL,
  `question` longtext NOT NULL,
  `answer` longtext,
  `mark` int(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `f_paperId` (`paperId`),
  CONSTRAINT `fill_blank_ibfk_1` FOREIGN KEY (`paperId`) REFERENCES `paper` (`paperId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of fill_blank
-- ----------------------------
INSERT INTO fill_blank VALUES ('1', '16', 'p1', '______________________方法是只有方法头，没有具体方法体和操作实现的方法。                  ', null, '5');
INSERT INTO fill_blank VALUES ('2', '17', 'p1', '开发和运行Java程序需要经过的三个主要步骤是________________。                            ', null, '5');
INSERT INTO fill_blank VALUES ('3', '18', 'p1', '在Java的基本数据类型中，char型采用Unicode编码方案，所以无论是中文字符还是英文字符，都是占用____________ 字节的内存                          ', null, '5');
INSERT INTO fill_blank VALUES ('4', '16', 'p2', '数据库中，导致数据不一致的根本原因是________________', null, '5');
INSERT INTO fill_blank VALUES ('5', '17', 'p2', '数据模型通常分为________________、_______________ 面向对象模型和对象关系模型。                            ', null, '5');
INSERT INTO fill_blank VALUES ('6', '18', 'p2', 'DBMS的主要功能有__________，数据组织存储和管理功能，____________， 数据库的事务管理和运行功能，数据库的建立和维护功能。                      ', null, '5');
INSERT INTO fill_blank VALUES ('7', '16', 'p3', '付家饿哦佛__________fjeofjeow的简欧风家饿哦基金人间佛教饿哦就饿哦就。                            ', null, '5');
INSERT INTO fill_blank VALUES ('8', '17', 'p3', '______________付家饿哦佛二级eofjeoj_____________fjeo二佛尔                           ', null, '5');
