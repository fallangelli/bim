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

-- 导出 bim_base 的数据库结构
DROP DATABASE IF EXISTS `bim_base`;
CREATE DATABASE IF NOT EXISTS `bim_base` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `bim_base`;


-- 导出  表 bim_base.cinema 结构
CREATE TABLE IF NOT EXISTS `cinema` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `district_id` int(11) NOT NULL DEFAULT '0' COMMENT '区域',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '地址',
  `latitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '经度',
  `longitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '纬度',
  `rating` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评分',
  `has_3d` tinyint(1) DEFAULT NULL COMMENT '是否3d',
  `has_imax` tinyint(1) DEFAULT NULL COMMENT '是否IMax',
  `has_Wifi` tinyint(1) DEFAULT NULL COMMENT '是否Wifi',
  `has_Park` tinyint(1) DEFAULT NULL COMMENT '是否停车',
  `tel` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话json',
  `route` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_maoyan` int(11) DEFAULT NULL,
  `id_mtime` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `district_id_name` (`district_id`,`name`),
  KEY `id_maoyan` (`id_maoyan`),
  KEY `id_mtime` (`id_mtime`),
  CONSTRAINT `fk_cinema_area` FOREIGN KEY (`district_id`) REFERENCES `city_area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_base.city 结构
CREATE TABLE IF NOT EXISTS `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `first_letter` varchar(4) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `pinyin` varchar(300) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `id_maoyan` int(11) DEFAULT '0',
  `id_mtime` int(11) DEFAULT '0',
  `id_baidu` int(11) DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `id_maoyan` (`id_maoyan`),
  KEY `id_mtime` (`id_mtime`),
  KEY `id_baidu` (`id_baidu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_base.city_area 结构
