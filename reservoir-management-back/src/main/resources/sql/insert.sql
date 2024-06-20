# 添加一条管理员数据（密码默认为123456，MD5加密）
insert into admin_user
values (null, 'admin', 'e10adc3949ba59abbe56e057f20f883e');




# 插入用户表数据,密码为123456加密后的数据
insert into app_user (id, realname, username, password, phone, gender,
                      work_place, home_address, icon, create_time)
values (null, '张三', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '13012345123', '1', '华南水电站分部',
        '华山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2023-10-01 00:00:00'),
       (null, '太乙真人', 'taiyizhenren', 'e10adc3949ba59abbe56e057f20f883e', '13043245123', '1', '华南水电站分部',
        '泰山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2023-10-01 00:00:00'),
       (null, '孙悟空', 'sunwukong', 'e10adc3949ba59abbe56e057f20f883e', '13075645123', '1', '华南水电站分部',
        '泰山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2023-10-01 00:00:00'),
       (null, '狄仁杰', 'direnjie', 'e10adc3949ba59abbe56e057f20f883e', '13075645123', '1', '华南水电站分部',
        '泰山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2023-10-01 00:00:00'),
       (null, '东方求败', 'dongfangqiubai', 'e10adc3949ba59abbe56e057f20f883e', '13031245123', '1', '华南水电站分部',
        '华山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2023-10-01 00:00:00'),
       (null, '武则天', 'wuzetian', 'e10adc3949ba59abbe56e057f20f883e', '13054345123', '1', '华南水电站分部',
        '华山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2023-10-01 00:00:00'),
       (null, '宫本武藏', 'gongbenwuzang', 'e10adc3949ba59abbe56e057f20f883e', '13012344323', '1', '华南水电站分部',
        '华山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2023-10-01 00:00:00'),
       (null, '李元芳', 'liyuanfang', 'e10adc3949ba59abbe56e057f20f883e', '13014321234', '1', '华南水电站分部',
        '泰山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-01 00:00:00'),
       (null, '法外狂徒', '法外狂徒', 'e10adc3949ba59abbe56e057f20f883e', '13045675345', '0', '华南水电站分部',
        '小黑山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-02 00:00:00'),
       (null, '上官婉儿', '上官婉儿', 'e10adc3949ba59abbe56e057f20f883e', '13064234345', '0', '华南水电站分部',
        '小黑山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-02 00:00:00'),
       (null, '王七', 'wangqi', 'e10adc3949ba59abbe56e057f20f883e', '15555345345', '0', '华南水电站分部',
        '小黑山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-02 00:00:00'),
       (null, '王八', 'wangba', 'e10adc3949ba59abbe56e057f20f883e', '13018888345', '0', '华南水电站分部',
        '小黑山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-02 00:00:00'),
       (null, '王九', 'wangjiu', 'e10adc3949ba59abbe56e057f20f883e', '13012345789', '0', '华南水电站分部',
        '小黑山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-02 00:00:00'),
       (null, '王十', 'wangshi', 'e10adc3949ba59abbe56e057f20f883e', '13015675345', '0', '华南水电站分部',
        '小黑山',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-02 00:00:00'),
       (null, '赵六', 'zhaoliu', 'e10adc3949ba59abbe56e057f20f883e', '13012345456', '1', '华南水电站分部',
        '启林',
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png',
        '2024-01-03 00:00:00');





# 插入水库表数据
INSERT INTO `app_reservoir`
VALUES (null, 'R001', '水库一', 1, '地址一', '1000', '1200', '1500', '800', '200', '800', '700', '850', '1000', '1200',
        '900'),
       (null, 'R002', '水库二', 2, '地址二', '800', '1000', '1200', '600', '150', '700', '600', '750', '900', '1000',
        '700'),
       (null, 'R003', '水库三', 3, '地址三', '1200', '1500', '1800', '1000', '300', '1100', '1000', '1150', '1400',
        '1500',
        '1100'),
       (null, 'R004', '水库四', 1, '地址四', '900', '1100', '1400', '700', '180', '900', '800', '950', '1100', '1200',
        '850'),
       (null, 'R005', '水库五', 2, '地址五', '1100', '1300', '1600', '900', '250', '1200', '1100', '1250', '1500',
        '1600',
        '1200'),
       (null, 'R006', '水库六', 3, '地址六', '1300', '1600', '1900', '1100', '350', '1400', '1300', '1450', '1700',
        '1800',
        '1300'),
       (null, 'R007', '水库七', 3, '地址七', '1500', '1800', '2100', '1300', '450', '1600', '1500', '1650', '1900',
        '2000',
        '1500'),
       (null, 'R008', '水库八', 1, '地址八', '1200', '1400', '1700', '1000', '280', '1000', '900', '1050', '1300',
        '1400',
        '1000'),
       (null, 'R009', '水库九', 2, '地址九', '1000', '1300', '1600', '800', '220', '850', '750', '900', '1100', '1200',
        '850'),
       (null, 'R010', '水库十', 1, '地址十', '1400', '1700', '2000', '1200', '400', '1200', '1100', '1250', '1500',
        '1600',
        '1200'),
       (null, 'R011', '水库十一', 1, '地址十一', '1420', '1800', '2000', '1200', '400', '1200', '1100', '1250', '1500',
        '1600',
        '1200'),
       (null, 'R011', '水库十二', 1, '地址十二', '1420', '1800', '2000', '1200', '400', '1200', '1100', '1250', '1500',
        '1600',
        '1200'),
       (null, 'R011', '水库十三', 1, '地址十三', '1420', '1800', '2000', '1200', '400', '1200', '1100', '1250', '1500',
        '1600',
        '1200'),
       (null, 'R011', '水库十四', 1, '地址十四', '1420', '1800', '2000', '1200', '400', '1200', '1100', '1250', '1500',
        '1600',
        '1200');





# 消息表  管理员发送给用户的消息
INSERT INTO `app_message`
VALUES (1, 1, '放水计划', '一号水库进行放水', 1, 0, 0, '2023-12-02 00:00:00');
INSERT INTO `app_message`
VALUES (2, 2, '放水计划', '二号水库进行放水', 2, 0, 0, '2023-12-02 00:00:00');
INSERT INTO `app_message`
VALUES (3, 3, '放水计划', '三号水库进行放水', 1, 0, 0, '2023-12-02 00:00:00');
INSERT INTO `app_message`
VALUES (4, 3, '放水计划', '四号水库进行放水', 1, 1, 0, '2023-12-02 00:00:00');




# 插入电站数据
INSERT INTO `app_powerstation`
VALUES (1, 'S001', '电站一号', 1, true, 1, 3, '500', 254.80, 121.72, '地址一', 267.00, 30.00),
       (2, 'P001', '电站二号', 2, true, 2, 4, '650', 200.00, 80.23, '地址二', 244.00, 32.00),
       (3, 'S002', '电站十一号', 1, true, 1, 3, '500', 254.80, 121.72, '地址三', 267.00, 30.00),
       (4, 'P002', '电站二二号', 3, true, 2, 4, '650', 200.00, 80.23, '地址二', 244.00, 32.00),
       (5, 'P003', '电站三二号', 2, false, 1, 4, '650', 200.00, 80.23, '地址二', 244.00, 32.00),
       (6, 'P004', '电站二十号', 3, false, 2, 4, '650', 200.00, 80.23, '地址三', 244.00, 32.00),
       (7, 'P005', '电站二六号', 2, false, 2, 4, '650', 200.00, 80.23, '地址二', 244.00, 32.00);





# 员工申请表数据插入
INSERT INTO app_application (id, employee_id, employee_realname, reservoir_id, power_station_id, type, content,
                             create_time,
                             status, picture)
values (null, 1, '张三', 1, 1, 1, '变压器故障', '2023-12-02 00:00:00', 0,
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-22074cb8-d038-09f2-c09d-65ad33a998ed.jpg'),
       (null, 2, '太乙真人', 1, 1, 1, '变压器故障', '2023-12-02 00:00:00', 0,
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-22074cb8-d038-09f2-c09d-65ad33a998ed.jpg'),
       (null, 3, '孙悟空', 1, 1, 2, '调速器故障', '2023-12-02 00:00:00', 0,
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-22074cb8-d038-09f2-c09d-65ad33a998ed.jpg'),
       (null, 4, '狄仁杰', 1, 1, 2, '调速器故障', '2023-12-02 00:00:00', 1,
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-22074cb8-d038-09f2-c09d-65ad33a998ed.jpg'),
       (null, 5, '东方求败', 1, 1, 3, '水轮机故障', '2023-12-02 00:00:00', 1,
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-22074cb8-d038-09f2-c09d-65ad33a998ed.jpg'),
       (null, 6, '武则天', 1, 1, 3, '水轮机故障', '2023-12-02 00:00:00', 1,
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-22074cb8-d038-09f2-c09d-65ad33a998ed.jpg'),
       (null, 7, '宫本武藏', 1, 1, 4, '发电机组故障', '2023-12-02 00:00:00', 0,
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-22074cb8-d038-09f2-c09d-65ad33a998ed.jpg'),
       (null, 8, '李元芳', 1, 1, 4, '发电机组故障', '2024-01-02 00:00:00', 0,
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-22074cb8-d038-09f2-c09d-65ad33a998ed.jpg'),
       (null, 9, '法外狂徒', 1, 1, 4, '发电机组故障', '2024-01-02 00:00:00', 0,
        'https://tanhao-bu0cket.oss-cn-guangzhou.aliyuncs.com/reservoir-22074cb8-d038-09f2-c09d-65ad33a998ed.jpg'),
       (null, 10, '上官婉儿', 1, 1, 4, '发电机组故障', '2024-01-02 00:00:00', 0,
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-22074cb8-d038-09f2-c09d-65ad33a998ed.jpg'),
       (null, 11, '王七', 1, 1, 4, '发电机组故障', '2024-01-02 00:00:00', 2,
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-22074cb8-d038-09f2-c09d-65ad33a998ed.jpg'),
       (null, 12, '王八', 1, 1, 4, '发电机组故障', '2024-01-02 00:00:00', 2,
        'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-22074cb8-d038-09f2-c09d-65ad33a998ed.jpg');




# 水文数据插入
insert into hydrology
values (null, 101, 501, 50, 2, 0, 2, 1, 100, 2);




# 月降水数据插入
INSERT INTO `monthly_precipitation`
VALUES (null, '2020', '1', 20.9);
INSERT INTO `monthly_precipitation`
VALUES (null, '2020', '2', 69.4);
INSERT INTO `monthly_precipitation`
VALUES (null, '2020', '3', 84.7);
INSERT INTO `monthly_precipitation`
VALUES (null, '2020', '4', 201.8);
INSERT INTO `monthly_precipitation`
VALUES (null, '2020', '5', 283.4);
INSERT INTO `monthly_precipitation`
VALUES (null, '2020', '6', 234.2);
INSERT INTO `monthly_precipitation`
VALUES (null, '2020', '7', 331.1);
INSERT INTO `monthly_precipitation`
VALUES (null, '2020', '8', 234.1);
INSERT INTO `monthly_precipitation`
VALUES (null, '2020', '9', 166.4);
INSERT INTO `monthly_precipitation`
VALUES (null, '2020', '10', 80.4);
INSERT INTO `monthly_precipitation`
VALUES (null, '2020', '11', 23.4);
INSERT INTO `monthly_precipitation`
VALUES (null, '2020', '12', 33.5);
INSERT INTO `monthly_precipitation`
VALUES (null, '2021', '1', 19.4);
INSERT INTO `monthly_precipitation`
VALUES (null, '2021', '2', 30.8);
INSERT INTO `monthly_precipitation`
VALUES (null, '2021', '3', 54.9);
INSERT INTO `monthly_precipitation`
VALUES (null, '2021', '4', 40.9);
INSERT INTO `monthly_precipitation`
VALUES (null, '2021', '5', 80.8);
INSERT INTO `monthly_precipitation`
VALUES (null, '2021', '6', 145.8);
INSERT INTO `monthly_precipitation`
VALUES (null, '2021', '7', 189.8);
INSERT INTO `monthly_precipitation`
VALUES (null, '2021', '8', 215.7);
INSERT INTO `monthly_precipitation`
VALUES (null, '2021', '9', 245.1);
INSERT INTO `monthly_precipitation`
VALUES (null, '2021', '10', 123.4);
INSERT INTO `monthly_precipitation`
VALUES (null, '2021', '11', 80.9);
INSERT INTO `monthly_precipitation`
VALUES (null, '2021', '12', 35.9);
INSERT INTO `monthly_precipitation`
VALUES (null, '2022', '1', 23.1);
INSERT INTO `monthly_precipitation`
VALUES (null, '2022', '2', 13.8);
INSERT INTO `monthly_precipitation`
VALUES (null, '2022', '3', 48.7);
INSERT INTO `monthly_precipitation`
VALUES (null, '2022', '4', 80.3);
INSERT INTO `monthly_precipitation`
VALUES (null, '2022', '5', 120.7);
INSERT INTO `monthly_precipitation`
VALUES (null, '2022', '6', 157.2);
INSERT INTO `monthly_precipitation`
VALUES (null, '2022', '7', 222.4);
INSERT INTO `monthly_precipitation`
VALUES (null, '2022', '8', 289.3);
INSERT INTO `monthly_precipitation`
VALUES (null, '2022', '9', 199.1);
INSERT INTO `monthly_precipitation`
VALUES (null, '2022', '10', 60.2);
INSERT INTO `monthly_precipitation`
VALUES (null, '2022', '11', 40.1);
INSERT INTO `monthly_precipitation`
VALUES (null, '2022', '12', 15.5);
INSERT INTO `monthly_precipitation`
VALUES (null, '2023', '1', 40.8);
INSERT INTO `monthly_precipitation`
VALUES (null, '2023', '2', 23.8);
INSERT INTO `monthly_precipitation`
VALUES (null, '2023', '3', 33.9);
INSERT INTO `monthly_precipitation`
VALUES (null, '2023', '4', 87.2);
INSERT INTO `monthly_precipitation`
VALUES (null, '2023', '5', 116.8);
INSERT INTO `monthly_precipitation`
VALUES (null, '2023', '6', 203.4);
INSERT INTO `monthly_precipitation`
VALUES (null, '2023', '7', 160.8);
INSERT INTO `monthly_precipitation`
VALUES (null, '2023', '8', 234.9);
INSERT INTO `monthly_precipitation`
VALUES (null, '2023', '9', 123.4);
INSERT INTO `monthly_precipitation`
VALUES (null, '2023', '10', 78.5);
INSERT INTO `monthly_precipitation`
VALUES (null, '2023', '11', 66.5);
INSERT INTO `monthly_precipitation`
VALUES (null, '2023', '12', 23.7);
INSERT INTO `monthly_precipitation`
VALUES (null, '2024 ', '1', 46.8);
INSERT INTO `monthly_precipitation`
VALUES (null, '2024 ', '2', 92.4);

# 备忘录插入
INSERT INTO `record` (`content`, `create_time`) VALUES ('整理本周工作计划', '2024-06-19 09:30:00');
INSERT INTO `record` (`content`, `create_time`) VALUES ('和项目经理开会讨论进度', '2024-06-20 11:00:00');
INSERT INTO `record` (`content`, `create_time`) VALUES ('编写本月工作总结', '2024-06-22 17:00:00');
INSERT INTO `record` (`content`, `create_time`) VALUES ('和团队成员评估下半年目标', '2024-06-23 14:30:00');
INSERT INTO `record` (`content`, `create_time`) VALUES ('准备下周例会汇报事项', '2024-06-24 10:45:00');


