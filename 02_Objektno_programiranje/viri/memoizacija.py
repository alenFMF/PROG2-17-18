# Memoizacija, cool varianta



# Pri uporabi rekurzivnih funkcij, ki se klicejo precej v globino, se uprablja
# sklad klicev, ki je po velikosti omejen.
# To vidimo v tem primeru:
def sm(n):
    if n == 1: return 1
    a = sm(n - 1)
    return a + n
#klic:
# >>> sm(1000)

# Povečamo ga npr. takole
# >>> import sys
# >>> sys.setrecursionlimit(2000)

# V nekih primerih pri interpreterjih in prevajalnikih so rekurzivne funkcije,
# pri katerih je edini rekurzivni klic zadnje kar naredimo v funkciji,
# optimizirane tako, da se sklad sproti čisti in je zato število rekurzivnih
# klicev lahko neomejeno. Python zaradi posebnih razlogov nima te optimizacije,
# torej t.i. "repne rekurzije" ("tail recursion").
def sm2(n):
    if n == 1: return 1
    return n + sm2(n - 1)


from functools import wraps

def memo(func):
    cache = {}
    @wraps(func)
    def wrap(*args):
        if args not in cache:
            cache[args] = func(*args)
        return cache[args]
    return wrap

@memo
def fibX(n):
    if n<2: return 1
    return fibX(n-1) + fibX(n-2)



def memo2(fn):
    cache = {}
    miss = object()

    def wrapper(*args):
        result = cache.get(args, miss)
        if result is miss:
            result = fn(*args)
            cache[args] = result
        return result
    return wrapper

@memo2
def fibX2(n):
    if n<2: return 1
    return fibX2(n-1) + fibX2(n-2)

def memo(f):
    """Memoiziramo rekurzivno funkcijo f. Predpostavljamo, da f ne klice
    zares sama sebe, ampak poklice svoj prvi argument."""
    cache = {}

    def f_hitra(*args):
        """Ta dela, ker ima cache definiran zunaj."""
        if args in cache:
            return cache[args]
        else:
            y = f(f_hitra, *args)
            cache[args] = y
            return y

    # Rezultat je pohitrena funkcija f
    return f_hitra

##def fib_pomozna(jaz, n):
##    if n == 0 or n == 1: return 1
##    else: return jaz(n-1) + jaz(n-2)
##
##def fib_pocasi(n):
##    return fib_pomozna(fib_pocasi, n)

#fib_hitra = memo(fib_pomozna)

def fib_hitra(n):
    def fib_pomozna(jaz,n):
        if n == 0 or n == 1: return 1
        else: return jaz(n-1) + jaz(n-2)

    return memo(fib_pomozna)(n)
    
def bin_hitra(n,k):
    """Binomski koeficient."""

    def bin_pomozna(jaz,n,k):
        if k < 0 or k > n: return 0
        if k == 0 or k == n: return 1
        else: return jaz(n-1,k-1) + jaz(n-1,k)

    return memo(bin_pomozna)(n,k)

