-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2017-06-28 15:20:14
-- 服务器版本： 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mybatis`
--
CREATE DATABASE IF NOT EXISTS `mybatis` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `mybatis`;

-- --------------------------------------------------------

--
-- 表的结构 `t_lecture`
--

CREATE TABLE IF NOT EXISTS `t_lecture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lecture_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `t_lecture`
--

INSERT INTO `t_lecture` (`id`, `lecture_name`, `note`) VALUES
(2, '操作系统', '计算机操作系统（第四版）'),
(3, '高性能mysql', '存储原理，事务，锁，多主复制等');

-- --------------------------------------------------------

--
-- 表的结构 `t_role`
--

CREATE TABLE IF NOT EXISTS `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- 转存表中的数据 `t_role`
--

INSERT INTO `t_role` (`id`, `role_name`, `note`) VALUES
(3, 'mybatis', '04-05'),
(20, 'huangfugui', 'learning Mybatis');

-- --------------------------------------------------------

--
-- 表的结构 `t_student`
--

CREATE TABLE IF NOT EXISTS `t_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cnname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `sex` int(11) NOT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `t_student`
--

INSERT INTO `t_student` (`id`, `cnname`, `sex`, `note`) VALUES
(1, '黄复贵', 0, '我是黄复贵，正在学mybatis');

-- --------------------------------------------------------

--
-- 表的结构 `t_student_lecture`
--

CREATE TABLE IF NOT EXISTS `t_student_lecture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `lecture_id` int(11) NOT NULL,
  `grade` decimal(16,2) NOT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `t_student_lecture`
--

INSERT INTO `t_student_lecture` (`id`, `student_id`, `lecture_id`, `grade`, `note`) VALUES
(1, 1, 2, '96.50', '黄复贵操作系统学的非常好！'),
(2, 1, 3, '100.00', '作为一名研发人员，数据库mysql必须精通！');

-- --------------------------------------------------------

--
-- 表的结构 `t_student_selfcard`
--

CREATE TABLE IF NOT EXISTS `t_student_selfcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `native` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `issue_date` date NOT NULL,
  `end_date` date NOT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `t_student_selfcard`
--

INSERT INTO `t_student_selfcard` (`id`, `student_id`, `native`, `issue_date`, `end_date`, `note`) VALUES
(1, 1, '广东深圳', '2014-09-03', '2018-07-01', '黄复贵学生卡1120142038');

-- --------------------------------------------------------

--
-- 表的结构 `t_user`
--

CREATE TABLE IF NOT EXISTS `t_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `cnname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- 转存表中的数据 `t_user`
--

INSERT INTO `t_user` (`id`, `user_name`, `cnname`, `birthday`, `sex`, `email`, `mobile`, `note`) VALUES
(14, 'zhangsan', '黄复贵', '2017-04-10', 'MALE', 'geek_huangfugui@163.com', '13051189772', 'I am huangfugui'),
(18, 'zhangsan', '黄复贵', '2017-05-01', 'MALE', 'geek_huangfugui@163.com', '13051189772', 'I am huangfugui'),
(19, 'zhangsan', '黄复贵', '2017-05-01', 'MALE', 'geek_huangfugui@163.com', '13051189772', 'I am huangfugui'),
(20, 'zhangsan', '黄复贵', '2017-05-01', 'MALE', 'geek_huangfugui@163.com', '13051189772', 'I am huangfugui'),
(21, 'zhangsan', '黄复贵', '2017-05-01', 'MALE', 'geek_huangfugui@163.com', '13051189772', 'I am huangfugui');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
