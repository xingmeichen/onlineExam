/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : online_exam

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-03-21 15:24:41
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `judge`
-- ----------------------------
DROP TABLE IF EXISTS `judge`;
CREATE TABLE `judge` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `paperId` varchar(10) NOT NULL,
  `questionNumber` int(11) NOT NULL,
  `question` longtext NOT NULL,
  `answer` longtext NOT NULL,
  `mark` int(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `j_paperId` (`paperId`) USING BTREE,
  CONSTRAINT `judge_ibfk_1` FOREIGN KEY (`paperId`) REFERENCES `paper` (`paperId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of judge
-- ----------------------------
INSERT INTO judge VALUES ('1', 'p1', '11', '父类的final方法可以被子类重写', 'N', '3');
INSERT INTO judge VALUES ('2', 'p1', '12', 'abstract类只可以用abstract方法', 'N', '3');
INSERT INTO judge VALUES ('3', 'p1', '13', 'final修饰的成员变量定义时必须初始化', 'Y', '3');
INSERT INTO judge VALUES ('4', 'p1', '14', 'String类是final类', 'Y', '3');
INSERT INTO judge VALUES ('5', 'p1', '15', '内部类的类体中不可以声明类变量和类方法', 'Y', '3');
INSERT INTO judge VALUES ('6', 'p2', '11', '数据库设计阶段分为：物理设计阶段、逻辑设计阶段、编程和调试', 'N', '5');
INSERT INTO judge VALUES ('7', 'p2', '12', '设属性A是关系R的主属性，则属性A不能是空值，这是实体完整性规则                            ', 'Y', '3');
INSERT INTO judge VALUES ('8', 'p2', '13', '用树型结构表示实体类型以及实体间联系的数据模型称为层次模型                           ', 'Y', '3');
INSERT INTO judge VALUES ('9', 'p2', '14', '在SQL语言的select语句中，实现选中运算的字句是select                            ', 'Y', '3');
INSERT INTO judge VALUES ('10', 'p2', '15', '如果事务T已对数据D加S锁，则其他事务对数据D不能加S锁，可以加X锁                            ', 'N', '3');
