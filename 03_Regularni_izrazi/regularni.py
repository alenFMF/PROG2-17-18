def celo(s):
    try:
        int(s)
    except:
        return False
    return True

def prepoznajAvto(niz):
    if niz == "avto":
        return True

import re

def prepoznajAvtoRe(niz):
    vzorec = "avto"
    rezultat = re.match(vzorec, niz)
    return rezultat != None

def prepoznajAvtoRe2(niz):
    vzorec = r"^avto$"
    rezultat = re.match(vzorec, niz)
    return rezultat != None

def tocnoTri(niz):
    vzorec = r"^...$"
    rezultat = re.match(vzorec, niz)
    return rezultat != None

# . , * + - ? { } [ ] \ | ( ) ^ $

def dvePiki(niz):
    vzorec = r"^\.\.$"
    rezultat = re.match(vzorec, niz)
    return rezultat != None

# \d - katera koli števka
# \D - kateri koli znak razen števke
# \s - kateri koli beli znak (presledek, tabulator, skok v novo vrstico)
# \S - kateri koli nebeli znak
# \w - kateri koli alfanumeričen znak ali podčrtaj
# \W - kateri koli znak, ki ni alfanumeričen


def celoStevilo3(niz):
    vzorec = r"^\d(\d)\d$"
    rezultat = re.match(vzorec, niz)
    return rezultat != None

# oklepaji - grupiranje

def triAlfaZnakiABC(niz):
    vzorec = r"^[abc][abc][abc]$"
    rezultat = re.match(vzorec, niz)
    return rezultat != None
    
def dvaMalaZnakaEnaStevka(niz):
    vzorec = r"^[a-z][a-z][0-9]$"
    rezultat = re.match(vzorec, niz)
    return rezultat != None

def triMaliZnakiAliStevke(niz):
    vzorec = r"^[a-z0-9][a-z0-9][a-z0-9]$"
    rezultat = re.match(vzorec, niz)
    return rezultat != None

def enaAliVecStevk(niz):
    vzorec = r"^\d+$"
    rezultat = re.match(vzorec, niz)
    return rezultat != None
    
def nicAliVecStevk(niz):
    vzorec = r"^\d*$"
    rezultat = re.match(vzorec, niz)
    return rezultat != None

def crkaMogoceStevilkaCrka(niz):
    vzorec = r"^[a-zA-Z]\d?[a-zA-Z]$"
    rezultat = re.match(vzorec, niz)
    return rezultat != None

def celoStevilo(niz):
    vzorec = r"^\s*[\+\-]?(0|([1-9]\d*))\s*$"
    rezultat = re.match(vzorec, niz)
    return rezultat != None


def celoStevilo2(niz):
    vzorec = r"^\s*[\+\-]?(0|([1-9]\d*))\s*$"
    rezultat = re.match(vzorec, niz)
    if rezultat != None:
        print(rezultat.group(0))
        print(rezultat.group(1))
        print(rezultat.group(2))
    
def celoStevilo3(niz):
    vzorec = r"^\s*([\+\-]?(0|([1-9]\d*)))\s*$"
    rezultat = re.match(vzorec, niz)
    if rezultat != None:
        return rezultat.group(1)

def petStevk(niz):
    vzorec = r"^\d{5}$"
    rezultat = re.match(vzorec, niz)
    return rezultat != None

def triDoPetStevk(niz):
    vzorec = r"^\d{3,5}$"
    rezultat = re.match(vzorec, niz)
    return rezultat != None

def vektor(niz):
    vzorec = r"^\s*\(\s*([\+\-]?(0|([1-9]\d*)))\s*\,\s*([\+\-]?(0|([1-9]\d*)))\s*\,\s*([\+\-]?(0|([1-9]\d*)))\s*\)\s*$"
    rezultat = re.match(vzorec, niz)
    if rezultat != None:
        return (rezultat.group(1), rezultat.group(4), rezultat.group(7))

def seznam(niz):
    vzorec = r"^\s*\w+\s*(\,\s*\w+)*\s*$"
    rezultat = re.match(vzorec, niz)
    return rezultat != None

def email(niz):
    vzorec = r"^(\w+(\.\w+)*)@(\w+(\.\w+)*)$"
    rezultat = re.match(vzorec, niz)
    if rezultat != None:
        return rezultat.group(1), rezultat.group(3)
    
    
