import java.awt.Graphics;

// https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html
public abstract class Lik {
    protected int x;
    protected int y;
    
    public Lik(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
    
    public void premakni(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    
    public abstract double ploscina();
    
    public abstract void narisiSe(Graphics g);
}
