package com.prog2.ulomek;

import java.util.ArrayList;
import java.util.List;

public class GlavniProgram {

    public static void main(String[] args) {
        Ulomek u = new Ulomek(2, 3);
        Ulomek v = new Ulomek(6, 4);
        System.out.println("na zacetku:" + u + " ... " + v);
        Ulomek rezultat = u.vsota(v);
        System.out.println(rezultat);
        
        System.out.println(Ulomek.vsota2(u, v));
        
        System.out.println("prej:" + u + " ... " + v);
        u.pristej(v);
        System.out.println("potem:" + u + " ... " + v);
        
        List<Ulomek> ulomki = new ArrayList<Ulomek>();
        ulomki.add(new Ulomek(1, 2));
        ulomki.add(new Ulomek(1, 4));
        ulomki.add(new Ulomek(1, 5));
                
        System.out.println(Ulomek.sestejSeznam(ulomki));
    }

}
