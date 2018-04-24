package com.prog2.ulomek;

import java.util.List;

public class Ulomek {
    private int stevec;
    private int imenovalec;
    
    public Ulomek(int stevec, int imenovalec) {
        this.stevec = stevec;
        this.imenovalec = imenovalec;
    }
    
    public Ulomek(int stevilo) {
        this.stevec = stevilo;
        this.imenovalec = 1;
    }
    
    public Ulomek(Ulomek u) {
        this.stevec = u.stevec;
        this.imenovalec = u.imenovalec;
    }

    public Ulomek vsota(Ulomek u) {
        Ulomek x = new Ulomek(
                 (this.stevec*u.imenovalec + this.imenovalec*u.stevec),
                 (this.imenovalec*u.imenovalec)
               );
        x.okrajsaj();
        return x;
    }
            
    public static Ulomek vsota2(Ulomek u, Ulomek v) {
        Ulomek vmesni = new Ulomek(u);
        return vmesni.vsota(v);
    }
    
    public void pristej(Ulomek u) {
        this.stevec = this.stevec*u.imenovalec + this.imenovalec*u.stevec;
        this.imenovalec = this.imenovalec*u.imenovalec;
        this.okrajsaj();
    }
    
    public void okrajsaj() {
        int delitelj = gcd(this.stevec, this.imenovalec);
        this.stevec = this.stevec / delitelj;
        this.imenovalec = this.imenovalec / delitelj;       
    }
    
    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
    
    public static Ulomek sestejSeznam(List<Ulomek> seznam) {
        Ulomek vsota = new Ulomek(0);
        for(Ulomek u: seznam) {
            vsota.pristej(u);
        }
        return vsota;
    }
    
    @Override
    public String toString() {
        return stevec + "/" + imenovalec ;
    }
    
}
