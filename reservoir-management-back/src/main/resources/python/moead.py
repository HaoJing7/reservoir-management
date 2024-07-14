from MyProblem import MyProblem  # 导入自定义问题接口

import geatpy as ea  # import geatpy

if __name__ == '__main__':
    # 参数列表
    parameter1 = [50, 50, 50, 60, 60, 100, 100, 100, 60, 60, 50, 50]
    parameter2 = [20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20]
    parameter3 = [0.7, 100, 10, 10000, 80, 10, 100000000, 50, 15, 20]
    parameter4 = [100, 300]

    # 实例化问题对象
    problem = MyProblem(monthly_water_demand=parameter1,
                        monthly_inflow=parameter2,
                        reservoir_params=parameter3)

    algorithm = ea.moea_MOEAD_templet(
        problem,
        # 构建算法
        ea.Population(Encoding='RI', NIND=parameter4[0]),  # NIND为所需要的个体数。
        MAXGEN=parameter4[1],  # 最大进化代数
        logTras=0)  # 表示每隔多少代记录一次日志信息，0表示不记录。

    # 求解
    res = ea.optimize(algorithm,
                      verbose=False,
                      drawing=0,
                      outputMsg=False,
                      drawLog=False,
                      saveFlag=True)
    print(res)
