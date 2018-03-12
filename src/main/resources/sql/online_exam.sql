/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : online_exam

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-03-21 15:26:49
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `adminId` varchar(10) NOT NULL,
  `adminPassword` varchar(30) NOT NULL,
  `adminName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO admin VALUES ('1', 'a1', 'a12345', '李');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `courseId` varchar(10) NOT NULL,
  `courseName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`courseId`),
  UNIQUE KEY `c_courseId` (`courseId`),
  KEY `c_courseName` (`courseName`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO course VALUES ('1', 'c1', 'Java');
INSERT INTO course VALUES ('5', 'c5', '操作系统原理');
INSERT INTO course VALUES ('4', 'c4', '高级语言程序设计');
INSERT INTO course VALUES ('8', 'c8', '计算机网络');
INSERT INTO course VALUES ('3', 'c3', '计算机组成原理');
INSERT INTO course VALUES ('10', 'c9', '离散数学');
INSERT INTO course VALUES ('11', 'c10', '面向对象程序设计');
INSERT INTO course VALUES ('7', 'c7', '数据结构');
INSERT INTO course VALUES ('2', 'c2', '数据库原理');
INSERT INTO course VALUES ('6', 'c6', '算法设计与分析');

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

-- ----------------------------
-- Table structure for `result`
-- ----------------------------
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `studentId` varchar(10) NOT NULL,
  `paperId` varchar(10) NOT NULL,
  `questionNumber` int(8) NOT NULL,
  `answer` longtext NOT NULL,
  `mark` int(3) NOT NULL,
  `questionType` varchar(30) NOT NULL,
  `signStatus` varchar(5) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `r_paperId` (`paperId`),
  KEY `r_studentId` (`studentId`),
  CONSTRAINT `r_paperId` FOREIGN KEY (`paperId`) REFERENCES `paper` (`paperId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `r_studentId` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of result
-- ----------------------------
INSERT INTO result VALUES ('1', 's1', 'p1', '1', 'B', '5', 'single', 'Y');
INSERT INTO result VALUES ('2', 's1', 'p1', '2', 'A', '5', 'single', 'Y');
INSERT INTO result VALUES ('3', 's3', 'p1', '1', 'B', '5', 'single', 'Y');
INSERT INTO result VALUES ('4', 's3', 'p1', '2', 'A', '5', 'single', 'Y');
INSERT INTO result VALUES ('5', 's2', 'p1', '1', 'B', '5', 'single', 'Y');
INSERT INTO result VALUES ('6', 's2', 'p1', '2', 'A', '5', 'single', 'Y');
INSERT INTO result VALUES ('7', 's4', 'p1', '1', 'B', '5', 'single', 'Y');
INSERT INTO result VALUES ('8', 's4', 'p1', '2', 'A', '5', 'single', 'Y');
INSERT INTO result VALUES ('9', 's4', 'p1', '6', 'AB', '0', 'multiple', 'N');
INSERT INTO result VALUES ('10', 's4', 'P1', '7', 'BC', '0', 'multiple', 'N');
INSERT INTO result VALUES ('11', 's4', 'p1', '11', 'Y', '0', 'judge', 'N');
INSERT INTO result VALUES ('12', 's4', 'p1', '12', 'Y', '0', 'judge', 'N');
INSERT INTO result VALUES ('13', 's4', 'p1', '13', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('14', 's4', 'p1', '14', 'N', '0', 'judge', 'N');
INSERT INTO result VALUES ('15', 's4', 'p1', '15', 'N', '0', 'judge', 'N');
INSERT INTO result VALUES ('16', 's1', 'p1', '3', 'B', '5', 'single', 'Y');
INSERT INTO result VALUES ('17', 's1', 'p1', '6', 'ACD', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('18', 's1', 'P1', '7', 'ABCD', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('19', 's1', 'p1', '11', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('20', 's1', 'p1', '12', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('21', 's1', 'p1', '13', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('22', 's1', 'p1', '14', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('23', 's1', 'p1', '15', 'N', '3', 'judge', 'Y');
INSERT INTO result VALUES ('24', 's1', 'p1', '19', 'socket即套接字。               ', '3', 'essay', 'Y');
INSERT INTO result VALUES ('25', 's1', 'p1', '20', 'public class Memo {\r\n	\r\n	/**\r\n	 * 正确的电话号码\r\n	 * */\r\n	public void ifPhone(){\r\n		String phone = \"1989889\";\r\n		boolean result = phone.matches(\"[0-9]{11}\");\r\n		if(result)\r\n			System.out.println(\"正确的电话号码\");\r\n		else\r\n			System.out.println(\"错误的电话号码\");\r\n	\r\n	}\r\n	\r\n	public static void main(String[] args) {\r\n		\r\n		Memo memo = new Memo();\r\n		memo.ifPhone();\r\n		String m = \"\";\r\n		m = \"hello \";\r\n		m = m+\"\\n\";\r\n		m = m+\"world\";\r\n		System.out.println(m);\r\n	}\r\n\r\n}\r\n                ', '8', 'essay', 'Y');
INSERT INTO result VALUES ('26', 's2', 'p1', '6', 'ACD', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('27', 's2', 'P1', '7', 'ABCD', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('28', 's2', 'p1', '19', 'socket即套接字                ', '0', 'essay', 'N');
INSERT INTO result VALUES ('29', 's2', 'p1', '20', 'public class Memo {\r\n	\r\n	/**\r\n	 * 正确的电话号码\r\n	 * */\r\n	public void ifPhone(){\r\n		String phone = \"1989889\";\r\n		boolean result = phone.matches(\"[0-9]{11}\");\r\n		if(result)\r\n			System.out.println(\"正确的电话号码\");\r\n		else\r\n			System.out.println(\"错误的电话号码\");\r\n	\r\n	}\r\n	\r\n	public static void main(String[] args) {\r\n		\r\n		Memo memo = new Memo();\r\n		memo.ifPhone();\r\n		String m = \"\";\r\n		m = \"hello \";\r\n		m = m+\"\\n\";\r\n		m = m+\"world\";\r\n		System.out.println(m);\r\n	}\r\n\r\n}               ', '0', 'essay', 'N');
INSERT INTO result VALUES ('30', 's1', 'p1', '8', 'BC', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('31', 's1', 'p1', '9', 'A', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('32', 's1', 'p1', '10', 'C', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('33', 's1', 'p1', '16', '抽象方法               ', '5', 'fill', 'Y');
INSERT INTO result VALUES ('34', 's1', 'p1', '17', '编辑、编译、运行              ', '5', 'fill', 'Y');
INSERT INTO result VALUES ('35', 's1', 'p1', '18', '2               ', '5', 'fill', 'Y');
INSERT INTO result VALUES ('36', 's1', 'p2', '6', '', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('37', 's1', 'p2', '7', '', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('38', 's1', 'p2', '8', '', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('39', 's1', 'p2', '9', '', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('40', 's1', 'p2', '10', '', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('41', 's1', 'p2', '16', '                ', '0', 'fill', 'N');
INSERT INTO result VALUES ('42', 's1', 'p2', '17', '                ', '0', 'fill', 'N');
INSERT INTO result VALUES ('43', 's1', 'p2', '18', '                ', '0', 'fill', 'N');
INSERT INTO result VALUES ('44', 's1', 'p1', '4', 'D', '5', 'single', 'Y');
INSERT INTO result VALUES ('45', 's1', 'p1', '5', 'D', '5', 'single', 'Y');
INSERT INTO result VALUES ('46', 's1', 'p3', '16', '                ', '0', 'fill', 'N');
INSERT INTO result VALUES ('47', 's1', 'p3', '17', '                ', '0', 'fill', 'N');
INSERT INTO result VALUES ('48', 's1', 'p2', '19', '                ', '0', 'essay', 'N');
INSERT INTO result VALUES ('49', 's1', 'p2', '20', '                ', '0', 'essay', 'N');
INSERT INTO result VALUES ('50', 's3', 'p2', '1', 'C', '5', 'single', 'Y');
INSERT INTO result VALUES ('51', 's3', 'p2', '2', 'B', '5', 'single', 'Y');
INSERT INTO result VALUES ('52', 's3', 'p2', '3', 'D', '5', 'single', 'Y');
INSERT INTO result VALUES ('53', 's3', 'p2', '4', 'B', '5', 'single', 'Y');
INSERT INTO result VALUES ('54', 's3', 'p2', '5', 'A', '5', 'single', 'Y');
INSERT INTO result VALUES ('55', 's3', 'p2', '6', 'B', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('56', 's3', 'p2', '7', 'B', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('57', 's3', 'p2', '8', 'AB', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('58', 's3', 'p2', '9', 'AC', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('59', 's3', 'p2', '10', 'AC', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('60', 's3', 'p2', '11', 'N', '5', 'judge', 'Y');
INSERT INTO result VALUES ('61', 's3', 'p2', '12', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('62', 's3', 'p2', '13', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('63', 's3', 'p2', '14', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('64', 's3', 'p2', '15', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('65', 's3', 'p2', '16', '数据冗余                ', '0', 'fill', 'N');
INSERT INTO result VALUES ('66', 's3', 'p2', '17', '层次模型、网络模型                ', '0', 'fill', 'N');
INSERT INTO result VALUES ('67', 's3', 'p2', '18', '数据定义、数据操纵               ', '0', 'fill', 'N');
INSERT INTO result VALUES ('68', 's3', 'p2', '19', ' 关系即数据库采用的结构。              ', '0', 'essay', 'N');
INSERT INTO result VALUES ('69', 's3', 'p2', '20', ' 文件系统将文件长期保持在外存上。              ', '0', 'essay', 'N');
INSERT INTO result VALUES ('70', 's3', 'p1', '3', 'B', '5', 'single', 'Y');
INSERT INTO result VALUES ('71', 's3', 'p1', '4', 'D', '5', 'single', 'Y');
INSERT INTO result VALUES ('72', 's3', 'p1', '5', 'D', '5', 'single', 'Y');
INSERT INTO result VALUES ('73', 's3', 'p1', '6', 'ACD', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('74', 's3', 'P1', '7', 'ABD', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('75', 's3', 'p1', '8', 'BC', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('76', 's3', 'p1', '9', 'A', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('77', 's3', 'p1', '10', 'C', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('78', 's3', 'p1', '11', 'N', '3', 'judge', 'Y');
INSERT INTO result VALUES ('79', 's3', 'p1', '12', 'N', '3', 'judge', 'Y');
INSERT INTO result VALUES ('80', 's3', 'p1', '13', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('81', 's3', 'p1', '14', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('82', 's3', 'p1', '15', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('83', 's3', 'p1', '16', '抽象方法        ', '0', 'fill', 'N');
INSERT INTO result VALUES ('84', 's3', 'p1', '17', '编辑、编译、运行               ', '0', 'fill', 'N');
INSERT INTO result VALUES ('85', 's3', 'p1', '18', '2            ', '0', 'fill', 'N');
INSERT INTO result VALUES ('86', 's3', 'p1', '19', 'socket是套接字。               ', '0', 'essay', 'N');
INSERT INTO result VALUES ('87', 's3', 'p1', '20', 'public class Memo {\r\n	\r\n	/**\r\n	 * 正确的电话号码\r\n	 * */\r\n	public void ifPhone(){\r\n		String phone = \"1989889\";\r\n		boolean result = phone.matches(\"[0-9]{11}\");\r\n		if(result)\r\n			System.out.println(\"正确的电话号码\");\r\n		else\r\n			System.out.println(\"错误的电话号码\");\r\n	\r\n	}\r\n	\r\n	public static void main(String[] args) {\r\n		\r\n		Memo memo = new Memo();\r\n		memo.ifPhone();\r\n		String m = \"\";\r\n		m = \"hello \";\r\n		m = m+\"\\n\";\r\n		m = m+\"world\";\r\n		System.out.println(m);\r\n	}\r\n\r\n}', '0', 'essay', 'N');
INSERT INTO result VALUES ('88', 's4', 'p1', '3', 'B', '5', 'single', 'Y');
INSERT INTO result VALUES ('89', 's4', 'p1', '4', 'D', '5', 'single', 'Y');
INSERT INTO result VALUES ('90', 's4', 'p1', '5', 'D', '5', 'single', 'Y');
INSERT INTO result VALUES ('91', 's4', 'p1', '8', 'BC', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('92', 's4', 'p1', '9', 'BC', '0', 'multiple', 'N');
INSERT INTO result VALUES ('93', 's4', 'p1', '10', 'AB', '0', 'multiple', 'N');
INSERT INTO result VALUES ('94', 's4', 'p1', '16', '抽象方法              ', '0', 'fill', 'N');
INSERT INTO result VALUES ('95', 's4', 'p1', '17', '编辑、编译、运行             ', '0', 'fill', 'N');
INSERT INTO result VALUES ('96', 's4', 'p1', '18', '2            ', '0', 'fill', 'N');
INSERT INTO result VALUES ('97', 's4', 'p1', '19', 'socket即套接字                ', '0', 'essay', 'N');
INSERT INTO result VALUES ('98', 's4', 'p1', '20', 'public class Memo {\r\n	\r\n	/**\r\n	 * 正确的电话号码\r\n	 * */\r\n	public void ifPhone(){\r\n		String phone = \"1989889\";\r\n		boolean result = phone.matches(\"[0-9]{11}\");\r\n		if(result)\r\n			System.out.println(\"正确的电话号码\");\r\n		else\r\n			System.out.println(\"错误的电话号码\");\r\n	\r\n	}\r\n	\r\n	public static void main(String[] args) {\r\n		\r\n		Memo memo = new Memo();\r\n		memo.ifPhone();\r\n		String m = \"\";\r\n		m = \"hello \";\r\n		m = m+\"\\n\";\r\n		m = m+\"world\";\r\n		System.out.println(m);\r\n	}\r\n\r\n}               ', '0', 'essay', 'N');
INSERT INTO result VALUES ('99', 's2', 'p1', '3', 'B', '5', 'single', 'Y');
INSERT INTO result VALUES ('100', 's2', 'p1', '4', 'D', '5', 'single', 'Y');
INSERT INTO result VALUES ('101', 's2', 'p1', '5', 'D', '5', 'single', 'Y');
INSERT INTO result VALUES ('102', 's2', 'p1', '8', 'BC', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('103', 's2', 'p1', '9', 'A', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('104', 's2', 'p1', '10', 'C', '5', 'multiple', 'Y');
INSERT INTO result VALUES ('105', 's2', 'p1', '11', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('106', 's2', 'p1', '12', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('107', 's2', 'p1', '13', 'N', '3', 'judge', 'Y');
INSERT INTO result VALUES ('108', 's2', 'p1', '14', 'N', '3', 'judge', 'Y');
INSERT INTO result VALUES ('109', 's2', 'p1', '15', 'Y', '3', 'judge', 'Y');
INSERT INTO result VALUES ('110', 's2', 'p1', '16', '抽象               ', '0', 'fill', 'N');
INSERT INTO result VALUES ('111', 's2', 'p1', '17', '编辑、编译、运行               ', '0', 'fill', 'N');
INSERT INTO result VALUES ('112', 's2', 'p1', '18', '2             ', '0', 'fill', 'N');

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

-- ----------------------------
-- View structure for `cs_view`
-- ----------------------------
DROP VIEW IF EXISTS `cs_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cs_view` AS select `student`.`studentName` AS `studentName`,`paper`.`paperId` AS `paperId`,`paper`.`paperName` AS `paperName`,`cs`.`courseId` AS `courseId`,`cs`.`id` AS `id`,`cs`.`studentId` AS `studentId`,`paper`.`paperSubject` AS `paperSubject` from ((`student` join `paper`) join `cs`);

-- ----------------------------
-- View structure for `grade_view`
-- ----------------------------
DROP VIEW IF EXISTS `grade_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `grade_view` AS select `result`.`id` AS `id`,`result`.`studentId` AS `studentId`,`student`.`studentName` AS `studentName`,`result`.`paperId` AS `paperId`,`paper`.`courseId` AS `courseId`,`result`.`mark` AS `mark`,`paper`.`paperSubject` AS `paperSubject`,`paper`.`paperMaker` AS `paperMaker`,`paper`.`paperName` AS `paperName`,`result`.`signStatus` AS `signStatus` from ((`result` join `student`) join `paper`);
