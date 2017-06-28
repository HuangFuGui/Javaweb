-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2017-06-28 15:16:43
-- 服务器版本： 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `youandme`
--
CREATE DATABASE IF NOT EXISTS `youandme` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `youandme`;

-- --------------------------------------------------------

--
-- 表的结构 `comment_info`
--

CREATE TABLE IF NOT EXISTS `comment_info` (
  `comment_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dynamics_id` int(10) unsigned NOT NULL,
  `send_id` int(10) unsigned DEFAULT '0',
  `receive_username` varchar(100) NOT NULL,
  `comment_text` varchar(500) NOT NULL,
  `like_num` int(10) unsigned DEFAULT '0',
  `comment_num` int(10) unsigned DEFAULT '0',
  `comment_time` timestamp NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- 转存表中的数据 `comment_info`
--

INSERT INTO `comment_info` (`comment_id`, `dynamics_id`, `send_id`, `receive_username`, `comment_text`, `like_num`, `comment_num`, `comment_time`) VALUES
(13, 46, 118, '我是黄复贵', '厉害！\n', 0, 0, '2017-03-09 02:47:11');

-- --------------------------------------------------------

--
-- 表的结构 `dynamics_like_user`
--

CREATE TABLE IF NOT EXISTS `dynamics_like_user` (
  `dynamics_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `dynamics_like_user`
--

INSERT INTO `dynamics_like_user` (`dynamics_id`, `user_id`) VALUES
(46, 118);

-- --------------------------------------------------------

--
-- 表的结构 `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `message_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `from_id` int(10) unsigned DEFAULT NULL,
  `from_name` varchar(100) DEFAULT NULL,
  `to_id` int(10) unsigned DEFAULT NULL,
  `message_text` varchar(500) DEFAULT NULL,
  `message_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `message`
--

INSERT INTO `message` (`message_id`, `from_id`, `from_name`, `to_id`, `message_text`, `message_date`) VALUES
(1, 118, '我是黄复贵', 123, '你在干嘛？', '2017-03-09 02:45:37'),
(2, 123, '杨千嬅', 118, '恩', '2017-03-09 02:45:45'),
(3, 123, '杨千嬅', 118, '哈哈', '2017-04-07 14:58:33');

-- --------------------------------------------------------

--
-- 表的结构 `plupload_file`
--

CREATE TABLE IF NOT EXISTS `plupload_file` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `file_name` varchar(300) NOT NULL,
  `upload_username` varchar(100) NOT NULL,
  `upload_time` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `plupload_file`
--

INSERT INTO `plupload_file` (`id`, `file_name`, `upload_username`, `upload_time`) VALUES
(1, '郭富城 - 對你愛不完 (電視 mv).mp4', '我是黄复贵', '2017-03-09 02:48:12'),
(2, '[J[9`P6V1T1Y_HQNTT(4~SA.png', '我是黄复贵', '2017-03-09 02:48:13');

-- --------------------------------------------------------

--
-- 表的结构 `reply_info`
--

CREATE TABLE IF NOT EXISTS `reply_info` (
  `reply_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `comment_id` int(10) unsigned DEFAULT NULL,
  `send_id` int(10) unsigned DEFAULT NULL,
  `receive_username` varchar(300) DEFAULT NULL,
  `reply_text` varchar(500) DEFAULT NULL,
  `like_num` int(10) unsigned DEFAULT '0',
  `reply_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`reply_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=43 ;

--
-- 转存表中的数据 `reply_info`
--

INSERT INTO `reply_info` (`reply_id`, `comment_id`, `send_id`, `receive_username`, `reply_text`, `like_num`, `reply_time`) VALUES
(42, 13, 123, '我是黄复贵', '是啊！', 0, '2017-03-09 02:47:31');

-- --------------------------------------------------------

--
-- 表的结构 `social_dynamics`
--

CREATE TABLE IF NOT EXISTS `social_dynamics` (
  `dynamics_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `dynamics_text` varchar(500) DEFAULT NULL,
  `dynamics_file` varchar(300) DEFAULT NULL,
  `create_time` timestamp NOT NULL,
  `like_num` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`dynamics_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=49 ;

--
-- 转存表中的数据 `social_dynamics`
--

INSERT INTO `social_dynamics` (`dynamics_id`, `user_id`, `dynamics_text`, `dynamics_file`, `create_time`, `like_num`) VALUES
(46, 118, '郭富城！', '118/郭富城 - 對你愛不完 (電視 mv).mp4', '2017-03-09 02:46:20', 1),
(47, 118, 'asdad', '118/优秀学生申请表（黄复贵）.doc', '2017-05-02 06:10:30', 0),
(48, 118, 'asdasd', '118/评奖评优奖项梳理.docx', '2017-05-02 06:12:09', 0);

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `head_img` varchar(300) DEFAULT 'common/defaultHeadImg.jpg',
  `join_time` varchar(15) DEFAULT NULL,
  `dynamics_num` int(10) unsigned DEFAULT '0',
  `following_num` int(10) unsigned DEFAULT '0',
  `followers_num` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=128 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `email`, `address`, `description`, `head_img`, `join_time`, `dynamics_num`, `following_num`, `followers_num`) VALUES
(118, '我是黄复贵', '123', '1151650717@qq.com', 'Beijing China', '做自己想做的人，随时都可以做出改变，什么时候开始都合适，从来不会晚。', '118/20160506_131805-1.jpg', '2016-07-25', 3, 0, 0),
(120, 'Hill', '123', 'youyouyou1151650717@qq.comhaha', 'Los Angeles USA', 'I am from LA,is a addicted fans of basketball', '120/isHeadImg24.jpg', '2016-07-28', 2, 0, 0),
(122, '史哥来啦更新哈哈哈', '123', '7@qq.comyyyy', '美国阿斯达斯的多少法所得', '我是史哥点点滴滴大撒旦撒', '122/isHeadImg史哥.jpg', '2016-07-29', 5, 0, 0),
(123, '杨千嬅', '123', '11M@9962556.com', '香港，九龙', '我是大笑姑婆杨千嬅~', '123/杨千嬅.png', '2016-08-04', 4, 0, 0),
(124, '张敬轩', '123', '123@zhangjingxuan.sina.com', '香港', '', '124/isHeadImgzhang.jpg', '2016-08-09', 0, 0, 0),
(125, '~~千嬅喔^^', '123', 'qianhua@qq.com', '英国伦敦', '', '125/isHeadImgmer.jpg', '2016-08-12', 1, 0, 0),
(126, '贵哥', '123', 'guige@qq.', NULL, NULL, 'common/defaultHeadImg.jpg', '2016-08-16', 0, 0, 0),
(127, '黄复贵小号', '123', 'huangfuguiL@', NULL, NULL, 'common/defaultHeadImg.jpg', '2016-08-16', 0, 0, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
