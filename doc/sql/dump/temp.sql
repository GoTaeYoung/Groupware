-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: groupware
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attach_list_table`
--

DROP TABLE IF EXISTS `attach_list_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attach_list_table` (
  `ATTACH_LIST_PK` int(11) NOT NULL AUTO_INCREMENT COMMENT '첨부 파일 목록 기본키',
  `MAIL_PK` int(11) DEFAULT NULL COMMENT '메일 기본키',
  `ATTACH_SUM_SIZE` varchar(255) NOT NULL COMMENT '첨부 파일 총 용량',
  `ATTACH_SUM_COUNT` int(11) NOT NULL COMMENT '첨부 파일 갯수',
  PRIMARY KEY (`ATTACH_LIST_PK`),
  KEY `FK_MAIL_TABLE_TO_ATTACH_LIST_TABLE` (`MAIL_PK`),
  CONSTRAINT `FK_MAIL_TABLE_TO_ATTACH_LIST_TABLE` FOREIGN KEY (`MAIL_PK`) REFERENCES `mail_table` (`MAIL_PK`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='첨부 파일 목록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attach_list_table`
--

LOCK TABLES `attach_list_table` WRITE;
/*!40000 ALTER TABLE `attach_list_table` DISABLE KEYS */;
INSERT INTO `attach_list_table` VALUES (1,15,'171879',2),(2,17,'9788',1);
/*!40000 ALTER TABLE `attach_list_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attach_table`
--

DROP TABLE IF EXISTS `attach_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attach_table` (
  `ATTACH_PK` int(11) NOT NULL AUTO_INCREMENT COMMENT '첨부 파일 기본키',
  `ATTACH_LIST_PK` int(11) NOT NULL COMMENT '첨부 파일 목록 기본키',
  `ATTACH_SIZE` varchar(255) NOT NULL COMMENT '첨부 파일 크기',
  `ATTACH_NAME` varchar(50) NOT NULL COMMENT '첨부 파일 이름',
  `ATTACH_ROUTE` varchar(255) NOT NULL COMMENT '첨부 파일 경로',
  PRIMARY KEY (`ATTACH_PK`),
  KEY `FK_ATTACH_LIST_TABLE_TO_ATTACH_TABLE` (`ATTACH_LIST_PK`),
  CONSTRAINT `FK_ATTACH_LIST_TABLE_TO_ATTACH_TABLE` FOREIGN KEY (`ATTACH_LIST_PK`) REFERENCES `attach_list_table` (`ATTACH_LIST_PK`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='첨부 파일';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attach_table`
--

LOCK TABLES `attach_table` WRITE;
/*!40000 ALTER TABLE `attach_table` DISABLE KEYS */;
INSERT INTO `attach_table` VALUES (1,1,'158853','사진2.jpg','C:\\Users\\GoTaeYoung\\20180327_groupware\\Groupware\\src\\main\\webapp\\temp\\mail_files\\2018-06-10_21-06-24\\사진2.jpg'),(2,1,'13026','장바구니담기.PNG','C:\\Users\\GoTaeYoung\\20180327_groupware\\Groupware\\src\\main\\webapp\\temp\\mail_files\\2018-06-10_21-06-24\\장바구니담기.PNG'),(3,2,'9788','설정.png','C:\\Users\\GoTaeYoung\\20180327_groupware\\Groupware\\src\\main\\webapp\\temp\\mail_files\\2018-06-10_21-08-15\\설정.png');
/*!40000 ALTER TABLE `attach_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mail_table`
--

DROP TABLE IF EXISTS `mail_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mail_table` (
  `MAIL_PK` int(11) NOT NULL AUTO_INCREMENT COMMENT '메일 기본키',
  `USER_ID` varchar(255) NOT NULL COMMENT '사용자 ID',
  `MAIL_SENDER` varchar(40) DEFAULT NULL COMMENT '보낸 사람',
  `MAIL_RECEIVER` varchar(40) DEFAULT NULL COMMENT '받는 사람',
  `MAIL_TITLE` varchar(255) NOT NULL COMMENT '메일 제목',
  `MAIL_DATE` varchar(40) NOT NULL COMMENT '메일 날짜',
  `MAIL_CONTENT` varchar(255) DEFAULT NULL COMMENT '메일 내용',
  `MAIL_BOX_NAME` varchar(50) NOT NULL COMMENT '메일 위치',
  `MAIL_READ` varchar(50) NOT NULL COMMENT '읽음 여부',
  `MAIL_ATTACH` varchar(10) NOT NULL COMMENT '첨부 여부',
  PRIMARY KEY (`MAIL_PK`),
  KEY `FK_USER_TABLE_TO_MAIL_TABLE` (`USER_ID`),
  CONSTRAINT `FK_USER_TABLE_TO_MAIL_TABLE` FOREIGN KEY (`USER_ID`) REFERENCES `user_table` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='메일';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail_table`
--

LOCK TABLES `mail_table` WRITE;
/*!40000 ALTER TABLE `mail_table` DISABLE KEYS */;
INSERT INTO `mail_table` VALUES (14,'gotayung3909','gty3909@naver.com','gty3909@naver.com','하모니카','2018-06-10_19-40-48','<img src=\"\\temp\\mail_write\\2018-06-10_19-40-34.png\"><br style=\"clear:both;\"><p>&nbsp;</p>','/mail_send','0','0'),(15,'gotayung3909','gty3909@naver.com','gty3909@naver.com','파란언덕','2018-06-10_21-06-24','<p>ㄴㅇㄴㅇㄴㅇㅇㄴㄴㅇㄴㅇㄴㅇ<img src=\"\\temp\\mail_write\\2018-06-10_21-06-22.png\">&nbsp;</p>','/mail_send','1','1'),(16,'gotayung3909','gty3909@naver.com','gty3909@naver.com','가자!','2018-06-10_21-07-02','<p>아이에오에</p>','/mail_send','0','0'),(17,'gotayung3909','gty3909@naver.com','gty3909@naver.com','나비야나비야','2018-06-10_21-08-15','<p>날아간다</p>','/mail_send','1','1'),(18,'gotayung3909','gty3909@naver.com','gty3909@naver.com','부릉부릉','2018-06-10_21-09-03','<p>저저낙<img src=\"\\temp\\mail_write\\2018-06-10_21-09-00.jpg\">&nbsp;</p>','/mail_send','0','0'),(21,'gotayung3909','gty3909@naver.com','gty3909@naver.com','메일 테스트','2018-06-14_13-46-34','<p>테스트</p>','/mail_send','1','0');
/*!40000 ALTER TABLE `mail_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_table`
--

DROP TABLE IF EXISTS `user_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_table` (
  `USER_ID` varchar(255) NOT NULL COMMENT '사용자 ID',
  `USER_PASSWORD` varchar(255) NOT NULL COMMENT '사용자 비밀번호',
  `USER_NAME` varchar(50) NOT NULL COMMENT '사용자 이름',
  `USER_MAIL` varchar(40) NOT NULL COMMENT '사용자 메일',
  `USER_GENDER` varchar(10) NOT NULL COMMENT '사용자 성별',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_table`
--

LOCK TABLES `user_table` WRITE;
/*!40000 ALTER TABLE `user_table` DISABLE KEYS */;
INSERT INTO `user_table` VALUES ('gotayung3909','gotayung3714','고태영','gty3909@naver.com','남자');
/*!40000 ALTER TABLE `user_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-14 13:58:05
