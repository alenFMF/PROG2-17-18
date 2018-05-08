
public class PametniTelefon extends NavadenTelefon {

    private int kolkSemPameten = 10;
    
    public PametniTelefon() {}
    
    public PametniTelefon(int pametnost) {
        this.kolkSemPameten = pametnost;
    }

    public void slikaj(String oseba) {
        System.out.println("Slikam osebo: " + oseba);
    } 
    
    public int mojaPametnost() {
        return kolkSemPameten;
    }
    
    @Override
    public void poklici(String narocnik) {
        super.poklici(narocnik);
        System.out.println("Pametni telefon si je zapomnil klic za naročnika: " + narocnik);
    }

}
