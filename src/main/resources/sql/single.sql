/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : online_exam

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-03-21 15:25:04
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `single`
-- ----------------------------
DROP TABLE IF EXISTS `single`;
CREATE TABLE `single` (
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
  KEY `s_paperId` (`paperId`),
  CONSTRAINT `s_paperId` FOREIGN KEY (`paperId`) REFERENCES `paper` (`paperId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of single
-- ----------------------------
INSERT INTO single VALUES ('1', 'p1', '1', '下列哪些是java中正确的标识符', 'moon-sun', 'int_long', 'public', '25', 'B', '5');
INSERT INTO single VALUES ('2', 'p1', '2', '下列四个访问限制修饰符中，哪个的访问权限最大', 'public', 'private', 'default', 'protected', 'A', '5');
INSERT INTO single VALUES ('3', 'p1', '3', 'Java类的源文件的扩展名是什么？', '.js', '.java', '.class', '.html', 'B', '5');
INSERT INTO single VALUES ('4', 'p1', '4', 'java类经过编译成功后生成的字节码文件的文件扩展名是什么？                            ', '.java', '.c', '.js', '.class', 'D', '5');
INSERT INTO single VALUES ('5', 'p1', '5', ' Java类可以用一下哪些修饰符修饰                           ', '只能用public', '只能用private', '只能用protected', 'public或者省略', 'D', '5');
INSERT INTO single VALUES ('6', 'p2', '1', '使用二维表格结构表达数据和数据间联系的数据模型是                            ', '层次模型', '网状模型', '关系模型', '实体-联系模型', 'C', '5');
INSERT INTO single VALUES ('7', 'p2', '2', '在数据库中存储的是                            ', '数据', '数据及数据之间的联系', '数据模型', '信息', 'B', '5');
INSERT INTO single VALUES ('8', 'p2', '3', '划分层次型、网状型和关系型数据库的原则是                            ', '记录的长度', '文件的大小', '联系的复杂程度', '数据及联系的表示方式', 'D', '5');
INSERT INTO single VALUES ('9', 'p2', '4', '数据库三级模式体系结构的划分，主要有利于保持数据库的                            ', '数据安全性', '数据独立性', '结构规范化', '操作可行性', 'B', '5');
INSERT INTO single VALUES ('10', 'p2', '5', '数据库系统中用于定义和描述数据库逻辑结构的语言是                            ', 'SQL', 'DDL', 'DCL', 'DML', 'A', '5');
INSERT INTO single VALUES ('11', 'p5', '1', '下列选项中，不属于因特网基本功能的是（）.                            ', '电子邮件', '文件传输', '远程登录', '实时监测控制', 'D', '5');
INSERT INTO single VALUES ('12', 'p4', '1', '下列不属于C语言的基本数据类型的是（）                          ', 'int', 'char', 'short', 'boolean', 'D', '5');
