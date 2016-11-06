use risk_manage;
set names utf8;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for risk
-- ----------------------------
DROP TABLE IF EXISTS `risk`;
CREATE TABLE `risk` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '风险id',
  `name` varchar(400) NOT NULL COMMENT '风险名称',
  `content` varchar(400) NOT NULL COMMENT '风险内容',
  `possibility` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '可能性，0-低，1-中，2-高',
  `impact` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '影响程度，0-低，1-中，2-高',
  `trigger` varchar(400) NOT NULL COMMENT '触发器/阈值，文本形式',
  `committer` bigint(20) NOT NULL DEFAULT '0' COMMENT '提交者，映射到user表中的id字段',
  `followers` varchar(400) NOT NULL COMMENT '跟踪者id，多个跟踪者以,（英文下）相连',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `data_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '数据有效性，0-有效，1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for risk_track
-- ----------------------------
DROP TABLE IF EXISTS `risk_track`;
CREATE TABLE `risk_track` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `risk_id` bigint(20) NOT NULL COMMENT '风险id',
  `status` tinyint(11) unsigned NOT NULL DEFAULT '0' COMMENT '风险状态,0-风险状态，1-问题状态，2-解决状态',
  `description` varchar(400) NOT NULL COMMENT '风险描述',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `data_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '数据有效性，0-有效，1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(400) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '用户密码',
  `type` tinyint(3) unsigned NOT NULL COMMENT '用户类型,0-普通工程师，1-项目经理',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `data_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '数据有效性，0-有效，1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
