/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : online_exam

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-03-21 15:24:58
*/

SET FOREIGN_KEY_CHECKS=0;
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
