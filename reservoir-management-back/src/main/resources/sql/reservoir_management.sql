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

# 水库表
drop table if exists `app_reservoir`;
create table `app_reservoir`
(
    id                    bigint auto_increment comment '主键' primary key,
    `number`              varchar(15) not null comment '水库编号',
    `name`                varchar(50) not null comment '水库名称',
    `type`                int         not null comment '水库类型 1为引水 2为径流 3为蓄水 0为其它',
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

# 消息表  管理员发送给用户的消息
DROP TABLE IF EXISTS `app_message`;
CREATE TABLE `app_message`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
    `level`       int                                                           NOT NULL COMMENT '消息的类型  1为通知消息 2为提示消息 3为紧急消息',
    `title`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息的标题',
    `content`     varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息的内容',
    `employee_id` bigint                                                        NOT NULL COMMENT '接收该消息的员工id',
    `checked`     int                                                           NOT NULL COMMENT '是否已读，0为未读  1为已读',
    `finished`    int                                                           NOT NULL COMMENT '是否已完成  0未完成  1已完成',
    `create_time` datetime                                                      NOT NULL COMMENT '消息创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    CONSTRAINT `app_message_chk_1` CHECK (`level` in (1, 2, 3)),
    CONSTRAINT `app_message_chk_2` CHECK (`checked` in (0, 1)),
    CONSTRAINT `app_message_chk_3` CHECK (`finished` in (0, 1))
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

# 电站表
DROP TABLE IF EXISTS `app_powerstation`;
CREATE TABLE `app_powerstation`
(
    `power_station_id`                bigint        NOT NULL COMMENT '电站id',
    `power_station_number`            varchar(255)  NOT NULL COMMENT '电站编号',
    `power_station_name`              varchar(255)  NOT NULL COMMENT '电站名称',
    `type`                            int           NOT NULL COMMENT '电站类型(1:蓄水式 2:引水式 3: 径流式)',
    `is_stair`                        boolean       NOT NULL COMMENT '是否梯级',
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


# 员工用户申请表
DROP TABLE IF EXISTS `app_application`;
CREATE TABLE `app_application`
(
    `id`                bigint primary key auto_increment COMMENT '申请消息的id(主键自增)',
    `employee_id`       bigint       NOT NULL COMMENT '申请用户的id',
    `employee_realname` varchar(255) NOT NULL COMMENT '申请用户的名字',
    `reservoir_id`      bigint       NOT NULL COMMENT '水库id，id为0的话表示不选择水库',
    `power_station_id`  bigint       NOT NULL COMMENT '电站id，id为0的话表示不选择电站',
    `type`              int          NOT NULL COMMENT '申请类型  1变压器故障  2调速器故障  3水轮机故障  4发电机组故障  0其它',
    `content`           varchar(255) NOT NULL COMMENT '申请消息内容',
    `create_time`       datetime     NOT NULL COMMENT '申请时间',
    `status`            int          NOT NULL COMMENT '申请消息的状态(0:未受理 1：受理中 2:已处理)',
    `picture`           varchar(255) COMMENT '附件，该字段为附件图片url，只能提交一张附件图片'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

# 水文数据表
drop table if exists `hydrology`;
create table `hydrology`
(
    id                     int primary key auto_increment comment '主键id',
    water_level            int comment '水位',
    runoff_volume          int comment '净流量大小',
    sediment_concentration int comment '含沙量',
    flood_season           int comment '汛期 1 2 3 4代表春夏秋冬',
    presence_of_ice_cover  int comment '有无结冰期 0为无 1为有',
    flow_velocity          int comment '流速',
    river_network_shape    int comment '河流水系形状 0为未知 1为树枝状水系，2为向心状水系，3为平行状水系，4为网状水系',
    hydropower_potential   int comment '水能资源蕴藏量',
    river_navigation_value int comment '河流航运价值 0为低 1位中 2为高'
);

# 电站运行数据表（该部分数据是后端模拟直接返回无需通过数据库）
drop table if exists `powerstation_data`;
create table `powerstation_data`
(
    id                int  not null auto_increment primary key,
    date              date not null comment '电站某日的运行数据',
    power_generation  int comment '发电量(kWh)',
    water_level       float comment '平均水位(m)',
    inflow_flow_rate  float comment '进流流量(m^3/s)',
    outflow_flow_rate float comment '出流流量(m^3/s)',
    pressure          float comment '压力(Pa)',
    speed             float comment '转速(rpm)',
    voltage           float comment '电网电压(V)',
    frequency         float comment '电网频率(Hz)',
    grid_load         float comment '电网负荷(kW)'
);


# 气温表
DROP TABLE IF EXISTS `daily_weather`;
CREATE TABLE `daily_weather`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT COMMENT 'id主键',
    `date`          date         NULL DEFAULT NULL COMMENT '日期',
    `temp_min`      int          NULL DEFAULT NULL COMMENT '当日最低温',
    `temp_max`      int          NULL DEFAULT NULL COMMENT '当日最高温',
    `precipitation` double(6, 1) NULL DEFAULT NULL COMMENT '当日降水(单位:mm)',
    PRIMARY KEY (`id`) USING BTREE
);

# 月降水
DROP TABLE IF EXISTS `monthly_precipitation`;
CREATE TABLE `monthly_precipitation`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `year`          int          NULL DEFAULT NULL COMMENT '年份',
    `month`         int          NULL DEFAULT NULL COMMENT '月份',
    `precipitation` double(6, 1) NULL DEFAULT NULL COMMENT '月总降水',
    PRIMARY KEY (`id`) USING BTREE
);

# 备忘录表
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`
(
    `id`      int PRIMARY KEY AUTO_INCREMENT COMMENT '主键id',
    `content` varchar(50) NOT NULL COMMENT '内容',
    `create_time`    varchar(50)    NOT NULL COMMENT '创建时间'
);

# 文件URL表
DROP TABLE IF EXISTS `file_url`;
CREATE TABLE `file_url`
(
    `id`      int PRIMARY KEY AUTO_INCREMENT COMMENT '主键id',
    `file_name`    varchar(50) NOT NULL NULL COMMENT '名称(以时间戳命名)',
    `file_url` varchar(200) NOT NULL COMMENT '文件url'
)