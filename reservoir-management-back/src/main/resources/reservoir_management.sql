# 管理后台单独使用的表加admin前缀
# 小程序单独使用的表加app前缀
# 如果是小程序和管理后台共用的表则不用添加任何前缀

drop database if exists reservoir_management;
create database reservoir_management;
use reservoir_management;

# 管理员表（只有一个管理员）
drop table if exists `admin_user`;
create table admin_user
(
    id       bigint auto_increment comment '主键'
        primary key,
    username varchar(32) not null comment '用户名',
    password varchar(64) not null comment '密码(MD5加密后)'
) comment '管理员登陆表' collate = utf8mb3_bin;

# 添加一条管理员数据（密码默认为admin，MD5加密）
insert into admin_user values (null, 'admin', '21232f297a57a5a743894a0e4a801fc3');


#############################################

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


# 水库表
create table `app_reservoir` (
                                 `number` varchar(15) not null comment '水库编号',
                                 `name` varchar(50) not null comment '水库名称',
                                 `address` varchar(50) not null comment '水库地址',
                                 `usable_capacity` varchar(50) not null comment '有效库容',
                                 `design_capacity` varchar(50) not null comment '设计库容',
                                 `max_capacity` varchar(50) not null comment '最大库容',
                                 `min_capacity` varchar(50) not null comment '最小库容',
                                 `dead_capacity` varchar(50) not null comment '死库容',
                                 `normal_water_level` varchar(50) not null comment '正常水位',
                                 `meixun_water_level` varchar(50) not null comment '梅汛期水位',
                                 `typhoon_water_level` varchar(50) not null comment '台风期水位',
                                 `flood_water_level` varchar(50) not null comment '洪水期水位',
                                 `max_water_level` varchar(50) not null comment '最高水位',
                                 `dead_water_level` varchar(50) not null comment '死水位'
);

# 插入测试静态数据
INSERT INTO `app_reservoir` VALUES
                                ('R001', '水库一', '地址一', '1000', '1200', '1500', '800', '200', '800', '700', '850', '1000', '1200', '900'),
                                ('R002', '水库二', '地址二', '800', '1000', '1200', '600', '150', '700', '600', '750', '900', '1000', '700'),
                                ('R003', '水库三', '地址三', '1200', '1500', '1800', '1000', '300', '1100', '1000', '1150', '1400', '1500', '1100'),
                                ('R004', '水库四', '地址四', '900', '1100', '1400', '700', '180', '900', '800', '950', '1100', '1200', '850'),
                                ('R005', '水库五', '地址五', '1100', '1300', '1600', '900', '250', '1200', '1100', '1250', '1500', '1600', '1200'),
                                ('R006', '水库六', '地址六', '1300', '1600', '1900', '1100', '350', '1400', '1300', '1450', '1700', '1800', '1300'),
                                ('R007', '水库七', '地址七', '1500', '1800', '2100', '1300', '450', '1600', '1500', '1650', '1900', '2000', '1500'),
                                ('R008', '水库八', '地址八', '1200', '1400', '1700', '1000', '280', '1000', '900', '1050', '1300', '1400', '1000'),
                                ('R009', '水库九', '地址九', '1000', '1300', '1600', '800', '220', '850', '750', '900', '1100', '1200', '850'),
                                ('R010', '水库十', '地址十', '1400', '1700', '2000', '1200', '400', '1200', '1100', '1250', '1500', '1600', '1200');

