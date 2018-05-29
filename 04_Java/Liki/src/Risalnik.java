import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.Timer;

public class Risalnik extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    private Platno platno;
    private JButton gumb;
    private String kajRisem = null;
    private Tocka trenutnaTocka = null;
    private Color barvaCrte = Color.black;
    private Color barvaOzadja = Color.white;
    private Lik likOgrodje = null;

    private JButton orodnaIzborBarveCrte;
    private JButton orodnaIzborBarveOzadja;
    
    private JToggleButton orodnaKvadrat;
    private JToggleButton orodnaKrog;
    private JToggleButton orodnaLomljenka;
    private JToggleButton orodnaAnimacija;
    
    private int korakAnimacije = 0;
    private Timer animacijskiTimer;
    
    private final Color BARVA_OBROBE = Color.BLACK;
    private final Color BARVA_OZADJA = Color.WHITE;
    
    public Risalnik() {
        Container pane = this.getContentPane();
        GridBagLayout layout = new GridBagLayout();
        pane.setLayout(layout);
        
        GridBagConstraints gumbLayout = new GridBagConstraints();
        gumbLayout.gridx = 0;
        gumbLayout.gridy = 2;
        this.gumb = new JButton("Briši");
        gumb.setActionCommand("BrisiGumb");
        gumb.addActionListener(this);
        pane.add(this.gumb, gumbLayout);
        
        GridBagConstraints platnoLayout = new GridBagConstraints();
        platnoLayout.gridx = 0;
        platnoLayout.gridy = 1;   
        platnoLayout.fill = GridBagConstraints.BOTH; // se razteguje v vse smeri
        platnoLayout.weightx = 1; // zavzame ves prostor vodoravno
        platnoLayout.weighty = 1; // zavzame ves prostor navpično        
        this.platno = new Platno();

        
        pane.add(platno, platnoLayout); 

        // dodajanje listenerjev na platno
        this.platno.addMouseListener(this);
        this.platno.addMouseMotionListener(this);
        
        // izgradnja menija 
        JMenuBar menijskaVrstica = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit"); 
        
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.setMnemonic(KeyEvent.VK_N);
        newMenuItem.setActionCommand("New");

        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.setActionCommand("Open");

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setActionCommand("Save");

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setActionCommand("Exit");

        JMenuItem cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.setActionCommand("Cut");

        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.setActionCommand("Copy");

        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.setActionCommand("Paste");

        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);
        cutMenuItem.addActionListener(this);
        copyMenuItem.addActionListener(this);
        pasteMenuItem.addActionListener(this);        
        
        // umeščanje menijev
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);        
        
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        

        //pripenjanje glavnih podmenijev
        menijskaVrstica.add(fileMenu);
        menijskaVrstica.add(editMenu);

        //pripenjanje menjiske vrstice na okno
        setJMenuBar(menijskaVrstica);
        setVisible(true);          
        
        // Orodna vrstica
        JToolBar toolBar = new JToolBar();
        GridBagConstraints toolbarLayout = new GridBagConstraints();
        toolbarLayout.gridx = 0;
        toolbarLayout.gridy = 0;
        toolbarLayout.weightx = 1; // se razteguje vodoravno
        toolbarLayout.anchor = GridBagConstraints.WEST; // je na levi strani
        pane.add(toolBar, toolbarLayout);

        // gumbi v orodni vrstici
        // brisi
        JButton orodnaBrisi = new JButton("Zbriši");
        orodnaBrisi.setActionCommand("OrodnaBrisi");
        orodnaBrisi.addActionListener(this);
        toolBar.add(orodnaBrisi);

        // izbira barv
        orodnaIzborBarveCrte = new JButton("Barva črte");
        orodnaIzborBarveCrte.setOpaque(true); // da se bo videlo barvo gumba
        orodnaIzborBarveCrte.setBorderPainted(false); // da se bo dejansko pobarvalo ozadje
        this.barvaCrte = BARVA_OBROBE;
        orodnaIzborBarveCrte.setActionCommand("OrodnaIzborBarveCrta");
        orodnaIzborBarveCrte.addActionListener(this);
        toolBar.add(orodnaIzborBarveCrte);        

        orodnaIzborBarveOzadja = new JButton("Barva ozadja");
        orodnaIzborBarveOzadja.setOpaque(true); // da se bo videlo barvo gumba
        orodnaIzborBarveOzadja.setBorderPainted(false); // da se bo dejansko pobarvalo ozadje
        this.barvaOzadja = BARVA_OZADJA;
        orodnaIzborBarveOzadja.setActionCommand("OrodnaIzborBarveOzadja");
        orodnaIzborBarveOzadja.addActionListener(this);
        toolBar.add(orodnaIzborBarveOzadja);  
        
        pobarvajGumbeZaBarve();
        
        // liki 
        orodnaKvadrat = new JToggleButton("Kvadrat");
        orodnaKvadrat.setActionCommand("OrodnaRisiKvadrat");
        orodnaKvadrat.addActionListener(this);
        toolBar.add(orodnaKvadrat);  

        orodnaKrog = new JToggleButton("Krog");
        orodnaKrog.setActionCommand("OrodnaRisiKrog");
        orodnaKrog.addActionListener(this);
        toolBar.add(orodnaKrog);  

        orodnaLomljenka = new JToggleButton("Lomljenka");
        orodnaLomljenka.setActionCommand("OrodnaRisiLomljenko");
        orodnaLomljenka.addActionListener(this);
        toolBar.add(orodnaLomljenka);  

        orodnaAnimacija = new JToggleButton("Animiraj");
        orodnaAnimacija.setActionCommand("OrodnaAnimiraj");
        orodnaAnimacija.addActionListener(this);
        toolBar.add(orodnaAnimacija);  
        
        // križec za zapiranje okna
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               System.exit(0);
            }        
        }); 
        
        // prestrezanje tipk
        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {
              if (e.getID() == KeyEvent.KEY_TYPED) {
                System.out.println(e);
              }
              // Pass the KeyEvent to the next KeyEventDispatcher in the chain
              return false;
            }
        };
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);      
        animacijskiTimer = new Timer(25, this);
        
    }
    
    public static void main(String[] args) {
        JFrame okno = new Risalnik();
        okno.pack();
        okno.setVisible(true);
    }

    
    private void pobarvajBarvaGumb(JButton gumb, Color barva) {
        Color komplementarna =
                new Color(255 - barva.getRed(),
                          255 - barva.getGreen(),
                          255 - barva.getBlue());
        gumb.setForeground(komplementarna);
        gumb.setBackground(barva);
    }
    
    private void pobarvajGumbeZaBarve() {
        pobarvajBarvaGumb(orodnaIzborBarveCrte, this.barvaCrte);
        pobarvajBarvaGumb(orodnaIzborBarveOzadja, this.barvaOzadja);
    }
    
    public void vklopiGumb(JToggleButton gumb) {
        this.orodnaKrog.setSelected(false);
        this.orodnaKvadrat.setSelected(false);
        this.orodnaLomljenka.setSelected(false);
        this.orodnaAnimacija.setSelected(false);
        if(gumb != null) {
            gumb.setSelected(true);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.animacijskiTimer) {
            if(this.korakAnimacije > 100) {
                this.animacijskiTimer.stop();
                this.korakAnimacije = 0;
                vklopiGumb(null);
                return;
            }
            this.korakAnimacije += 1;
            this.platno.zadnjiLik().premakni(1, 1);
            repaint();
            return;
        }
        String izbira = e.getActionCommand();
        
        switch(e.getActionCommand()) {
            case "Exit":
                System.exit(0);
                break;
            case "OrodnaBrisi":
            case "BrisiGumb":
                this.platno.brisi();
                break;
            case "OrodnaIzborBarveCrta":
                this.barvaCrte = JColorChooser.showDialog(this, "Barva črte", this.barvaCrte);
                pobarvajGumbeZaBarve();
                break;
            case "OrodnaIzborBarveOzadja":
                this.barvaOzadja = JColorChooser.showDialog(this, "Barva ozadja", this.barvaOzadja);
                pobarvajGumbeZaBarve();
                break;
            case "OrodnaRisiKrog":
                this.kajRisem = "KROG";
                vklopiGumb(this.orodnaKrog);
                break;
            case "OrodnaRisiKvadrat":
                this.kajRisem = "KVADRAT";
                vklopiGumb(this.orodnaKvadrat);
                break;
            case "OrodnaRisiLomljenko":
                this.kajRisem = "LOMLJENKA";
                vklopiGumb(this.orodnaLomljenka);
                break;               
            case "OrodnaAnimiraj":
                vklopiGumb(this.orodnaAnimacija);
                this.korakAnimacije = 0;
                if(this.platno.zadnjiLik() != null) {
                    this.animacijskiTimer.start();
                } else {
                    vklopiGumb(null);
                }               
                break; 
            case "Save":   
                System.out.println("Evo pritiskam Save");
                break;
            default:
                System.out.println(String.format("Izbrali ste meni \"%s\"", izbira)); 
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(this.kajRisem != null) {
            switch(this.kajRisem) {
                case "KROG": 
                    ((Krog)this.likOgrodje).setR((int)this.trenutnaTocka.razdalja(new Tocka(e)));
                    break;
                case "KVADRAT":
                    break;
                case "LOMLJENKA":
                    ((Lomljenka)this.likOgrodje).dodajTocko(e.getX(), e.getY());
                    break;
                case "CRTA":
                    break;
                default:
                    break;                  
            }
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(this.kajRisem != null) {
            switch(this.kajRisem) {
                case "KROG":
                    this.likOgrodje = new Krog(e.getX(), e.getY(), 0);
                    this.likOgrodje.setBarvaObrobe(this.barvaCrte);
                    this.trenutnaTocka = new Tocka(e);
                    this.likOgrodje.setTipCrteOgrodje();
                    this.platno.dodajLik(this.likOgrodje);
                    break;
                case "KVADRAT":
                    this.trenutnaTocka = new Tocka(e);
                    break;
                case "LOMLJENKA":
                    this.likOgrodje = new Lomljenka(e.getX(), e.getY());
                    this.likOgrodje.setBarvaObrobe(this.barvaCrte);
                    this.platno.dodajLik(this.likOgrodje);
                    break;
                case "CRTA":
                    this.trenutnaTocka = new Tocka(e);
                    break;
                default:
                    break;                  
            }  
            vklopiGumb(null);
            repaint();
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(this.kajRisem != null) {
            switch(this.kajRisem) {
                case "KROG":  
                    ((Krog)this.likOgrodje).setR((int)this.trenutnaTocka.razdalja(new Tocka(e)));
                    this.likOgrodje.setBarvaObrobe(this.barvaCrte);
                    this.likOgrodje = null;
                    break;
                case "KVADRAT":
                    break;
                case "LOMLJENKA":
                    break;
                case "CRTA":
                    break;
                default:
                    break;                  
            }  
            repaint();
            this.kajRisem = null;            
        }
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

}
