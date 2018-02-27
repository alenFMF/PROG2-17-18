# -*- encoding: utf-8 -*-

# Področje, na katerega lahko rišemo lomljeno črto.
# S pritiskom na levi gumb začnemo risati novo lomljeno črto,
# z desnim gumbom nadaljujemo prejšnjo črto.

from tkinter import *


class Risalnik():
    def __init__(self, master):
        # Trenutna točka, od koder bomo nadaljevali lomljeno
        # črto. Na začetku je ni.
        self.tocka = None
        self.risemo = True
        self.debelina = 5
        self.barva = 'black'

        # Naredimo področje za risanje
        self.canvas = Canvas(master, width=300, height=300)
        self.canvas.pack()

        # Registiramo se za premike miške
        self.canvas.bind("<B1-Motion>", self.nadaljuj_crto)
        self.canvas.focus_set()
        self.canvas.bind("<Key>", self.tipka_pritisnjena)
        self.canvas.bind("<ButtonRelease-1>", self.izklopi_risanje)


    def nadaljuj_crto(self, event):
        '''Nadaljuj lomljeno črto.'''
        if self.tocka is not None:
            (x, y) = self.tocka
            self.canvas.create_line(x, y, event.x, event.y,
                    width=self.debelina, capstyle=ROUND, fill=self.barva)
            self.tocka = (event.x, event.y)
        else:
           self.tocka = (event.x, event.y)            

    def izklopi_risanje(self, event):
        self.tocka = None
        
    def tipka_pritisnjena(self, event):
        if event.char == '+':
            self.debelina += 1
        if event.char == '-' and self.debelina > 1:
            self.debelina -= 1
        if event.char == 'c':
            self.barva = 'black'
        if event.char == 'm':
            self.barva = '#4ba5f4'
        if event.char == 'd':
            self.canvas.delete(ALL)            
            
# Glavnemu oknu rečemo "root" (koren), ker so grafični elementi
# organizirani v drevo, glavno okno pa je koren tega drevesa

# Naredimo glavno okno
root = Tk()

aplikacija = Risalnik(root)

# Kontrolo prepustimo glavnemu oknu. Funkcija mainloop neha
# delovati, ko okno zapremo.
root.mainloop()
