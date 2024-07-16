import sys
from MyProblem import MyProblem  # 导入自定义问题接口
import geatpy as ea  # import geatpy

def main():
    # 从命令行参数获取参数列表
    parameter1 = list(map(float, sys.argv[1].strip('[]').split(',')))
    parameter2 = list(map(float, sys.argv[2].strip('[]').split(',')))
    parameter3 = list(map(float, sys.argv[3].strip('[]').split(',')))
    particle_count = int(sys.argv[4])
    iteration_count = int(sys.argv[5])

    # 实例化问题对象
    problem = MyProblem(monthly_water_demand=parameter1,
                        monthly_inflow=parameter2,
                        reservoir_params=parameter3)

    algorithm = ea.moea_MOEAD_templet(
        problem,
        ea.Population(Encoding='RI', NIND=particle_count),  # NIND为所需要的个体数
        MAXGEN=iteration_count,  # 最大进化代数
        logTras=0  # 表示每隔多少代记录一次日志信息，0表示不记录
    )

    # 求解
    res = ea.optimize(algorithm,
                      verbose=False,
                      drawing=0,
                      outputMsg=False,
                      drawLog=False,
                      saveFlag=True,
                      dirName='result')
    print('MOEA/D算法调用成功=========')

if __name__ == '__main__':
    main()
