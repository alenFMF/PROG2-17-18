import java.awt.Container;

import javax.swing.JFrame;

public class Okno extends JFrame {
    private Platno platno;
    

    public Okno() {        
        // Okna so dediči JFrame. V konstruktorju sestavimo vsebino okna tako, da v 'pane' 
        // dodajamo komponente.                 
        super();    
        Container pane = this.getContentPane();
        platno = new Platno();   
        pane.add(platno);
    }
    
    public static void main(String[] args) {
        // Osnovna inicializacija okna.
        JFrame okno = new Okno();
        okno.pack();
        okno.setVisible(true);       
    }

}
