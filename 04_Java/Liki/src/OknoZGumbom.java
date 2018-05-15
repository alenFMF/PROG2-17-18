import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class OknoZGumbom extends JFrame implements ActionListener {
    private Platno platno;
    private JButton gumb;
        
    public OknoZGumbom(List<Lik> liki) {
        Container pane = this.getContentPane();
        GridBagLayout layout = new GridBagLayout();
        pane.setLayout(layout);
        
        GridBagConstraints gumbLayout = new GridBagConstraints();
        gumbLayout.gridx = 0;
        gumbLayout.gridy = 1;
        this.gumb = new JButton("Briši");
        gumb.addActionListener(this);
        pane.add(this.gumb, gumbLayout);
        
        GridBagConstraints platnoLayout = new GridBagConstraints();
        platnoLayout.gridx = 0;
        platnoLayout.gridy = 0;       
        this.platno = new Platno();
        for(Lik lik: liki) {
            platno.dodajLik(lik);
        }
        pane.add(platno, platnoLayout);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               System.exit(0);
            }        
        });
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.gumb) {
            System.out.println("Gumb pritisnjen");      
            this.platno.brisi();
        }       
    }


    public static void main(String[] args) {
        List<Lik> mojiLiki = new ArrayList<Lik>();
        mojiLiki.add(new Kvadrat(10, 10, 100));
        mojiLiki.add(new Krog(150, 150, 20));
        JFrame okno = new OknoZGumbom(mojiLiki);
        okno.pack();
        okno.setVisible(true);
        
    }
    

}
