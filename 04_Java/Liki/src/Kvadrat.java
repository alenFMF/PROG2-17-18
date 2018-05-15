import java.awt.Graphics;

public class Kvadrat extends Lik {
    private int a;
    
    public Kvadrat(int x, int y, int a) {
        super(x, y);
        this.a = a;
    }
    
    @Override
    public double ploscina() {
        return a*a;
    }

    @Override
    public void narisiSe(Graphics g) {
        g.fillRect(x, y, a, a);
    }

    @Override
    public String toString() {
        // https://dzone.com/articles/java-string-format-examples
        return String.format("Kvadrat [a=%d]", a);
    }
    
}
