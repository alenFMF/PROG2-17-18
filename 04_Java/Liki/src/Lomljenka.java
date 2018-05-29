import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class Lomljenka extends Lik {
    private Path2D lomljenka;
    
    public Lomljenka(int x, int y) {
        super(x, y);
        this.lomljenka = new Path2D.Float();
        this.lomljenka.moveTo(x, y);        
    }

    public void dodajTocko(int x, int y) {
        this.lomljenka.lineTo(x, y);
    }
    
    @Override
    public void premakni(int dx, int dy) {
        super.premakni(dx, dy);
        this.lomljenka.transform(AffineTransform.getTranslateInstance(dx, dy));
    }

    @Override
    public double ploscina() {
        return 0;
    }

    @Override
    public void narisiSe(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(5));
        g2.setColor(this.getBarvaObrobe());
        g2.draw(this.lomljenka);
    }

    
}
