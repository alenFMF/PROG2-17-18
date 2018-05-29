import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Stroke;

// https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html
public abstract class Lik {
    protected int x;
    protected int y;
    protected Color barvaObrobe;
    protected Color barvaNotranjosti;
    protected Stroke tipCrte;
    
    public Lik(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
    
    public void premakni(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
        
    public Color getBarvaObrobe() {
        return barvaObrobe;
    }

    public void setBarvaObrobe(Color barvaObrobe) {
        this.barvaObrobe = barvaObrobe;
    }

    public Color getBarvaNotranjosti() {
        return barvaNotranjosti;
    }

    public void setBarvaNotranjosti(Color barvaNotranjosti) {
        this.barvaNotranjosti = barvaNotranjosti;
    }

    public Stroke getTipCrte() {
        return tipCrte;
    }

    public void setTipCrte(Stroke tipCrte) {
        this.tipCrte = tipCrte;
    }
    
    public void setTipCrteOgrodje() {
        this.setTipCrte(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[] {10}, 0));
    }

    public abstract double ploscina();
    
    public abstract void narisiSe(Graphics g);
}