CREATE TABLE IF NOT EXISTS `city_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` int(11) NOT NULL DEFAULT '0',
  `area` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `city_id_area` (`city_id`,`area`),
  CONSTRAINT `fk_area_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_base.movieshowing 结构
CREATE TABLE IF NOT EXISTS `movieshowing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `image` varchar(400) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '主图片',
  `rating` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '评分',
  `is_imax` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否imax',
  `is_3d` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否3d',
  `directors` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '导演',
  `actors` varchar(3000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '演员',
  `type` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '类型',
  `content` varchar(2000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '简述',
  `runTime` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '时长',
  `id_maoyan` int(11) DEFAULT '0',
  `id_mtime` int(11) DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `id_maoyan` (`id_maoyan`),
  KEY `id_mtime` (`id_mtime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_base.screening 结构
CREATE TABLE IF NOT EXISTS `screening` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cinema_id` int(11) NOT NULL DEFAULT '0' COMMENT '影院',
  `movie_id` int(11) NOT NULL DEFAULT '0' COMMENT '影片',
  `show_date` date NOT NULL COMMENT '日期',
  `start_time` time NOT NULL COMMENT '开始时间',
  `end_time` time NOT NULL COMMENT '结束时间',
  `language` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '语种',
  `hall` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '厅位',
  `version` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型',
  `sale_price` float NOT NULL COMMENT '原价',
  `cinema_price` float NOT NULL COMMENT '价格',
  `source` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '来源',
  `ticket_url` varchar(3000) COLLATE utf8_unicode_ci NOT NULL COMMENT '购票链接',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `showplan_cinema_id` (`cinema_id`),
  KEY `showplan_movie_id` (`movie_id`),
  KEY `showplan_show_date` (`show_date`),
  KEY `showplan_source` (`source`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出 bim_grab 的数据库结构
DROP DATABASE IF EXISTS `bim_grab`;
CREATE DATABASE IF NOT EXISTS `bim_grab` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `bim_grab`;


-- 导出  表 bim_grab.base 结构
CREATE TABLE IF NOT EXISTS `base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinemamovie_maoyan 结构
CREATE TABLE IF NOT EXISTS `cinemamovie_maoyan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cinema_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cinema_id_movie_id` (`cinema_id`,`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinemamovie_mtime 结构
CREATE TABLE IF NOT EXISTS `cinemamovie_mtime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cinema_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `show_dates` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cinema_id_movie_id` (`cinema_id`,`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinema_baidu 结构
CREATE TABLE IF NOT EXISTS `cinema_baidu` (
  `id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL DEFAULT '0',
  `area` varchar(100) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '区域',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinema_maoyan 结构
CREATE TABLE IF NOT EXISTS `cinema_maoyan` (
  `id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `area` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '区域',
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '地址',
  `latitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '经度',
  `longitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '纬度',
  `sell` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否售票',
  `preferential` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否特惠',
  `has_imax` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否imax',
  `tel` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话json',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `json_md5` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinema_mtime 结构
CREATE TABLE IF NOT EXISTS `cinema_mtime` (
  `id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL DEFAULT '0',
  `area` varchar(100) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '区域',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '地址',
  `latitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '经度',
  `longitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '纬度',
  `rating` varchar(100) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '评分',
  `has_3d` tinyint(1) DEFAULT '0' COMMENT '是否3d',
  `has_imax` tinyint(1) DEFAULT '0' COMMENT '是否IMax',
  `has_Wifi` tinyint(1) DEFAULT '0' COMMENT '是否Wifi',
  `has_Park` tinyint(1) DEFAULT '0' COMMENT '是否停车',
  `route` varchar(500) COLLATE utf8_unicode_ci DEFAULT '0',
  `tel` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话json',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinema_taobao 结构
CREATE TABLE IF NOT EXISTS `cinema_taobao` (
  `id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `area` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '区域',
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '地址',
  `latitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '经度',
  `longitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '纬度',
  `preferential` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否特惠',
  `tel` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话json',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `json_md5` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cityarea_baidu 结构
CREATE TABLE IF NOT EXISTS `cityarea_baidu` (
  `id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_baidu_area_city` (`city_id`),
  CONSTRAINT `fk_baidu_area_city` FOREIGN KEY (`city_id`) REFERENCES `city_baidu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cityarea_mtime 结构
CREATE TABLE IF NOT EXISTS `cityarea_mtime` (
  `id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mtime_area_city` (`city_id`),
  CONSTRAINT `fk_mtime_area_city` FOREIGN KEY (`city_id`) REFERENCES `city_mtime` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.city_baidu 结构
CREATE TABLE IF NOT EXISTS `city_baidu` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `first_letter` varchar(4) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `pinyin` varchar(300) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.city_maoyan 结构
CREATE TABLE IF NOT EXISTS `city_maoyan` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `first_letter` varchar(4) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `pinyin` varchar(300) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.city_mtime 结构
CREATE TABLE IF NOT EXISTS `city_mtime` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `first_letter` varchar(4) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `pinyin` varchar(300) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.city_taobao 结构
CREATE TABLE IF NOT EXISTS `city_taobao` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `first_letter` varchar(4) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `pinyin` varchar(300) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.moviecoming 结构
CREATE TABLE IF NOT EXISTS `moviecoming` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `director` varchar(500) COLLATE utf8_unicode_ci DEFAULT '0',
  `actor` varchar(2000) COLLATE utf8_unicode_ci DEFAULT '0',
  `type` varchar(50) COLLATE utf8_unicode_ci DEFAULT '0',
  `source` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `urlMd5` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.movieshowing_baidu 结构
CREATE TABLE IF NOT EXISTS `movieshowing_baidu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `image` varchar(400) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '主图片',
  `rating` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '评分',
  `content` varchar(2000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '简述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.movieshowing_maoyan 结构
CREATE TABLE IF NOT EXISTS `movieshowing_maoyan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `image` varchar(400) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '主图片',
  `rating` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '评分',
  `is_imax` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否imax',
  `is_3d` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否3d',
  `directors` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '导演',
  `actors` varchar(3000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '演员',
  `type` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '类型',
  `content` varchar(2000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '简述',
  `runTime` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '时长',
  `ver` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '版本',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.movieshowing_mtime 结构
CREATE TABLE IF NOT EXISTS `movieshowing_mtime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `image` varchar(400) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '主图片',
  `rating` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '评分',
  `is_imax` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否imax',
  `is_3d` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否3d',
  `directors` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '导演',
  `actors` varchar(3000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '演员',
  `type` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '类型',
  `content` varchar(2000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '简述',
  `runTime` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '时长',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.screening_baidu 结构
CREATE TABLE IF NOT EXISTS `screening_baidu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cinema_id` int(11) NOT NULL DEFAULT '0' COMMENT '影院',
  `movie_id` int(11) NOT NULL DEFAULT '0' COMMENT '影片',
  `show_date` date NOT NULL COMMENT '日期',
  `start_time` time NOT NULL COMMENT '开始时间',
  `end_time` time NOT NULL COMMENT '结束时间',
  `language` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '语种',
  `hall` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '厅位',
  `version` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型',
  `sale_price` float NOT NULL COMMENT '价格',
  `cinema_price` float NOT NULL COMMENT '原价',
  `ticket_url` varchar(3000) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `showplan_maoyan_cinema_id` (`cinema_id`),
  KEY `showplan_maoyan_movie_id` (`movie_id`),
  KEY `showplan_maoyan_show_date` (`show_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.screening_maoyan 结构
CREATE TABLE IF NOT EXISTS `screening_maoyan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cinema_id` int(11) NOT NULL DEFAULT '0' COMMENT '影院',
  `movie_id` int(11) NOT NULL DEFAULT '0' COMMENT '影片',
  `show_date` date NOT NULL COMMENT '日期',
  `start_time` time NOT NULL COMMENT '开始时间',
  `end_time` time NOT NULL COMMENT '结束时间',
  `language` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '语种',
  `hall` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '厅位',
  `version` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型',
  `sale_price` float NOT NULL COMMENT '价格',
  `cinema_price` float NOT NULL COMMENT '原价',
  `ticket_url` varchar(3000) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `showplan_maoyan_cinema_id` (`cinema_id`),
  KEY `showplan_maoyan_movie_id` (`movie_id`),
  KEY `showplan_maoyan_show_date` (`show_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.screening_mtime 结构
CREATE TABLE IF NOT EXISTS `screening_mtime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cinema_id` int(11) NOT NULL DEFAULT '0' COMMENT '影院',
  `movie_id` int(11) NOT NULL DEFAULT '0' COMMENT '影片',
  `show_date` date NOT NULL COMMENT '日期',
  `start_time` time NOT NULL COMMENT '开始时间',
  `end_time` time NOT NULL COMMENT '结束时间',
  `language` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '语种',
  `hall` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '厅位',
  `version` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型',
  `sale_price` float NOT NULL COMMENT '价格',
  `cinema_price` float NOT NULL COMMENT '原价',
  `ticket_url` varchar(3000) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `showplan_maoyan_cinema_id` (`cinema_id`),
  KEY `showplan_maoyan_movie_id` (`movie_id`),
  KEY `showplan_maoyan_show_date` (`show_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
