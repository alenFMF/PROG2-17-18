import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * Če želimo komponento platno, na katero lahko rišemo, naredimo dediča razreda JPanel. 
 * Da nastavimo velikost platna predefiniramo (povozimo) metodo getPreferredSize.
 * Rišemo v metodi paintComponent s pomočjo objekta Graphics, ki ga med delovanjem sam poda Swing.
 */
public class Platno extends JPanel {
    public Platno() {
        super();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);       // naj se nariše prej vse, kar zahteva "oče".
        g.setColor(Color.red);         // nad rišemo z ustreznimi ukai.
        g.fillRect(10, 10, 50, 50);
        g.setColor(Color.blue);
        
        // Za lepše in bolj natančno risanje uporabljamo metode iz Graphic
        Graphics2D g2 = (Graphics2D)g;
        // antialiasing za manj nazobčeno grafiko
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3));
        g.drawLine(50, 200, 250, 10);
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html               
    }   
}
