def hanoi(a, b, c, n):
    if n > 0:
        hanoi(a, c, b, n - 1)
        print("{0} -> {1}".format(a, c))
        hanoi(b, a, c, n - 1)
        
def hanoiG(a, b, c, n):
    if n > 0:
        for i in hanoiG(a, c, b, n - 1):
            yield i
        yield "{0} -> {1}".format(a, c)
        for i in hanoiG(b, a, c, n - 1):
            yield i



