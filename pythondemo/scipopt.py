"""
https://scipopt.github.io/PySCIPOpt/docs/html/index.html

https://www.cnblogs.com/dengfaheng/p/10041488.html 

https://zimpl.zib.de/

https://github.com/scipopt/PySCIPOpt?tab=readme-ov-file

对称旅行商问题:https://blog.csdn.net/HsinglukLiu/article/details/120442601
https://www.yanxurui.cc/posts/python/2017-06-18-3-ways-of-calling-c-functions-from-python/

https://scipopt.github.io/PySCIPOpt/docs/html/classpyscipopt_1_1scip_1_1Model.html#ab546d4666e10391f1f3a9df8585e94a3
"""

from pyscipopt import Model

model = Model("Example")  # model name is optional
# vtype 变量类型：'C' 连续、'I' 整数、'B' 二进制和 'M' 隐式整数

"""
创建新变量。默认变量为非负连续变量。

:param name：变量名称，如果为空则为通用名称（默认值 = 'x1,x2,x3...'）
:param vtype：变量类型：'C' 连续、'I' 整数、'B' 二进制和 'M' 隐式整数
（请参阅 https://www.scipopt.org/doc/html/FAQ.php#implicitinteger）（默认值 = 'C'）
:param lb：变量下限，负无穷时使用 None（默认值 = 0.0）
:param ub：变量上限，正无穷时使用 None（默认值 = None）
:param obj：变量的目标值（默认值 = 0.0）
:param pricedVar：变量是否为定价候选？ （默认值 = False）
：param pricedVarScore：变量的分数（若有定价），分数越高越好（默认值 = 1.0）
"""
x = model.addVar(name="x", vtype="I")  # 声明决策变量及类型
y = model.addVar("y", vtype="INTEGER")  # 声明决策变量及类型

""" 
将目标函数建立为线性表达式。
：param coeffs：系数
：param sense：目标意义（默认值 = 'minimize'）
：param clear：将所有其他变量目标系数设置为零（默认值 = 'true'）
"""
model.setObjective(x + y)  ## 目标函数

"""
添加线性或非线性约束。
:param cons：约束对象
:param name：约束的名称，如果为空则为通用名称（默认值 = ''）
:param initial：约束的 LP 放松是否应在初始 LP 中？（默认值 = True）
:param split：在 LP 处理期间是否应分离约束？（默认值 = True）
:param force：在节点处理期间是否应强制执行约束？（默认值 = True）
:param check：是否应检查约束的可行性？（默认值 = True）
:param propagate：是否应在节点处理期间传播约束？（默认值 = True）
:param local：约束是否仅在本地有效？（默认值 = False）
:param modifiable：约束是否可修改（取决于列生成）？（默认值 = False）
:param dynamic：约束是否受老化影响？ （默认值 = False）
:param removed: 是否应由于老化或清理而将松弛从 LP 中移除？（默认值 = False）
:param stickingatnode: 是否应始终将约束保留在添加它的节点上，即使它可能被移动到更全局的节点？（默认值 = False）
:return 添加的 @ref scip#Constraint “约束”对象。
"""
model.addCons(2 * x - y * y >= 10)  ## 约束条件
model.optimize()
sol = model.getBestSol()
print("x: {}".format(sol[x]))
print("y: {}".format(sol[y]))
