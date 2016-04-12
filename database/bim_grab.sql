-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.24 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 bim_grab 的数据库结构
DROP DATABASE IF EXISTS `bim_grab`;
CREATE DATABASE IF NOT EXISTS `bim_grab` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `bim_grab`;


-- 导出  表 bim_grab.cinemamovie_maoyan 结构
CREATE TABLE IF NOT EXISTS `cinemamovie_maoyan` (
  `cinema_id` int(11) unsigned NOT NULL,
  `movie_id` int(11) unsigned NOT NULL,
  `show_dates` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cinema_id`,`movie_id`),
  KEY `fk_cinemamovie_movie_maoyan` (`movie_id`),
  CONSTRAINT `fk_cinemamovie_cinema_maoyan` FOREIGN KEY (`cinema_id`) REFERENCES `cinema_maoyan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinemamovie_mtime 结构
CREATE TABLE IF NOT EXISTS `cinemamovie_mtime` (
  `cinema_id` int(11) unsigned NOT NULL,
  `movie_id` int(11) unsigned NOT NULL,
  `show_dates` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cinema_id`,`movie_id`),
  KEY `fk_cinemamovie_movie_mtime` (`movie_id`),
  CONSTRAINT `fk_cinemamovie_cinema_mtime` FOREIGN KEY (`cinema_id`) REFERENCES `cinema_mtime` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinema_baidu 结构
CREATE TABLE IF NOT EXISTS `cinema_baidu` (
  `id` int(11) unsigned NOT NULL,
  `baidu_city_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '百度城市名',
  `baidu_area` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '百度区域名',
  `city_id` int(11) unsigned DEFAULT NULL COMMENT '城市编号',
  `city_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '城市名',
  `area_id` int(11) unsigned DEFAULT NULL COMMENT '区域编号',
  `area_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '区域名',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinema_maoyan 结构
CREATE TABLE IF NOT EXISTS `cinema_maoyan` (
  `id` int(11) unsigned NOT NULL,
  `maoyan_city_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '猫眼城市名',
  `maoyan_area` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '猫眼区域',
  `city_id` int(11) unsigned DEFAULT NULL COMMENT '城市编号',
  `city_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '城市名',
  `area_id` int(11) unsigned DEFAULT NULL COMMENT '区域编号',
  `area_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '区域名',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinema_mtime 结构
CREATE TABLE IF NOT EXISTS `cinema_mtime` (
  `id` int(11) unsigned NOT NULL,
  `mtime_city_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `mtime_area` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `city_id` int(11) unsigned DEFAULT NULL,
  `city_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `area_id` int(11) unsigned DEFAULT NULL,
  `area_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '区域',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址',
  `latitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '经度',
  `longitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '纬度',
  `rating` float(3,1) DEFAULT NULL COMMENT '评分',
  `has_3d` tinyint(1) DEFAULT NULL COMMENT '是否3d',
  `has_imax` tinyint(1) DEFAULT NULL COMMENT '是否IMax',
  `has_Wifi` tinyint(1) DEFAULT NULL COMMENT '是否Wifi',
  `has_Park` tinyint(1) DEFAULT NULL COMMENT '是否停车',
  `route` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话json',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinema_taobao 结构
CREATE TABLE IF NOT EXISTS `cinema_taobao` (
  `id` int(11) unsigned NOT NULL,
  `city_id` int(11) unsigned NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `area` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '区域',
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址',
  `latitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '经度',
  `longitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '纬度',
  `preferential` tinyint(1) unsigned NOT NULL COMMENT '是否特惠',
  `tel` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话json',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `json_md5` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinema_weixin 结构
CREATE TABLE IF NOT EXISTS `cinema_weixin` (
  `id` int(11) unsigned NOT NULL,
  `weixin_city_id` int(11) unsigned NOT NULL COMMENT '微信城市编号',
  `weixin_city_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '微信城市名',
  `weixin_area` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '微信区域名',
  `city_id` int(11) unsigned DEFAULT NULL COMMENT '城市编号',
  `city_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '城市名',
  `area_id` int(11) unsigned DEFAULT NULL COMMENT '区域编号',
  `area_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '区域名',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址',
  `latitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '经度',
  `longitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '纬度',
  `tel` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话json',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cityarea_baidu 结构
CREATE TABLE IF NOT EXISTS `cityarea_baidu` (
  `id` int(11) unsigned NOT NULL,
  `city_id` int(11) unsigned NOT NULL,
  `city_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_baidu_area_city` (`city_id`),
  CONSTRAINT `fk_area_city_baidu` FOREIGN KEY (`city_id`) REFERENCES `city_baidu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cityarea_mtime 结构
CREATE TABLE IF NOT EXISTS `cityarea_mtime` (
  `id` int(11) unsigned NOT NULL,
  `city_id` int(11) unsigned NOT NULL,
  `city_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mtime_area_city` (`city_id`),
  CONSTRAINT `fk_area_city_mtime` FOREIGN KEY (`city_id`) REFERENCES `city_mtime` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.city_baidu 结构
CREATE TABLE IF NOT EXISTS `city_baidu` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.city_maoyan 结构
CREATE TABLE IF NOT EXISTS `city_maoyan` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `first_letter` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `pinyin` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.city_mtime 结构
CREATE TABLE IF NOT EXISTS `city_mtime` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.city_taobao 结构
CREATE TABLE IF NOT EXISTS `city_taobao` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `first_letter` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `pinyin` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.city_weixin 结构
CREATE TABLE IF NOT EXISTS `city_weixin` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `key_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.comment_mtime 结构
CREATE TABLE IF NOT EXISTS `comment_mtime` (
  `id` int(11) unsigned NOT NULL,
  `movie_id` int(11) unsigned NOT NULL,
  `title` varchar(500) COLLATE utf8_unicode_ci NOT NULL COMMENT '标题',
  `nick_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户昵称',
  `rating` float(3,1) NOT NULL COMMENT '评分',
  `content` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '内容',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_comment_movie_mtime` (`movie_id`),
  CONSTRAINT `fk_comment_movie_mtime` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing_mtime` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.image_mtime 结构
CREATE TABLE IF NOT EXISTS `image_mtime` (
  `id` int(11) unsigned NOT NULL,
  `movie_id` int(11) unsigned NOT NULL,
  `image_url` varchar(500) COLLATE utf8_unicode_ci NOT NULL COMMENT '图片路径',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_image_movie_mtime` (`movie_id`),
  CONSTRAINT `fk_image_movie_mtime` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing_mtime` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.movieshowing_baidu 结构
CREATE TABLE IF NOT EXISTS `movieshowing_baidu` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `image` varchar(400) COLLATE utf8_unicode_ci NOT NULL COMMENT '主图片',
  `rating` float(3,1) NOT NULL COMMENT '评分',
  `content` varchar(2000) COLLATE utf8_unicode_ci NOT NULL COMMENT '简述',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.movieshowing_maoyan 结构
CREATE TABLE IF NOT EXISTS `movieshowing_maoyan` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `image` varchar(400) COLLATE utf8_unicode_ci NOT NULL COMMENT '主图片',
  `rating` float(3,1) NOT NULL COMMENT '评分',
  `is_imax` tinyint(1) NOT NULL COMMENT '是否imax',
  `is_3d` tinyint(1) NOT NULL COMMENT '是否3d',
  `directors` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '导演',
  `actors` varchar(3000) COLLATE utf8_unicode_ci NOT NULL COMMENT '演员',
  `type` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型',
  `content` varchar(2000) COLLATE utf8_unicode_ci NOT NULL COMMENT '简述',
  `runTime` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '时长',
  `ver` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '版本',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.movieshowing_mtime 结构
CREATE TABLE IF NOT EXISTS `movieshowing_mtime` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `image` varchar(400) COLLATE utf8_unicode_ci NOT NULL COMMENT '主图片',
  `rating` float(3,1) NOT NULL COMMENT '评分',
  `is_imax` tinyint(1) NOT NULL COMMENT '是否imax',
  `is_3d` tinyint(1) NOT NULL COMMENT '是否3d',
  `directors` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '导演',
  `actors` varchar(3000) COLLATE utf8_unicode_ci NOT NULL COMMENT '演员',
  `type` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型',
  `content` varchar(2000) COLLATE utf8_unicode_ci NOT NULL COMMENT '简述',
  `runTime` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '时长',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.movieshowing_weixin 结构
CREATE TABLE IF NOT EXISTS `movieshowing_weixin` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `image` varchar(400) COLLATE utf8_unicode_ci NOT NULL COMMENT '主图片',
  `rating` float(3,1) NOT NULL COMMENT '评分',
  `directors` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '导演',
  `actors` varchar(3000) COLLATE utf8_unicode_ci NOT NULL COMMENT '演员',
  `type` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型',
  `content` varchar(2000) COLLATE utf8_unicode_ci NOT NULL COMMENT '简述',
  `runTime` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '时长',
  `ver` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '版本',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.screening_baidu 结构
CREATE TABLE IF NOT EXISTS `screening_baidu` (
  `cinema_id` int(11) unsigned NOT NULL COMMENT '影院',
  `movie_id` int(11) unsigned NOT NULL COMMENT '影片',
  `show_date` date NOT NULL COMMENT '日期',
  `start_time` time NOT NULL COMMENT '开始时间',
  `end_time` time NOT NULL COMMENT '结束时间',
  `language` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '语种',
  `hall` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '厅位',
  `version` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型',
  `sale_price` float NOT NULL COMMENT '价格',
  `cinema_price` float NOT NULL COMMENT '原价',
  `ticket_url` varchar(3000) COLLATE utf8_unicode_ci NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cinema_id`,`movie_id`,`show_date`,`start_time`,`hall`),
  KEY `fk_screening_movie_baidu` (`movie_id`),
  CONSTRAINT `fk_screening_cinema_baidu` FOREIGN KEY (`cinema_id`) REFERENCES `cinema_baidu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_screening_movie_baidu` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing_baidu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.screening_maoyan 结构
CREATE TABLE IF NOT EXISTS `screening_maoyan` (
  `cinema_id` int(11) unsigned NOT NULL COMMENT '影院',
  `movie_id` int(11) unsigned NOT NULL COMMENT '影片',
  `show_date` date NOT NULL COMMENT '日期',
  `start_time` time NOT NULL COMMENT '开始时间',
  `end_time` time NOT NULL COMMENT '结束时间',
  `language` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '语种',
  `hall` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '厅位',
  `version` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型',
  `sale_price` float NOT NULL COMMENT '价格',
  `cinema_price` float NOT NULL COMMENT '原价',
  `ticket_url` varchar(3000) COLLATE utf8_unicode_ci NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cinema_id`,`movie_id`,`show_date`,`start_time`,`hall`),
  KEY `showplan_maoyan_cinema_id` (`cinema_id`),
  KEY `showplan_maoyan_movie_id` (`movie_id`),
  KEY `showplan_maoyan_show_date` (`show_date`),
  CONSTRAINT `fk_screening_cinema_maoyan` FOREIGN KEY (`cinema_id`) REFERENCES `cinema_maoyan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_screening_movie_maoyan` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing_maoyan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.screening_mtime 结构
CREATE TABLE IF NOT EXISTS `screening_mtime` (
  `cinema_id` int(11) unsigned NOT NULL COMMENT '影院',
  `movie_id` int(11) unsigned NOT NULL COMMENT '影片',
  `show_date` date NOT NULL COMMENT '日期',
  `start_time` time NOT NULL COMMENT '开始时间',
  `end_time` time NOT NULL COMMENT '结束时间',
  `language` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '语种',
  `hall` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '厅位',
  `version` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型',
  `sale_price` float NOT NULL COMMENT '价格',
  `cinema_price` float NOT NULL COMMENT '原价',
  `ticket_url` varchar(3000) COLLATE utf8_unicode_ci NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cinema_id`,`movie_id`,`show_date`,`start_time`,`hall`),
  KEY `showplan_maoyan_cinema_id` (`cinema_id`),
  KEY `showplan_maoyan_movie_id` (`movie_id`),
  KEY `showplan_maoyan_show_date` (`show_date`),
  CONSTRAINT `fk_screening_cinema_mtime` FOREIGN KEY (`cinema_id`) REFERENCES `cinema_mtime` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_screening_movie_mtime` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing_mtime` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.screening_weixin 结构
CREATE TABLE IF NOT EXISTS `screening_weixin` (
  `cinema_id` int(11) unsigned NOT NULL COMMENT '影院',
  `movie_id` int(11) unsigned NOT NULL COMMENT '影片',
  `show_date` date NOT NULL COMMENT '日期',
  `start_time` time NOT NULL COMMENT '开始时间',
  `end_time` time NOT NULL COMMENT '结束时间',
  `language` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '语种',
  `hall` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '厅位',
  `version` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型',
  `sale_price` float NOT NULL COMMENT '价格',
  `cinema_price` float NOT NULL COMMENT '原价',
  `ticket_url` varchar(3000) COLLATE utf8_unicode_ci NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cinema_id`,`movie_id`,`show_date`,`start_time`,`hall`),
  KEY `showplan_maoyan_cinema_id` (`cinema_id`),
  KEY `showplan_maoyan_movie_id` (`movie_id`),
  KEY `showplan_maoyan_show_date` (`show_date`),
  CONSTRAINT `fk_screening_cinema_weixin` FOREIGN KEY (`cinema_id`) REFERENCES `cinema_weixin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_screening_movie_weixin` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing_weixin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
