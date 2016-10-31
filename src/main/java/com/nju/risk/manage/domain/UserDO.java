package com.nju.risk.manage.domain;

import java.util.Date;

/**
 * @author chenyina
 * @version V1.0
 * @Description:
 * @Company:meituan
 * @date 16/10/31
 */

//`id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
//        `user_id` BIGINT UNSIGNED COMMENT '用户ID',
//        `user_type` TINYINT UNSIGNED NOT NULL COMMENT '用户类型（）',
//        `user_switch` TINYINT UNSIGNED NOT NULL COMMENT '用户状态（有效、暂停、无效）',
//        `group_id` SMALLINT COMMENT '所属用户组id',
//        `gmt_create` datetime NOT NULL COMMENT '创建时间',
//        `gmt_modified` datetime NOT NULL COMMENT '修改时间',
//        `data_status` TINYINT UNSIGNED NOT NULL COMMENT '数据有效性',
public class UserDO {
    //主键
    private Integer id;

    //用户id
    private Integer userId;

    //用户类型
    private Integer userType;

    //用户状态
    private Integer userSwitch;

    //用户组id
    private Integer groupId;

    //创建时间
    private Date gmtCreate;

    //修改时间
    private Date gmtModified;

    //数据有效性
    private Integer dataStatus;


}
