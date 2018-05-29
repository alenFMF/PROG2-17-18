import java.awt.event.MouseEvent;

public class Tocka {
    private int x;
    private int y;
    
    public Tocka(int x, int y) {
        this.x = x;
        this.y = y;        
    }
    
    public Tocka(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public double razdalja(Tocka druga) {
        return Math.sqrt((x - druga.x)*(x - druga.x) + (y - druga.y)*(y - druga.y));
    }
}
