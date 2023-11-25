# 管理后台单独使用的表加admin前缀
# 小程序单独使用的表加app前缀
# 如果是小程序和管理后台共用的表则不用添加任何前缀

drop database if exists reservoir_management;
create database reservoir_management;
use reservoir_management;

# 管理员用户表
drop table if exists `admin_user`;
create table admin_user
(
    id          bigint auto_increment comment '主键'
        primary key,
    name        varchar(32)   not null comment '姓名',
    username    varchar(32)   not null comment '用户名',
    password    varchar(64)   not null comment '密码(加密后)',
    phone       varchar(11)   not null comment '手机号',
    sex         varchar(2)    not null comment '性别',
    status      int default 1 not null comment '状态 0:禁用，1:启用',
    create_time datetime      null comment '创建时间',
    update_time datetime      null comment '更新时间',
    create_user bigint        null comment '创建人',
    update_user bigint        null comment '修改人',
    constraint idx_username
        unique (username)
) comment '员工信息' collate = utf8mb3_bin;
# 插入超级管理员数据
insert into admin_user
values (null, '超级管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e',
        '13726844777', '1', 1, '2023-11-02 15:50:36', '2023-11-02 15:50:36', 1, 1);





#############################################

drop table if exists `app_user`;
CREATE TABLE `app_user`
(
    `id`           int                                                          NOT NULL AUTO_INCREMENT COMMENT '主键唯一标识',
    `realname`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工真实姓名',
    `username`     varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录用户名',
    `password`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
    `phone`        varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
    `gender`       varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   DEFAULT NULL COMMENT '性别',
    `work_place`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '工作地点',
    `home_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '家庭地址',
    `icon`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci comment '用户头像',
    `create_time`  timestamp                                                    NOT NULL COMMENT '该用户创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

# 插入一条测试数据,密码为123456加密后的数据
insert into app_user (id, realname, username, password, phone, gender,
                      work_place, home_address, icon, create_time)
values (null, '张三', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '13012345123', '1', '华南职业技术学院',
        '华山', null, '2023-10-01 00:00:00');