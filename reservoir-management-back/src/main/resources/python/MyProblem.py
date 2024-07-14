import numpy as np
import pandas as pd
import geatpy as ea


class MyProblem(ea.Problem):  # 继承Problem父类

    def __init__(self, M=2, monthly_water_demand=None, monthly_inflow=None, reservoir_params=None):  # M为目标维数
        # 读取 Excel 文件
        # file_path = r'D:\OneDrive\桌面\大创\资料\输入.xlsx'

        # 读取每月需水量信息，并转换为整数
        # self.monthly_water_demand = df.iloc[2, 1:13].astype(int).tolist()
        #
        # 读取每月水库平均入库流量信息，并转换为整数
        # self.monthly_inflow = df.iloc[7, 1:13].astype(int).tolist()
        #
        # 读取水库参数信息
        # self.reservoir_params = df.iloc[12, 0:10].tolist()
        # df = pd.read_excel(file_path, header=None)

        self.monthly_water_demand = monthly_water_demand
        self.monthly_inflow = monthly_inflow
        self.reservoir_params = reservoir_params

        print(self.monthly_water_demand)
        print(self.monthly_inflow)
        print(self.reservoir_params)

        name = 'MyProblem'  # 初始化name（函数名称，可以随意设置）

        # 决策变量为每个月的平均出库流量，共有12个月的平均出库流量 即12个决策变量
        Dim = 12  # 初始化Dim（决策变量维数，即有多少个决策变量。）

        # [1] * M 表示M个优化目标都是最小化该目标
        maxormins = [1, -1]  # 初始化maxormins（目标最小最大化标记列表，1：最小化该目标；-1：最大化该目标）
        varTypes = [0] * Dim  # 初始化varTypes（决策变量的类型，0：实数；1：整数）
        lb = [self.reservoir_params[5]] * Dim  # 决策变量下界
        ub = [self.reservoir_params[4]] * Dim  # 决策变量上界
        lbin = [1] * Dim  # 决策变量下边界（0表示不包含该变量的下边界，1表示包含）
        ubin = [1] * Dim  # 决策变量上边界（0表示不包含该变量的上边界，1表示包含）

        # 调用父类构造方法完成实例化
        ea.Problem.__init__(self,
                            name,
                            M,
                            maxormins,
                            Dim,
                            varTypes,
                            lb,
                            ub,
                            lbin,
                            ubin)

    def evalVars(self, Vars):  # 目标函数
        x1 = Vars[:, [0]]
        x2 = Vars[:, [1]]
        x3 = Vars[:, [2]]
        x4 = Vars[:, [3]]
        x5 = Vars[:, [4]]
        x6 = Vars[:, [5]]
        x7 = Vars[:, [6]]
        x8 = Vars[:, [7]]
        x9 = Vars[:, [8]]
        x10 = Vars[:, [9]]
        x11 = Vars[:, [10]]
        x12 = Vars[:, [11]]

        # 一个月的秒数
        # seconds = 2592000
        seconds = 2592000

        # 计算每个月的缺水量
        def calculate_deficit(x, demand):
            return np.maximum(0, demand - x)

        # 参数为每个月的平均出库流量xi，和总需水量
        deficit1 = calculate_deficit(x1, self.monthly_water_demand[0])
        deficit2 = calculate_deficit(x2, self.monthly_water_demand[1])
        deficit3 = calculate_deficit(x3, self.monthly_water_demand[2])
        deficit4 = calculate_deficit(x4, self.monthly_water_demand[3])
        deficit5 = calculate_deficit(x5, self.monthly_water_demand[4])
        deficit6 = calculate_deficit(x6, self.monthly_water_demand[5])
        deficit7 = calculate_deficit(x7, self.monthly_water_demand[6])
        deficit8 = calculate_deficit(x8, self.monthly_water_demand[7])
        deficit9 = calculate_deficit(x9, self.monthly_water_demand[8])
        deficit10 = calculate_deficit(x10, self.monthly_water_demand[9])
        deficit11 = calculate_deficit(x11, self.monthly_water_demand[10])
        deficit12 = calculate_deficit(x12, self.monthly_water_demand[11])

        # 计算总缺水量
        f1 = (deficit1 + deficit2 + deficit3 + deficit4 + deficit5 +
              deficit6 + deficit7 + deficit8 + deficit9 + deficit10 +
              deficit11 + deficit12) * seconds

        # 总发电量计算 计算得到的是以J为单位的发电量 后面要换算单位
        def calculate_power(x, inflow, h0):
            qa = (x + inflow) / 2
            # ha可能会算出来是负数，因此需要限制一下
            ha = h0 + ((inflow - x) * seconds) / (2 * self.reservoir_params[3])
            return self.reservoir_params[0] * 1000 * 10 * qa * ha * seconds

        area = self.reservoir_params[3] * 10 ** 6
        # 参数为（每个月的出库流量，入库流量，起调水位）
        h1 = np.full((len(x1), 1), self.reservoir_params[8])
        power1 = calculate_power(x1, self.monthly_inflow[0], h1)
        h2 = h1 + ((self.monthly_inflow[0] - x1) * seconds) / area
        power2 = calculate_power(x2, self.monthly_inflow[1], h2)
        h3 = h2 + ((self.monthly_inflow[1] - x2) * seconds) / area
        power3 = calculate_power(x3, self.monthly_inflow[2], h3)
        h4 = h3 + ((self.monthly_inflow[2] - x3) * seconds) / area
        power4 = calculate_power(x4, self.monthly_inflow[3], h4)
        h5 = h4 + ((self.monthly_inflow[3] - x4) * seconds) / area
        power5 = calculate_power(x5, self.monthly_inflow[4], h5)
        h6 = h5 + ((self.monthly_inflow[4] - x5) * seconds) / area
        power6 = calculate_power(x6, self.monthly_inflow[5], h6)
        h7 = h6 + ((self.monthly_inflow[5] - x6) * seconds) / area
        power7 = calculate_power(x7, self.monthly_inflow[6], h7)
        h8 = h7 + ((self.monthly_inflow[6] - x7) * seconds) / area
        power8 = calculate_power(x8, self.monthly_inflow[7], h8)
        h9 = h8 + ((self.monthly_inflow[7] - x8) * seconds) / area
        power9 = calculate_power(x9, self.monthly_inflow[8], h9)
        h10 = h9 + ((self.monthly_inflow[8] - x9) * seconds) / area
        power10 = calculate_power(x10, self.monthly_inflow[9], h10)
        h11 = h10 + ((self.monthly_inflow[9] - x10) * seconds) / area
        power11 = calculate_power(x11, self.monthly_inflow[10], h11)
        h12 = h11 + ((self.monthly_inflow[10] - x11) * seconds) / area
        power12 = calculate_power(x12, self.monthly_inflow[11], h12)

        # 总发电量 (J转换成kWh)
        f2 = (power1 + power2 + power3 + power4 + power5 + power6 + power7 + power8 +
              power9 + power10 + power11 + power12) / 3.6e6

        # 利用可行性法则处理约束条件
        CV = np.hstack([
            # 水头下限
            self.reservoir_params[2] - h1,
            self.reservoir_params[2] - h2,
            self.reservoir_params[2] - h3,
            self.reservoir_params[2] - h4,
            self.reservoir_params[2] - h5,
            self.reservoir_params[2] - h6,
            self.reservoir_params[2] - h7,
            self.reservoir_params[2] - h8,
            self.reservoir_params[2] - h9,
            self.reservoir_params[2] - h10,
            self.reservoir_params[2] - h11,
            self.reservoir_params[2] - h12,
            # self.reservoir_params[2] - np.hstack([h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12]),

            # 水头上限
            h1 - self.reservoir_params[1],
            h2 - self.reservoir_params[1],
            h3 - self.reservoir_params[1],
            h4 - self.reservoir_params[1],
            h5 - self.reservoir_params[1],
            h6 - self.reservoir_params[1],
            h7 - self.reservoir_params[1],
            h8 - self.reservoir_params[1],
            h9 - self.reservoir_params[1],
            h10 - self.reservoir_params[1],
            h11 - self.reservoir_params[1],
            h12 - self.reservoir_params[1],
            # np.hstack([h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12]) - self.reservoir_params[1],

            # 发电功率下限
            self.reservoir_params[7] - power1 / 3.6e6,
            self.reservoir_params[7] - power2 / 3.6e6,
            self.reservoir_params[7] - power3 / 3.6e6,
            self.reservoir_params[7] - power4 / 3.6e6,
            self.reservoir_params[7] - power5 / 3.6e6,
            self.reservoir_params[7] - power6 / 3.6e6,
            self.reservoir_params[7] - power7 / 3.6e6,
            self.reservoir_params[7] - power8 / 3.6e6,
            self.reservoir_params[7] - power9 / 3.6e6,
            self.reservoir_params[7] - power10 / 3.6e6,
            self.reservoir_params[7] - power11 / 3.6e6,
            self.reservoir_params[7] - power12 / 3.6e6,
            # self.reservoir_params[7] - np.hstack(
            #     [power1, power2, power3, power4, power5, power6, power7, power8, power9, power10, power11,
            #      power12]) / 3.6e6,

            # 发电功率上限
            power1 / 3.6e6 - self.reservoir_params[6],
            power2 / 3.6e6 - self.reservoir_params[6],
            power3 / 3.6e6 - self.reservoir_params[6],
            power4 / 3.6e6 - self.reservoir_params[6],
            power5 / 3.6e6 - self.reservoir_params[6],
            power6 / 3.6e6 - self.reservoir_params[6],
            power7 / 3.6e6 - self.reservoir_params[6],
            power8 / 3.6e6 - self.reservoir_params[6],
            power9 / 3.6e6 - self.reservoir_params[6],
            power10 / 3.6e6 - self.reservoir_params[6],
            power11 / 3.6e6 - self.reservoir_params[6],
            power12 / 3.6e6 - self.reservoir_params[6],
            # np.hstack([power1, power2, power3, power4, power5, power6, power7, power8, power9, power10, power11,
            #            power12]) / 3.6e6 - self.reservoir_params[6],
        ])
        # 目标函数矩阵
        ObjV = np.hstack([f1, f2])
        return ObjV, CV