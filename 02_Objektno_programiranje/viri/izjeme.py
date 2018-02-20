# primer loveljenja različnih izjem

# definicija novega tipa izjeme brez posebne funkcionalnosti
class IzjemaPri2(Exception):
    pass

# definicija naše posebne izjeme, ki hrani nek podatek
class CudnaIzjema(Exception):
    def __init__(self, n):
        self.n = n
        
    def __str__(self):
        return "Jaz sem ena čudna izjema in nosim število {0}.".format(self.n)
        
while True:
    print("-"*20)
    print("Radi bi delili dve števili.")
    svinjam = ""
    try:
        svinjam = "začel sem svinjat ..."
        a = int(input("Vpiši prvo število:"))
        b = int(input("Vpiši drugo število:"))
        print(a/b)
        if  a == 2:
            raise IzjemaPri2 # lahko vržemo kar objekt, ki definira razred izjeme!
        if  a > 5:
            raise CudnaIzjema(a) # metanje naše posebne izjeme
        if  a < 0:
            nekiTotalnoBrezveze
    except ZeroDivisionError:
        print("Deljenje z 0 ni veljavno.")
    except ValueError:
        print("Nepravilen vnos števila - ni možna pretvorba.")
    except KeyboardInterrupt:
        print("Ha ha, te ne pustim prekiniti s Ctrl+C ...")
    except CudnaIzjema as e: # lovljenje specifične izjeme in prepis v spremenljivko e
        print(e)
    except: # polovi ostale izjeme. Če nas zanima tip izjem, lahko napišemo: except Exception as e:
        print("Nič mi ni jasno. Mogoče izjema pri 2??? Mogoče Ctrl + D??? Mogoče, pa je kje v kodi sintaktična napaka???")
    finally: # finally se vedno izvede po zaključku zanke. Namenjen je čiščenju.
        print("Čiščenje na koncu ...")
        svinjam = ""

    if len(svinjam) > 0:
        print("Nekdo je nekaj posvinjal ...")
    else:
        print("Vse je čisto!")


