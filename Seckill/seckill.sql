-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2017-06-28 15:23:11
-- 服务器版本： 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `seckill`
--
CREATE DATABASE IF NOT EXISTS `seckill` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `seckill`;

DELIMITER $$
--
-- 存储过程
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `executeSeckill`(IN fadeSeckillId INT,IN fadeUserPhone VARCHAR (15),IN fadeKillTime TIMESTAMP ,OUT fadeResult INT)
BEGIN
    DECLARE insertCount INT DEFAULT 0;
    START TRANSACTION ;
    INSERT IGNORE success_killed(seckill_id,user_phone,state,create_time) VALUES(fadeSeckillId,fadeUserPhone,0,fadeKillTime);  
    SELECT ROW_COUNT() INTO insertCount;
    IF(insertCount = 0) THEN
      ROLLBACK ;
      SET fadeResult = -1;  
    ELSEIF(insertCount < 0) THEN
      ROLLBACK ;
      SET fadeResult = -2;  
    ELSE   
      UPDATE seckill SET number = number -1 WHERE seckill_id = fadeSeckillId AND start_time < fadeKillTime AND end_time > fadeKillTime AND number > 0;
      SELECT ROW_COUNT() INTO insertCount;
      IF (insertCount = 0)  THEN
        ROLLBACK ;
        SET fadeResult = 0;  
      ELSEIF (insertCount < 0) THEN
        ROLLBACK ;
        SET fadeResult = -2;  
      ELSE
        COMMIT ;    
        SET  fadeResult = 1;  
      END IF;
    END IF;
  END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `seckill`
--

CREATE TABLE IF NOT EXISTS `seckill` (
  `seckill_id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `number` int(11) NOT NULL,
  `start_time` timestamp NOT NULL,
  `end_time` timestamp NOT NULL,
  `create_time` timestamp NOT NULL,
  PRIMARY KEY (`seckill_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- 转存表中的数据 `seckill`
--

INSERT INTO `seckill` (`seckill_id`, `name`, `number`, `start_time`, `end_time`, `create_time`) VALUES
(5, '1000元秒杀iphone6', 91, '2017-03-01 01:50:44', '2017-03-31 01:51:19', '2017-03-01 01:51:19'),
(6, '500元秒杀ipad2', 193, '2017-03-01 01:49:46', '2017-03-31 01:49:46', '2017-03-01 01:49:46'),
(7, '300元秒杀小米4', 297, '2017-02-28 16:00:00', '2017-03-30 16:00:00', '2017-02-28 16:00:00'),
(8, '200元秒杀红米note', 92, '2017-03-01 05:14:00', '2017-03-30 16:00:00', '2017-02-28 16:00:00');

-- --------------------------------------------------------

--
-- 表的结构 `success_killed`
--

CREATE TABLE IF NOT EXISTS `success_killed` (
  `seckill_id` int(5) NOT NULL,
  `user_phone` varchar(15) NOT NULL,
  `state` tinyint(4) NOT NULL,
  `create_time` timestamp NOT NULL,
  PRIMARY KEY (`seckill_id`,`user_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `success_killed`
--

INSERT INTO `success_killed` (`seckill_id`, `user_phone`, `state`, `create_time`) VALUES
(5, '03151189772', 0, '0000-00-00 00:00:00'),
(5, '11051189778', 0, '2016-06-14 13:59:28'),
(5, '11111111111', 0, '2016-06-14 15:06:52'),
(5, '11151189772', 0, '2016-05-28 04:58:24'),
(5, '13051189771', 0, '0000-00-00 00:00:00'),
(5, '13051189772', 0, '0000-00-00 00:00:00'),
(5, '13051189778', 0, '0000-00-00 00:00:00'),
(5, '13051189779', 0, '0000-00-00 00:00:00'),
(5, '13151189772', 0, '0000-00-00 00:00:00'),
(5, '99999999997', 0, '2017-03-09 02:03:46'),
(6, '10051188772', 0, '2016-06-14 13:40:27'),
(6, '11111111111', 0, '2016-06-14 15:16:26'),
(6, '12315648796', 0, '2017-03-09 01:53:53'),
(6, '13051188772', 0, '2016-06-13 14:48:23'),
(6, '13051189772', 0, '2016-06-12 11:59:53'),
(6, '99999999997', 0, '2017-03-09 01:55:14'),
(7, '11051189778', 0, '2016-06-14 06:43:37'),
(7, '11151189772', 0, '2016-05-28 05:10:17'),
(7, '15151189772', 0, '2016-05-29 03:40:12'),
(8, '11111111111', 0, '2016-07-14 03:12:56'),
(8, '11111111112', 0, '2016-07-14 03:22:34'),
(8, '11111111113', 0, '2016-07-14 03:35:37'),
(8, '11111111114', 0, '2016-07-14 03:39:42'),
(8, '11111111115', 0, '2016-07-14 03:42:14'),
(8, '11111111116', 0, '2016-07-14 03:44:10'),
(8, '11111111117', 0, '2016-07-14 03:45:36'),
(8, '11111111118', 0, '2016-07-14 03:46:47');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
