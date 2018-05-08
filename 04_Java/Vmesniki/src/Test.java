import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        NavadenTelefon navaden = new NavadenTelefon();
        PametniTelefon pametni = new PametniTelefon(10);
        navaden.poklici("Francelj");
        pametni.poklici("Jožica");
        List<Telefon> telefoni = new LinkedList<Telefon>();
        telefoni.add(navaden);
        telefoni.add(pametni);
        String sef = "Janez";
        for(Telefon t: telefoni) {
            t.poklici(sef);
        }
        Telefon tel = pametni;
        NavadenTelefon tn = pametni;
        NavadenTelefon tn2 = (NavadenTelefon)tel;
        ((PametniTelefon)tel).slikaj("Milica");
    }

}
