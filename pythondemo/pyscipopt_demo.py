## https://blog.csdn.net/weixin_39059031/article/details/103241830
## https://blog.csdn.net/HsinglukLiu/article/details/120442601
## https://www.cnblogs.com/haohai9309/p/18268322
## https://www.cnblogs.com/haohai9309/p/18268322
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
