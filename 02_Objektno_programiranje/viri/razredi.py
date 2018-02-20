class Oseba:
    """Preprost razred, z imenom in priimkom osebe.""" #dokumentacijski niz
    x = 3
    print(3)
    def __init__(self, im, pr):
        self.ime = im
        self.priimek = pr

    def imeInPriimek(self):
        return self.ime + " " + self.priimek
# Opomba:
# Ko naložimo definicijo razreda, se pod simbol Oseba v globalnem kontekstu
# shrani definicija razreda, ki je tudi objekt. Če ta objekt (Oseba), kličemo
# kot funkcijo (callable), dejansko izvedemo konstruktor in kreiramo instance
# tega razreda.

def testOseba():
    o = Oseba("ime", "priimek")
    print(o.imeInPriimek())
    print(Oseba.imeInPriimek(o)) # alternativna oblika klica preko objekta
                                 # Oseba, ki hrani definicijo razreda

# Dedovanje
# Opombe:
# - minimiziramo večkratno pisanje iste kode in iz obstoječega razreda izpeljemo
# nadgrajenega oz. bolj specializiranega (ker ima bolj specialne funkcionalnosti)
#
# Za dedovanje navedemo razred iz katerega dedujemo v oklepaju pri prvi vrstici
# definicije razreda.
class Student(Oseba):
    def __init__(self, im, pr, vpst):
        #klici konstruktorja 'očetovskega' razreda.
        Oseba.__init__(self, im, pr)
        #Denimo da so vpisne številke oblike 27llnnnn, kjer je ll letnik vpisa.
        self.vpisnaStevilka = vpst

    def letnik(self):
        return self.vpisnaStevilka // 10000 % 100

# OPOMBA: vse funkcije, ki so definirane znotraj razreda so metode.
# obnašajo se tako, da se ob klicu na objektu preko operatorja pika (.)
# kot prvi argument vstavi objekt na katerem je klicana metoda.
# PAZI: zato ni nujno, da se prvi parameter metod imenuje 'self'. Lahko se
# imenuje karkoli in v vsaki metodi različno, a znotraj definicije posamezne
# metode vedno pomeni objekt, s katerega kličemo metodo.



# Dedovanje lahko uporabimo kot princip posploševanja oz. specializacije pri
# organizaciji kode. Razredi višje v hierarhiji so bolj splošni. Razredi, ki so
# nižje v hierarhiji se znajo "obnašati" (glede prisotnosti metod, atributov) kot
# tisti višje (npr. pes je tudi žival)
class Zival:
    def __init__(self, ime):
        self.ime = ime

    def oglasanje(self):
        return "No comment..."

class Pes(Zival):
    def __init__(self, ime, steviloNog = 4, dolzinaRepa = 0.2):
        Zival.__init__(self, ime)
        self.steviloNog = steviloNog
        self.dolzinaRepa = dolzinaRepa

    def oglasanje(self):
        return "Hov hov"

class Macka(Zival):
    def __init__(self, ime, dolzinaBrk=0.05):
        Zival.__init__(self, ime)
        self.dolzinaBrk = dolzinaBrk

    def oglasanje(self):
        return "Mjav mjav"

def testZivali():
    zivali = [Pes("Fifi"), Macka("Micka", dolzinaBrk=0.1), Macka("Leni")]
    for zival in zivali:
        print(zival.ime, ":", zival.oglasanje())
    
# tudi izjeme so razredi (dediči razreda Exception - oz. BaseException)
class MojaIzjema(Exception):
    def __init__(self, ind):
        self.index = ind

from math import *
class Vektor2D:
    "Dvodimezionalni vektor."
    def __init__(self, x=0, y=0):
        # 'skrita' atributa
        self._x = x
        self._y = y

    def norma(self):
        return sqrt(self._x**2 + self._y**2)

    # Norma kot lastnost preko uporabe dekoratorja @property  (funkcija norma2())
    #
    # Dekorator: funkcija ali razred napisana po protokolu imenovanem "descriptor protocol"
    # ki zapakira funkcijo na poseben način: nekaj kode doda pred ali za funkcijo in tako
    # funkcijo dopolni.
    #
    # Uporaba:
    # >>> v = Vektor(2,3)
    # >>> v.norma2
    # Take 'atribute', ki jih uporabljamo kot atribute, a so v resnici funkcije,
    # imenujemo 'lastnosti' (ang. property)    
    @property
    def norma2(self):
        return sqrt(self._x**2 + self._y**2)

    # Lastnost x z možnostjo branja in vpisa preko funkcije 'property'
    def vrniX(self):
        "Vrne x."
        print("Vračam x ...")
        return self._x

    def nastaviX(self, novi):
        print("Nastavljam x ...")
        if type(novi) is int or type(novi) is float:
            self._x = novi
        else:
            raise Exception()

    # s pomočjo funkcije propery popravimo objekt o, tako da bo:
    # o.x               klical funkcijo o.vrniX()
    # o.x = vrednost    klical funkcijo o.nastaviX(vrednost)
    x = property(fget=vrniX, fset=nastaviX, doc="Komponenta x.")

    
    # alternativen način za izvedbo lastnosti preko uporabe dekoratorjev
    @property
    def y(self):
        return self._y

    @y.setter
    def y(self, novi):
        self._y = novi
                
    def __abs__(self):
        return self.norma()

    # predstavitev objekta v obliki niza
    def __repr__(self):
        komponente = [str(i) if type(i) is int else "{0:.2f}".format(i) for i in (self._x, self._y)]
        return "({0},{1})".format(*komponente)

    # a + b -> a.__add__(b)
    # 'povozimo' metodo __add__ in tako omogočimo uporabo operatorja '+'
    def __add__(self, u):
        return Vektor2D(self._x + u._x, self._y + u._y)

    # a[i] -> a.__getitem__(i) - operator dostopa na indeksu.
    def __getitem__(self, i):
        if i == 0:
            return self._x
        if i == 1:
            return self._y
        # sprožanje lastne izjeme
        raise MojaIzjema(i)
    
        
        

class Matrika:
    """Matrika 2x2 definirana z dvema vektorjema - stolpcema."""
    def __init__(self, v1, v2):
        self.s1 = v1
        self.s2 = v2

    def __repr__(self):
        return "|{0:4}{1:4}|\n|{2:4}{3:4}|\n".format(self.s1[0], self.s2[0],
                                                     self.s1[1], self.s2[1])
    # a(v) -> a.__call__(v) - operator funkcijskega klica
    def __call__(self, v):
        return Vektor2D(self.s1[0]*v[0] + self.s2[0]*v[1],
                        self.s1[1]*v[0] + self.s2[1]*v[1])
        
def testVektorMatrika():
    s1 = Vektor2D(1,2.12)
    print("Prvi vektor:", s1)
    print("Norma je: ", s1.norma2)
    s2 = Vektor2D(3,4)
    print("Drugi vektor:", s2)
    m = Matrika(s1, s2)
    print("Matrika")
    print(m)
    print("{0} + {1} = {2}".format(s1, s2, s1 + s2))
    print("{0} . {1} = {2}".format(m, s1, m(s1)))        

# primer lovljenja in uporabe lastne izjeme
def kajJeNarobe():
    v = Vektor2D(1,2)
    try:
        v[3]
    except MojaIzjema as e:
        print("Vpisan indeks je {0}.".format(e.index))
        
    
