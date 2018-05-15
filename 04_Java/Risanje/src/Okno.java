import java.awt.Container;

import javax.swing.JFrame;

public class Okno extends JFrame {
    private Platno platno;
    
    public Okno() {
        super();    
        Container pane = this.getContentPane();
        platno = new Platno();
        pane.add(platno);
    }
    
    public static void main(String[] args) {
        JFrame okno = new Okno();
        okno.pack();
        okno.setVisible(true);       
    }

}
