import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Platno extends JPanel {
    private List<Lik> liki;
    
    public Platno() {
        super();
        this.liki = new ArrayList<Lik>();
        this.setBackground(Color.white);
    }
    
    public void dodajLik(Lik lik) {
        liki.add(lik);
    }
    
    public void brisi() {
        liki.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        for(Lik lik: this.liki) {
            lik.narisiSe(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {   
        return new Dimension(300, 300);
    }
    
    
}
