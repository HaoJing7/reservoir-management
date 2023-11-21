# 管理后台单独使用的表加admin前缀
# 小程序单独使用的表加app前缀
# 如果是小程序和管理后台共用的表则不用添加任何前缀


drop database if exists reservoir_management;
create database reservoir_management;
use reservoir_management;

# 用户模块
# 管理员登陆表
drop table if exists admin_user;
create table admin_user
(
    id       int auto_increment primary key,
    user_name varchar(30) not null comment '用户名',
    password varchar(30) not null comment '密码',
    problem  varchar(50) not null comment '找回密码的问题',
    answer   varchar(50) not null comment '找回密码的答案'
);
# 管理员登陆信息校验表
create table admin_token
(
    id      int auto_increment primary key,
    user_id int         null comment '用户的id',
    token   varchar(50) null comment 'token用于账号校验'
);

drop table if exists `app_user`;
CREATE TABLE `app_user` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '主键唯一标识',
                            `realname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工真实姓名',
                            `username` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录用户名',
                            `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
                            `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
                            `gender` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
                            `work_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '工作地点',
                            `home_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '家庭地址',
                            `icon`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci comment '用户头像',
                            `create_time` timestamp NOT NULL COMMENT '该用户创建时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# 插入一条测试数据,密码为123456加密后的数据
insert into app_user (id, realname, username, password, phone, gender,
                      work_place, home_address, icon, create_time)
values (null,'张三','zhangsan','e10adc3949ba59abbe56e057f20f883e','13012345123','1','华南职业技术学院',
        '华山',null,'2023-10-01 00:00:00');