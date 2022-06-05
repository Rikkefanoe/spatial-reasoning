from z3 import *
import time

# -------------Simple example --------------------
# x, a, b = Reals('x a b')
# t = Tactic('qe')
# q = Exists(x, And(a < x, x < b))
# t_q = t(q) # Applies quantifier elimination tactic to q
# print(t_q.as_expr())
#-------------------------------------------------


# -------- Intersects relation -------------------
# px, py, asx, aex, asy, aey = Reals('px py asx aex asy aey')
# bsx, bsy, bex, bey = Reals('bsx bsy bex bey')
# colinear_a = (aey - asy) * (px - asx) - (py - asy) * (aex - asx) == 0
# colinear_b = (bey - bsy) * (px - bsx) - (py - bsy) * (bex - bsx) == 0
# inbox_a = And(asx <= px, px <= aex, asy <= py, py <= aey)
# inbox_b = And(bsx <= px, px <= bex, bsy <= py, py <= bey)

# t = Tactic('qe')

# q = Exists((px, py), And(inbox_a, inbox_b))
# t_q = t(q) # Applies quantifier elimination tactic to q
# print(t_q.as_expr())
#-------------------------------------------------

# ---- Measuring execution time of solver --------
s = Solver()
s.add(q)
for i in range(2):
    startTime = time.time()
    for j in range(1000):
        s.check()
    endTime = time.time() - startTime
    print(f"Time: {endTime:.2f} seconds")
    s.reset()
    s.add(t_q.as_expr())
# ------------------------------------------------