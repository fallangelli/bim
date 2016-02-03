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
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `district_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '区域',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `firstSpell` varchar(500) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '地址',
  `latitude` varchar(100) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '经度',
  `longitude` varchar(100) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '纬度',
  `pos` point DEFAULT NULL,
  `rating` float(3,1) DEFAULT NULL COMMENT '评分',
  `has_3d` tinyint(1) DEFAULT NULL COMMENT '是否3d',
  `has_imax` tinyint(1) DEFAULT NULL COMMENT '是否IMax',
  `has_Wifi` tinyint(1) DEFAULT NULL COMMENT '是否Wifi',
  `has_Park` tinyint(1) DEFAULT NULL COMMENT '是否停车',
  `tel` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话json',
  `route` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_maoyan` int(11) DEFAULT NULL,
  `id_mtime` int(11) DEFAULT NULL,
  `id_baidu` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `district_id_name` (`district_id`,`name`),
  KEY `cinema_id_maoyan` (`id_maoyan`),
  KEY `cinema_id_mtime` (`id_mtime`),
  KEY `cinema_id_baidu` (`id_baidu`),
  CONSTRAINT `fk_cinema_area` FOREIGN KEY (`district_id`) REFERENCES `cityarea` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_base.city 结构
CREATE TABLE IF NOT EXISTS `city` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `first_letter` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `pinyin` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_base.cityarea 结构
CREATE TABLE IF NOT EXISTS `cityarea` (
  `id` int(16) unsigned NOT NULL,
  `city_id` int(16) unsigned NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `city_id_area` (`city_id`,`name`),
  CONSTRAINT `fk_area_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_base.comment 结构
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `movie_id` int(11) unsigned NOT NULL DEFAULT '0',
  `title` varchar(500) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '标题',
  `nick_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '用户昵称',
  `rating` float(3,1) NOT NULL DEFAULT '0.0' COMMENT '评分',
  `content` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '内容',
  `source_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '来源',
  `id_mtime` int(11) unsigned NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_comment_movie_mtime` (`movie_id`),
  KEY `fk_cmment_source` (`source_id`),
  CONSTRAINT `fk_cmment_source` FOREIGN KEY (`source_id`) REFERENCES `source` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_movieshowing` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_base.image 结构
CREATE TABLE IF NOT EXISTS `image` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `movie_id` int(11) unsigned NOT NULL,
  `image_url` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_image_movie` (`movie_id`),
  CONSTRAINT `fk_image_movie` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_base.movieshowing 结构
CREATE TABLE IF NOT EXISTS `movieshowing` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `image` varchar(400) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '主图片',
  `rating` float(3,1) NOT NULL DEFAULT '0.0' COMMENT '评分',
  `is_imax` tinyint(1) DEFAULT '0' COMMENT '是否imax',
  `is_3d` tinyint(1) DEFAULT '0' COMMENT '是否3d',
  `directors` varchar(50) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '导演',
  `actors` varchar(3000) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '演员',
  `type` varchar(100) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '类型',
  `content` varchar(2000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '简述',
  `runTime` varchar(50) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '时长',
  `id_maoyan` int(11) unsigned DEFAULT NULL,
  `id_mtime` int(11) unsigned DEFAULT NULL,
  `id_baidu` int(11) unsigned DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `movieshowing_id_maoyan` (`id_maoyan`),
  KEY `movieshowing_id_mtime` (`id_mtime`),
  KEY `movieshowing_id_baidu` (`id_baidu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_base.screening 结构
CREATE TABLE IF NOT EXISTS `screening` (
  `cinema_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '影院',
  `movie_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '影片',
  `show_date` date NOT NULL COMMENT '日期',
  `start_time` time NOT NULL COMMENT '开始时间',
  `end_time` time NOT NULL COMMENT '结束时间',
  `language` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '语种',
  `hall` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '厅位',
  `version` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型',
  `sale_price` float NOT NULL COMMENT '原价',
  `cinema_price` float NOT NULL COMMENT '价格',
  `source_id` int(10) unsigned NOT NULL COMMENT '来源',
  `ticket_url` varchar(3000) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cinema_id`,`movie_id`,`show_date`,`start_time`,`hall`,`source_id`),
  KEY `fk_screening_source` (`source_id`),
  KEY `fk_screening_movie` (`movie_id`),
  CONSTRAINT `fk_screening_cinema` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_screening_movie` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_screening_source` FOREIGN KEY (`source_id`) REFERENCES `source` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_base.source 结构
CREATE TABLE IF NOT EXISTS `source` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出 bim_grab 的数据库结构
DROP DATABASE IF EXISTS `bim_grab`;
CREATE DATABASE IF NOT EXISTS `bim_grab` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `bim_grab`;


-- 导出  表 bim_grab.cinemamovie_maoyan 结构
CREATE TABLE IF NOT EXISTS `cinemamovie_maoyan` (
  `cinema_id` int(11) unsigned NOT NULL,
  `movie_id` int(11) unsigned NOT NULL,
  `show_dates` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cinema_id`,`movie_id`),
  KEY `fk_cinemamovie_movie_maoyan` (`movie_id`),
  CONSTRAINT `fk_cinemamovie_cinema_maoyan` FOREIGN KEY (`cinema_id`) REFERENCES `cinema_maoyan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cinemamovie_movie_maoyan` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing_maoyan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinemamovie_mtime 结构
CREATE TABLE IF NOT EXISTS `cinemamovie_mtime` (
  `cinema_id` int(11) unsigned NOT NULL,
  `movie_id` int(11) unsigned NOT NULL,
  `show_dates` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cinema_id`,`movie_id`),
  KEY `fk_cinemamovie_movie_mtime` (`movie_id`),
  CONSTRAINT `fk_cinemamovie_cinema_mtime` FOREIGN KEY (`cinema_id`) REFERENCES `cinema_mtime` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cinemamovie_movie_mtime` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing_mtime` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinema_baidu 结构
CREATE TABLE IF NOT EXISTS `cinema_baidu` (
  `id` int(11) unsigned NOT NULL,
  `baidu_city_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `baidu_area` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '区域',
  `city_id` int(11) unsigned DEFAULT NULL,
  `city_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `area_id` int(11) unsigned DEFAULT NULL,
  `area_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
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
  `id` int(11) unsigned NOT NULL,
  `maoyan_city_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `maoyan_area` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '区域',
  `city_id` int(11) unsigned DEFAULT NULL,
  `city_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `area_id` int(11) unsigned DEFAULT NULL,
  `area_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.cinema_mtime 结构
CREATE TABLE IF NOT EXISTS `cinema_mtime` (
  `id` int(11) unsigned NOT NULL,
  `mtime_city_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `mtime_area` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `city_id` int(11) unsigned DEFAULT '0',
  `city_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT '0',
  `area_id` int(11) unsigned DEFAULT '0',
  `area_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '区域',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '地址',
  `latitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '经度',
  `longitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '纬度',
  `rating` float(3,1) DEFAULT '0.0' COMMENT '评分',
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
  `id` int(11) unsigned NOT NULL,
  `city_id` int(11) unsigned NOT NULL DEFAULT '0',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `area` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '区域',
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '地址',
  `latitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '经度',
  `longitude` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '纬度',
  `preferential` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否特惠',
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
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.city_maoyan 结构
CREATE TABLE IF NOT EXISTS `city_maoyan` (
  `id` int(11) unsigned NOT NULL,
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
  `id` int(11) unsigned NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.city_taobao 结构
CREATE TABLE IF NOT EXISTS `city_taobao` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `first_letter` varchar(4) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `pinyin` varchar(300) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.comment_mtime 结构
CREATE TABLE IF NOT EXISTS `comment_mtime` (
  `id` int(11) unsigned NOT NULL,
  `movie_id` int(11) unsigned NOT NULL DEFAULT '0',
  `title` varchar(500) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '标题',
  `nick_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '用户昵称',
  `rating` float(3,1) NOT NULL DEFAULT '0.0' COMMENT '评分',
  `content` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_comment_movie_mtime` (`movie_id`),
  CONSTRAINT `fk_comment_movie_mtime` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing_mtime` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.image_mtime 结构
CREATE TABLE IF NOT EXISTS `image_mtime` (
  `id` int(11) unsigned NOT NULL,
  `movie_id` int(11) unsigned NOT NULL DEFAULT '0',
  `image_url` varchar(500) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '图片路径',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_image_movie_mtime` (`movie_id`),
  CONSTRAINT `fk_image_movie_mtime` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing_mtime` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.movieshowing_baidu 结构
CREATE TABLE IF NOT EXISTS `movieshowing_baidu` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `image` varchar(400) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '主图片',
  `rating` float(3,1) NOT NULL DEFAULT '0.0' COMMENT '评分',
  `content` varchar(2000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '简述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.movieshowing_maoyan 结构
CREATE TABLE IF NOT EXISTS `movieshowing_maoyan` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `image` varchar(400) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '主图片',
  `rating` float(3,1) NOT NULL DEFAULT '0.0' COMMENT '评分',
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
  `id` int(11) unsigned NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '名称',
  `image` varchar(400) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '主图片',
  `rating` float(3,1) NOT NULL DEFAULT '0.0' COMMENT '评分',
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
  `cinema_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '影院',
  `movie_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '影片',
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
  PRIMARY KEY (`cinema_id`,`movie_id`,`show_date`,`start_time`,`hall`),
  KEY `fk_screening_movie_baidu` (`movie_id`),
  CONSTRAINT `fk_screening_cinema_baidu` FOREIGN KEY (`cinema_id`) REFERENCES `cinema_baidu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_screening_movie_baidu` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing_baidu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。


-- 导出  表 bim_grab.screening_maoyan 结构
CREATE TABLE IF NOT EXISTS `screening_maoyan` (
  `id` int(11) unsigned NOT NULL,
  `cinema_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '影院',
  `movie_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '影片',
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
  `cinema_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '影院',
  `movie_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '影片',
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
  PRIMARY KEY (`cinema_id`,`movie_id`,`show_date`,`start_time`,`hall`),
  KEY `showplan_maoyan_cinema_id` (`cinema_id`),
  KEY `showplan_maoyan_movie_id` (`movie_id`),
  KEY `showplan_maoyan_show_date` (`show_date`),
  CONSTRAINT `fk_screening_cinema_mtime` FOREIGN KEY (`cinema_id`) REFERENCES `cinema_mtime` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_screening_movie_mtime` FOREIGN KEY (`movie_id`) REFERENCES `movieshowing_mtime` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
