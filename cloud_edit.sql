# Host: 120.78.52.0  (Version 5.7.20-0ubuntu0.16.04.1-log)
# Date: 2018-07-30 11:24:20
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "e_edit"
#

DROP TABLE IF EXISTS `e_edit`;
CREATE TABLE `e_edit` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '小组ID',
  `user_name` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父ID',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文档表';

#
# Data for table "e_edit"
#


#
# Structure for table "e_edit_info"
#

DROP TABLE IF EXISTS `e_edit_info`;
CREATE TABLE `e_edit_info` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `edit_id` bigint(1) NOT NULL DEFAULT '0',
  `info` text COMMENT '正文',
  `type` int(1) NOT NULL DEFAULT '1',
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='文档正文表';

#
# Data for table "e_edit_info"
#


#
# Structure for table "e_edit_log"
#

DROP TABLE IF EXISTS `e_edit_log`;
CREATE TABLE `e_edit_log` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `content` text NOT NULL COMMENT '日志',
  `edit_id` bigint(20) NOT NULL DEFAULT '0',
  `type` varchar(255) NOT NULL DEFAULT '' COMMENT '类型，创建，修改，分享，删除',
  `status` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='编辑log';

#
# Data for table "e_edit_log"
#


#
# Structure for table "e_file"
#

DROP TABLE IF EXISTS `e_file`;
CREATE TABLE `e_file` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `edit_id` bigint(20) NOT NULL DEFAULT '0',
  `file_name` varchar(255) NOT NULL DEFAULT '' COMMENT '文件名',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '文件路径',
  `status` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件';

#
# Data for table "e_file"
#


#
# Structure for table "u_group"
#

DROP TABLE IF EXISTS `u_group`;
CREATE TABLE `u_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) NOT NULL DEFAULT '',
  `type` varchar(255) NOT NULL DEFAULT '' COMMENT '公开/私有',
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小组';

#
# Data for table "u_group"
#


#
# Structure for table "u_group_user"
#

DROP TABLE IF EXISTS `u_group_user`;
CREATE TABLE `u_group_user` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '小组ID',
  `type` varchar(255) NOT NULL DEFAULT '' COMMENT '类型',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户小组关联';

#
# Data for table "u_group_user"
#


#
# Structure for table "u_user"
#

DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `Id` bigint(1) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `secret` varchar(255) DEFAULT NULL COMMENT '安全密钥',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Data for table "u_user"
#

INSERT INTO `u_user` VALUES (1,'2018-07-21 15:35:35','2018-07-21 15:35:35','亮亮','liliang941107@sina.com','12345678901','0rI7VIxz2bWxVOTjewZXUg==',NULL,1);
