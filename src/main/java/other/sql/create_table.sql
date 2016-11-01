use risk_manage;
set names utf8;

CREATE TABLE `user` (
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
  `user_id` BIGINT UNSIGNED COMMENT '用户ID',
  `user_password` VARCHAR(64) NOT NULL COMMENT '用户密码',
  `user_type` TINYINT UNSIGNED NOT NULL COMMENT '用户类型（）',
  `user_switch` TINYINT UNSIGNED NOT NULL COMMENT '用户状态（有效、暂停、无效）',
  `group_id` SMALLINT COMMENT '所属用户组id',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `data_status` TINYINT UNSIGNED NOT NULL COMMENT '数据有效性',
  UNIQUE INDEX `unq_user_id` (`user_id`),
  INDEX `idx_user_type` (`user_type`)
) ENGINE=InnoDB CHARACTER SET=utf8 COMMENT='用户信息表';