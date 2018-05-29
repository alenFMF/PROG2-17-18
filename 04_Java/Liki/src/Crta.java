import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Crta extends Lik {
    private int x2;
    private int y2;

    public Crta(int x1, int y1, int x2, int y2) {
        super(x1, y1);
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public double ploscina() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void narisiSe(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(1));
        g2.setColor(this.getBarvaObrobe());
        g2.drawLine(x, y, x2, y2);        
    }
    
}
