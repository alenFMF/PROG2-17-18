import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Okno extends JFrame {
    private Platno platno;
    
    public Okno(List<Lik> liki) {
        super();
        Container pane = this.getContentPane();
        FlowLayout layout = new FlowLayout();
        pane.setLayout(layout);
        
        this.platno = new Platno();
        for(Lik lik: liki) {
            platno.dodajLik(lik);
        }
        pane.add(platno);
    }
    
    public static void main(String[] args) {
        List<Lik> mojiLiki = new ArrayList<Lik>();
        mojiLiki.add(new Kvadrat(10, 10, 100));
        mojiLiki.add(new Krog(150, 150, 20));
        JFrame okno = new Okno(mojiLiki);
        okno.pack();
        okno.setVisible(true);
    }
    
}


//https://www.tutorialspoint.com/swing/index.htm