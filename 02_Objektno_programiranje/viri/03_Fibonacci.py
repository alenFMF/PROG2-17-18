def fibR(n):
    if n == 0: return 0
    if n == 1: return 1
    return fibR(n-1) + fibR(n-2)

def fibI(n):
    a, b = 0, 1
    for i in range(n):
        a, b = b, a + b
    return a

def fibG():
    a, b = 0, 1
    while True:
        yield a
        a, b = b, a + b
