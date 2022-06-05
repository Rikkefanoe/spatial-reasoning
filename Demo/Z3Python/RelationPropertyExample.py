from z3 import *

a, b, c = Reals('a b c')

equal_ab = a == b
equal_bc = b == c
equal_ac = a == c

s = Solver()
s.add(And(equal_ab, equal_bc, Not(equal_ac)))

res = s.check()
print(f"Transitive (==): {res}")
if (res.__repr__() == "sat"):
    print(f"model: {s.model()}")