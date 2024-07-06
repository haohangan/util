## https://blog.csdn.net/weixin_39059031/article/details/103241830
## https://blog.csdn.net/HsinglukLiu/article/details/120442601
## https://www.cnblogs.com/haohai9309/p/18268322
## https://zh.wikipedia.org/zh-cn/%E7%BA%BF%E6%80%A7%E8%A7%84%E5%88%92
## https://blog.csdn.net/qq_55851911/article/details/124516895     对比多种算法库
## https://blog.csdn.net/weixin_44758127/article/details/113062186
## https://blog.csdn.net/hyhywang/article/details/133039363    工厂生产机器的问题
## https://www.cvxpy.org/tutorial/advanced/index.html cvxpy
## https://developers.google.com/optimization/introduction    OR-Tools不支持二次规划
## https://www.gnu.org/software/glpk/#TOCdownloading  大规模线性规划LP和混合型整数规划MIP问题
## https://blog.zhangzhk.com/2018/10/27/summary-linear-programming-solver/ 性能问题
## https://blog.csdn.net/kittyzc/article/details/82789439 对比

## https://cloud.tencent.com/developer/article/1944806 商用对比
## https://cn.linkedin.com/pulse/aps%E5%AE%9E%E7%8E%B0%E7%9A%84%E8%A6%81%E7%82%B9%E4%B8%8E%E9%9A%BE%E7%82%B9-%E5%81%A5%E5%BD%AA-%E5%BC%A0 现实意义

## 理论介绍 https://xoyolucas.github.io/2020/03/20/%E7%BA%BF%E6%80%A7%E8%A7%84%E5%88%92/
## https://juejin.cn/post/7315458151415021605  带着问题学习
import pyscipopt

## 一组决策变量
## 一个目标函数
## 一组决策变量的约束条件

model = pyscipopt.Model("model name")

## 定义一组变量
x1 = model.addVar()
x2 = model.addVar()
x3 = model.addVar()
x4 = model.addVar()
x5 = model.addVar()

## 设定目标函数
model.setObjective(-5 * x1 - 2 * x2 - 3 * x3 + x4 - x5)

## 设定决策变量的约束条件
model.addCons(x1 + 2 * x2 + 2 * x3 + x4 == 8)
model.addCons(3 * x1 + 4 * x2 + x3 + x5 == 7)

## 求解
model.optimize()
sol = model.getBestSol()

print(sol)
print(f"x1: {sol[x1]}")
