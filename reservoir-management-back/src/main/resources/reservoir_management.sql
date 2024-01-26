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
# 添加一条管理员数据（密码默认为123456，MD5加密）
insert into admin_user
values (null, 'admin', 'e10adc3949ba59abbe56e057f20f883e');


#############################################

# 用户表
drop table if exists `app_user`;
create table `app_user`
(
    `id`           bigint auto_increment primary key comment '主键唯一标识',
    `realname`     varchar(10) not null comment '员工真实姓名',
    `username`     varchar(15) not null unique comment '登录用户名',
    `password`     varchar(50) not null comment '密码',
    `phone`        varchar(11) not null comment '手机号',
    `gender`       varchar(3)   default null comment '性别',
    `work_place`   varchar(255) default null comment '工作地点',
    `home_address` varchar(255) default null comment '家庭地址',
    `icon`         varchar(255) comment '用户头像',
    `create_time`  timestamp comment '创建时间'
);
# 插入一条测试数据,密码为123456加密后的数据
insert into app_user (id, realname, username, password, phone, gender,
                      work_place, home_address, icon, create_time)
values (null, '张三', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '13012345123', '1', '华南职业技术学院',
        '华山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2023-10-01 00:00:00'),
       (null, '太乙真人', 'taiyizhenren', 'e10adc3949ba59abbe56e057f20f883e', '13043245123', '1', '华南职业技术学院',
        '泰山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2023-10-01 00:00:00'),
       (null, '孙悟空', 'sunwukong', 'e10adc3949ba59abbe56e057f20f883e', '13075645123', '1', '华南职业技术学院',
        '泰山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2023-10-01 00:00:00'),
       (null, '狄仁杰', 'direnjie', 'e10adc3949ba59abbe56e057f20f883e', '13075645123', '1', '华南职业技术学院',
        '泰山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2023-10-01 00:00:00'),
       (null, '东方求败', 'dongfangqiubai', 'e10adc3949ba59abbe56e057f20f883e', '13031245123', '1', '华南职业技术学院',
        '华山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2023-10-01 00:00:00'),
       (null, '武则天', 'wuzetian', 'e10adc3949ba59abbe56e057f20f883e', '13054345123', '1', '华南职业技术学院',
        '华山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2023-10-01 00:00:00'),
       (null, '宫本武藏', 'gongbenwuzang', 'e10adc3949ba59abbe56e057f20f883e', '13012344323', '1', '华南职业技术学院',
        '华山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2023-10-01 00:00:00'),
       (null, '李元芳', 'liyuanfang', 'e10adc3949ba59abbe56e057f20f883e', '13014321234', '1', '华南职业技术学院',
        '泰山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-01 00:00:00'),
       (null, '法外狂徒', '法外狂徒', 'e10adc3949ba59abbe56e057f20f883e', '13045675345', '0', '华南职业技术学院',
        '小黑山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-02 00:00:00'),
       (null, '上官婉儿', '上官婉儿', 'e10adc3949ba59abbe56e057f20f883e', '13064234345', '0', '华南职业技术学院',
        '小黑山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-02 00:00:00'),
       (null, '王七', 'wangqi', 'e10adc3949ba59abbe56e057f20f883e', '15555345345', '0', '华南职业技术学院',
        '小黑山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-02 00:00:00'),
       (null, '王八', 'wangba', 'e10adc3949ba59abbe56e057f20f883e', '13018888345', '0', '华南职业技术学院',
        '小黑山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-02 00:00:00'),
       (null, '王九', 'wangjiu', 'e10adc3949ba59abbe56e057f20f883e', '13012345789', '0', '华南职业技术学院',
        '小黑山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-02 00:00:00'),
       (null, '王十', 'wangshi', 'e10adc3949ba59abbe56e057f20f883e', '13015675345', '0', '华南职业技术学院',
        '小黑山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-02 00:00:00'),
       (null, '赵六', 'zhaoliu', 'e10adc3949ba59abbe56e057f20f883e', '13012345456', '1', '华南职业技术学院',
        '启林',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-03 00:00:00');


# 水库表
create table `app_reservoir`
(
    id                    bigint auto_increment comment '主键' primary key,
    `number`              varchar(15) not null comment '水库编号',
    `name`                varchar(50) not null comment '水库名称',
    `address`             varchar(50) not null comment '水库地址',
    `usable_capacity`     varchar(50) not null comment '有效库容',
    `design_capacity`     varchar(50) not null comment '设计库容',
    `max_capacity`        varchar(50) not null comment '最大库容',
    `min_capacity`        varchar(50) not null comment '最小库容',
    `dead_capacity`       varchar(50) not null comment '死库容',
    `normal_water_level`  varchar(50) not null comment '正常水位',
    `meixun_water_level`  varchar(50) not null comment '梅汛期水位',
    `typhoon_water_level` varchar(50) not null comment '台风期水位',
    `flood_water_level`   varchar(50) not null comment '洪水期水位',
    `max_water_level`     varchar(50) not null comment '最高水位',
    `dead_water_level`    varchar(50) not null comment '死水位'
);
# 插入测试静态数据
INSERT INTO `app_reservoir`
VALUES (null, 'R001', '水库一', '地址一', '1000', '1200', '1500', '800', '200', '800', '700', '850', '1000', '1200',
        '900'),
       (null, 'R002', '水库二', '地址二', '800', '1000', '1200', '600', '150', '700', '600', '750', '900', '1000',
        '700'),
       (null, 'R003', '水库三', '地址三', '1200', '1500', '1800', '1000', '300', '1100', '1000', '1150', '1400', '1500',
        '1100'),
       (null, 'R004', '水库四', '地址四', '900', '1100', '1400', '700', '180', '900', '800', '950', '1100', '1200',
        '850'),
       (null, 'R005', '水库五', '地址五', '1100', '1300', '1600', '900', '250', '1200', '1100', '1250', '1500', '1600',
        '1200'),
       (null, 'R006', '水库六', '地址六', '1300', '1600', '1900', '1100', '350', '1400', '1300', '1450', '1700', '1800',
        '1300'),
       (null, 'R007', '水库七', '地址七', '1500', '1800', '2100', '1300', '450', '1600', '1500', '1650', '1900', '2000',
        '1500'),
       (null, 'R008', '水库八', '地址八', '1200', '1400', '1700', '1000', '280', '1000', '900', '1050', '1300', '1400',
        '1000'),
       (null, 'R009', '水库九', '地址九', '1000', '1300', '1600', '800', '220', '850', '750', '900', '1100', '1200',
        '850'),
       (null, 'R010', '水库十', '地址十', '1400', '1700', '2000', '1200', '400', '1200', '1100', '1250', '1500', '1600',
        '1200');


# 消息表  管理员发送给用户的消息
drop table if exists app_message;
create table app_message
(
    id          bigint auto_increment primary key comment '主键自增id',
    level       int          not null check ( level in (1, 2, 3)) comment '消息的类型  1为通知消息 2为提示消息 3为紧急消息',
    content     varchar(300) not null comment '消息的内容',
    employee_id bigint       not null comment '接收该消息的员工id',
    checked     int          not null check ( checked in (0, 1) ) comment '是否已读，0为未读  1为已读'
);
insert into app_message
values (null, 1, '一号水库进行放水', 1, 0),
       (null, 2, '二号水库进行放水', 2, 0),
       (null, 3, '三号水库进行放水', 1, 0),
       (null, 3, '四号水库进行放水', 1, 1);


# 电站信息表
DROP TABLE IF EXISTS `app_powerstation`;
CREATE TABLE `app_powerstation`
(
    `power_station_id`                bigint        NOT NULL COMMENT '电站id',
    `power_station_name`              varchar(255)  NOT NULL COMMENT '电站名称',
    `type`                            int           NOT NULL COMMENT '电站类型(1:蓄水式 2:引水式 3: 径流式)',
    `reservoir_id`                    bigint        NOT NULL COMMENT '对应水库id',
    `unit`                            int           NOT NULL COMMENT '机组数量',
    `contain`                         varchar(255)  NOT NULL COMMENT '装机容量',
    `annual_average_runoff`           decimal(8, 2) NOT NULL COMMENT '年均径流量',
    `annual_average_power_generation` decimal(8, 2) NOT NULL COMMENT '年均发电量',
    `address`                         varchar(255)  NOT NULL COMMENT '电站地址',
    `max_reference_taffic`            decimal(8, 2) NOT NULL COMMENT '最大引用流量',
    `min_reference_taffic`            decimal(8, 2) NOT NULL COMMENT '最小引用流量',
    PRIMARY KEY (`power_station_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;
# 插入两条电站数据
INSERT INTO `app_powerstation`
VALUES (1, '电站一号', 1, 1, 3, '500', 254.80, 121.72, '地址一', 267.00, 30.00);
INSERT INTO `app_powerstation`
VALUES (2, '电站二号', 2, 2, 4, '650', 200.00, 80.23, '地址二', 244.00, 32.00);


# 员工用户申请表
DROP TABLE IF EXISTS `app_application`;
CREATE TABLE `app_application`
(
    `id`                bigint       NOT NULL COMMENT '申请消息的id(主键自增)',
    `employee_id`       bigint       NOT NULL COMMENT '申请用户的id',
    `employee_realname` varchar(255) NOT NULL COMMENT '申请用户的名字',
    `reservoir_id`      bigint       NOT NULL COMMENT '水库id，id为0的话表示不选择水库',
    `power_station_id`  bigint       NOT NULL COMMENT '电站id，id为0的话表示不选择电站',
    `type`              int          NOT NULL COMMENT '申请类型  1变压器故障  2调速器故障  3水轮机故障  4发电机组故障  0其它',
    `content`           varchar(255) NOT NULL COMMENT '申请消息内容',
    `create_time`       datetime     NOT NULL COMMENT '申请时间',
    `status`            int          NOT NULL COMMENT '申请消息的状态(0:未受理 1：受理中 2:已处理)',
    `picture`           varchar(255) COMMENT '附件，该字段为附件图片url，只能提交一张附件图片',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;
