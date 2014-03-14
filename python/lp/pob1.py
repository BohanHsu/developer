from pulp import *

f1 = LpVariable("f1", 0)
f2 = LpVariable("f2", 0)
f3 = LpVariable("f3", 0)

prob = LpProblem("pob1", LpMinimize)

# 0.1*f1 + 0.2*f2 + 0.15*f3 
# 0.1*f1 + 0.05*f2 + 0.15*f3
# 0.1*f1 + 0.15*f2 + 0.05*f3
# 0.05*f1 + 0.1*f2 + 0.05*f3

prob += -0.1*f1 + -0.2*f2 + -0.15*f3 <= -160
prob += -0.1*f1 + -0.05*f2 + -0.15*f3 <= -40
prob += -0.1*f1 + -0.15*f2 + -0.05*f3 <= -80
prob += -0.05*f1 + -0.1*f2 + -0.05*f3 <= -20

prob += 0.1*f1 + 0.05*f2 + 0.15*f3 <= 100
prob += 0.1*f1 + 0.15*f2 + 0.05*f3 <= 120
prob += 0.05*f1 + 0.1*f2 + 0.05*f3 <= 50

prob += 100*f1 + 150*f2 + 50*f3

status = prob.solve(GLPK(msg = 0))

print value(prob.objective)
print value(f1)
print value(f2)
print value(f3)
 
