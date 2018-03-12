/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : online_exam

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-03-21 15:24:47
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `multiple`
-- ----------------------------
DROP TABLE IF EXISTS `multiple`;
CREATE TABLE `multiple` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `paperId` varchar(10) NOT NULL,
  `questionNumber` int(8) NOT NULL,
  `question` longtext NOT NULL,
  `A` longtext NOT NULL,
  `B` longtext NOT NULL,
  `C` longtext NOT NULL,
  `D` longtext NOT NULL,
  `answer` longtext NOT NULL,
  `mark` int(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `s_paperId` (`paperId`) USING BTREE,
  CONSTRAINT `m_paperId` FOREIGN KEY (`paperId`) REFERENCES `paper` (`paperId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of multiple
-- ----------------------------
INSERT INTO multiple VALUES ('1', 'p1', '6', '下列叙述哪些是正确的？', 'final类不可以有子类。', 'abstract类中只可以有abstract方法。', 'abstract类中可以有非abstract方法，但该方法不可以用final修饰。', '不可以同时用final和abstract修饰一个方法', 'ACD', '5');
INSERT INTO multiple VALUES ('2', 'P1', '7', '下列叙述哪些是正确的？', 'String类是final类，不可以有子类', 'String类在java.lang包中', '\"abc\"==\"abc\"的值是false', '\"abc\".equals(\"abc\")的值是true', 'ABD', '5');
INSERT INTO multiple VALUES ('3', 'p1', '8', '下列表达式中，写法正确的有？                            ', 'if(1)', 'if(\"ab\"==\"dc\")', 'switch(1)', 'switch(\"abcd\")', 'BC', '5');
INSERT INTO multiple VALUES ('4', 'p1', '9', '下列关于构造函数，描写错误的是？                            ', '构造函数的返回类型只能是void型', '构造函数是类的一种特殊函数，它的方法名必须与类名一致', '构造函数的主要功能是完成对类的对象的初始化', '一般创建对象时，系统会自动调用构造函数', 'A', '5');
INSERT INTO multiple VALUES ('5', 'p1', '10', 'Java application中的主类必须包含main方法，main方法的返回类型是什么？                            ', 'int', 'char', 'void', 'String', 'C', '5');
INSERT INTO multiple VALUES ('6', 'p2', '6', ' 下列属于数据安全性控制方法的是                           ', '用户标识和鉴定', '用户存取权限控制', '数据加密', '网络流量控制', 'ABC', '5');
INSERT INTO multiple VALUES ('7', 'p2', '7', '数据模型应满足（     ）的需求                            ', '真实模拟现实世界', '容易被人们理解', '便于在计算机上实现', '以上都是', 'D', '5');
INSERT INTO multiple VALUES ('8', 'p2', '8', '下列表述符合关系数据库基本特征的有（  ）                            ', '不同的列应有不同的数据类型', '不同的列应有不同的列名', '与行的次序无关', '与列的次序无关', 'BCD', '5');
INSERT INTO multiple VALUES ('9', 'p2', '9', '关系模型中，一个码是（）                            ', '可以由多个任意属性组成', '至多由一个属性组成', '由一个或多个属性组成，其值能够惟一标识关系中一个元组', '以上都不是', 'C', '5');
INSERT INTO multiple VALUES ('10', 'p2', '10', '下列属于需求分析阶段工作的是（）                            ', '建立E-R图', '分析用户活动', '建立数据字典', '建立数据流图', 'BCD', '5');
