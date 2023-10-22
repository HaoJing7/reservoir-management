# 管理后台单独使用的表名前加admin前缀
# 小程序单独使用的表名前加app前缀
# 如果是小程序和管理后台共用的表则不用添加任何前缀

# 用户模块
# 管理员登陆表
create table admin_account
(
    id       int auto_increment primary key,
    username varchar(50) not null,
    password varchar(50) not null
);

# 管理员登陆信息校验表
create table t_token
(
    id      int auto_increment primary key,
    user_id int         null,
    token   varchar(50) null
);

# 管理员详细信息表
create table t_info
(
    id int auto_increment primary key,
    phone int,
    description varchar(30),
    address varchar(50)
);
