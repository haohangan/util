import pyscipopt

## 一组决策变量
## 一个目标函数
## 一组决策变量的约束条件

model = pyscipopt.Model("model name")

## 定义一组变量
x1 = model.addVar(vtype="I")
x2 = model.addVar(vtype="I")
x3 = model.addVar(vtype="I")
x4 = model.addVar(vtype="I")
x5 = model.addVar(vtype="I")

## 设定目标函数 minimize
model.setObjective(-1 * x1)

## 设定决策变量的约束条件
model.addCons(2 * x1 + x2 + x3 == 12)
# model.addCons(x1 + 2 * x2 + x4 == 9)

## 求解
model.optimize()
sol = model.getBestSol()


print(f"obj val {model.getObjVal()}")
print(sol)
