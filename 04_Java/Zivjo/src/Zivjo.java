
public class Zivjo {

    public static boolean jePrastevilo(int n) {
        for(int i = 2; i*i <= n; i++) {
            if(n % i == 0) return false;
        }
        return true;
    }
        
    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
    
    public static void main(String[] args) {
        for(int i = 2; i <= 100; i++) {
            if(jePrastevilo(i))
                System.out.println(i + " je praštevilo.");
            else 
                System.out.println(i + " ni praštevilo.");
        }
        int a = 12;
        int b = 70;
                
        System.out.println("Največji skupni delitelj " + a + " in " + b + " je " + gcd(a, b));
    }

}
