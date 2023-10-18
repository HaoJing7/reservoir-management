# 用户模块
# 用户登陆表r
create table t_user
(
    id       int auto_increment primary key,
    username varchar(50) not null,
    password varchar(50) not null
);

# 登陆信息校验表
create table t_token
(
    id      int auto_increment primary key,
    user_id int         null,
    token   varchar(50) null
);

# 用户详细信息表
create table t_info
(
    id int auto_increment primary key
);
