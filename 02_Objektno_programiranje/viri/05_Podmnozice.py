def podmnozice(A):
    n = len(A); h = 2**n; k = 0
    while True:
        s = bin(h+k)[3:]
        yield({A[i] for i in range(n) if s[i]=='1'})
        k = k+1
        if k==h: raise StopIteration

def karakteristicniVektorji(n):
    h = 2**n; k = 0
    while True:
        yield([int(z) for z in bin(h+k)[3:]])
        k = k+1
        if k==h: raise StopIteration
