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
