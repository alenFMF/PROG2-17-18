package com.prog2.ulomek;

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

    @Override
    public String toString() {
        return stevec + "/" + imenovalec ;
    }
    
}
