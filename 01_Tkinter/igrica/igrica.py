# Preprosta igrica
import tkinter
import time

# Spodaj je vesoljska ladjica, ki jo lahko premikamo levo in desno
# Ladjica strelja metke (pritisk na presledek)
# Zgoraj so vesoljci
# Ko metek zadane vesoljca, ga ubije

# Hierarhija objektov v igrici (logika igre):
#
# - Igrica
#    (mora vedeti, v kateri Canvas se riše)
#   * zacni_igro
#   * koncaj_igro
#
# - Ladjica
#    (mora vedeti, v kateri Canvas se riše)
#   * premikanje
#   * izstreli nov metek
#
# - Vesoljec
#    (mora vedeti, v kateri Canvas se riše)
#   * (premikanje)
#
# - Metek
#    (mora vedeti, katera ladjica ga nadzoruje)
#    (mora vedeti, kateri vesoljci so na zaslonu)
#    (mora vedeti, v kateri Canvas se riše)
#   * premikanje
#   * zadetek


WIDTH=500
HEIGHT=500

VR=20 # Polmer vesoljca
VN=5  # Število vesoljcev v zgornji vrsti

LW=20 # Širina ladjice
LH=40 # Višina ladjice
LPREMIK=5 # Koliko se naenkrat premaknemo z ladjico

MW=5  # Širina metka
MH=20 # Višina metka
MHITROST=200 # hitrost (piksli/sekundo)

MAX_METKI = 10 # Največje dovoljeno število metkov
METEK_TIMEOUT = 0.25 # PREMOR MED METKI (sekunde)

class Vesoljec():

    def __init__(self, igrica, x, y):
        self.igrica = igrica
        self.gid = self.igrica.canvas.create_oval(x-VR, y-VR, x+VR, y+VR, fill="green")

class Ladjica():

    def __init__(self, igrica, x, y):
        # x, y = sredina zgornjega roba ladjice (kjer letijo metki ven)
        self.x = x
        self.y = y
        self.igrica = igrica
        # Podatki v zvezi z metki
        self.metki = [] # Seznam vseh trenutno aktivnih metkov
        self.cas_streljanja = 0 # Kdaj smo nazadnje streljali
        # TODO: trikotna ladjica je lepša
        self.gid = self.igrica.canvas.create_rectangle(0,0,1,1, fill="red")
        self.osvezi()
        self.igrica.canvas.bind("<Left>", self.premik_levo)
        self.igrica.canvas.bind("<Right>", self.premik_desno)
        self.igrica.canvas.bind("<space>", self.streljaj)


    def osvezi(self):
        """Postavi ladjico na zaslonu na trenutne koordinate."""
        self.igrica.canvas.coords(self.gid, self.x-LW/2, self.y, self.x+LW/2, self.y+LH)

    def zbrisi_metek(self, metek):
        self.igrica.canvas.delete(metek.gid) # zbirisi iz zaslona
        self.metki.remove(metek) # odstrani iz spiska

    def streljaj(self, event):
        trenutni_cas = time.time()
        if (len(self.metki) < MAX_METKI and
            trenutni_cas - self.cas_streljanja > METEK_TIMEOUT):
            m = Metek(self.igrica, self.x, self.y)
            self.metki.append(m)
            self.cas_streljanja = trenutni_cas

    def premik_levo(self, event):
        if self.x > WIDTH/10:
            self.x -= LPREMIK
            self.osvezi()

    def premik_desno(self, event):
        if self.x < 9 * WIDTH/10:
            self.x += LPREMIK
            self.osvezi()

class Metek():
    def __init__(self, igrica, x, y):
        self.igrica = igrica
        # (x,y) = sredina zgornjega roba
        self.x = x
        self.y = y
        self.t = time.time() # Kdaj smo se nazadnje animirali
        self.gid = self.igrica.canvas.create_rectangle(0,0,1,1,fill="yellow")
        self.igrica.canvas.tag_lower(self.gid)
        self.osvezi()
        self.animiraj()

    def osvezi(self):
        self.igrica.canvas.coords(self.gid, self.x-MW/2, self.y, self.x+MW/2, self.y+MH)

    def animiraj(self):
        if self.y < -MH:
            # Metek je šel čez zgornji rob
            self.igrica.ladjica.zbrisi_metek(self)
        else:
            # Ali smo se zaleteli?
            # Seznam vseh gid-jev, ki se dotikajo metka
            lst = self.igrica.canvas.find_overlapping(
                self.x-MW/2, self.y, self.x+MW/2, self.y+MH)
            for v in self.igrica.vesoljci:
                # ali je gid od vesoljca v element spiska lst?
                if v.gid in lst:
                    self.igrica.zbrisi_vesoljca(v)
                    self.igrica.ladjica.zbrisi_metek(self)
                    return
            # nismo se zaleteli
            t2 = time.time()
            dt = t2 - self.t
            self.t = t2
            self.y -= MHITROST * dt
            self.osvezi()
            self.igrica.canvas.after(50, self.animiraj)

class Igrica():

    def zbrisi_vesoljca(self, vesoljec):
        self.canvas.delete(vesoljec.gid) # zbrisemo iz zaslona
        self.vesoljci.remove(vesoljec) # odstranimo iz spiska

    def __init__(self, master):
        self.canvas = tkinter.Canvas(master, width=WIDTH, height=HEIGHT, background="black")
        self.canvas.grid(row=0, column=0)
        self.canvas.focus_set()
        # Dodamo vesoljce
        y = HEIGHT/10 + VR
        # TODO: popravi formulo, da bodo centrirani
        x0 = (WIDTH - 2 * VR * VN - VR * (VN - 1)) / 2
        self.vesoljci = [Vesoljec(self, x0 + i * (3 * VR), y) for i in range(VN)]
        self.ladjica = Ladjica(self, WIDTH/2, 9*HEIGHT/10 - LH)



# Naredimo glavno okno
root = tkinter.Tk()

root.title("Galaxy Shootout III")

aplikacija = Igrica(root)

# Kontrolo prepustimo glavnemu oknu. Funkcija mainloop neha
# delovati, ko okno zapremo.
root.mainloop()
