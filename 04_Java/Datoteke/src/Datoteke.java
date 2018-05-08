import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Datoteke {

    public static String mapa = "/Kingston/delo/vaje/2017-18/Prog2/tmp/";

    private static String celaPot(String ime) {
        return(mapa + "/" + ime);
    }
    
    public static void main(String[] args) {
        pisiBrisi();
    }

    public static void pisi(String ime) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(ime, "UTF-8");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vpiši besedilo tekstovne datoteke. Zaključi s prazno vrstico:");
        while(true) {            
            String vpisano = scanner.nextLine();
            if(vpisano.length() == 0) break;
            writer.println(vpisano);
        } 
        writer.close();
        System.out.println("konec");
        
    }
    
    public static String beri1(String ime) throws FileNotFoundException, IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(ime))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        }
    }
    
    
    public static void pisiBrisi() {
        try {
            String ime = celaPot("test1.txt");
            pisi(ime);
            String prebrano = beri1(ime);
            System.out.println("Prebral sem:");
            System.out.println(prebrano);
        } catch(Exception e) {
            System.out.println(e);
        }        
    }
    
}

