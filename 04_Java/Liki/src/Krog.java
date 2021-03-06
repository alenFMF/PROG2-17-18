import java.awt.Graphics;

public class Krog extends Lik {
    private int r; 
    
    public Krog(int x, int y, int r) {
        super(x, y);
        this.r = r; 
    }

    @Override
    public double ploscina() {
        return Math.PI * r * r;
    }

    @Override
    public void narisiSe(Graphics g) {
        g.setColor(this.getBarvaObrobe());
        g.drawOval(x - r, y - r, 2*r, 2*r);
        
    }

    @Override
    public String toString() {
        return String.format("Krog [r=%d]", r);
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
    
    
}
