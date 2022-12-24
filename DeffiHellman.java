import java.util.ArrayList;
import java.util.Random;
class Alice{
    double g;
    double p;
    double x;
    double r1;
    double shared_key;
    Alice(double g,double p){
        this.g=g;
        this.p=p;
        Random random=new Random();
        this.x=random.nextInt(1000);
    }

    public void setX(double x) {
        this.x = x;
    }
    void generatePublicKey(){
        System.out.println("Generating Private Key for Alice ......");
        double public_key=1;
        for (int i = 0; i < this.x; i++) {
            public_key=(public_key*(this.g%p))%p;
        }
        this.r1=public_key;
    }
    void generateSharedKey(double publicKey){
        System.out.println("Generating Shared Key (by Alice) ......");
        double sharedKey=1;
        for (int i = 0; i < this.x; i++) {
            sharedKey=(sharedKey*(publicKey%p))%p;
        }
        this.shared_key=sharedKey;
    }

}
class Bob{
    double g;
    double p;
    double y;
    double r2;
    double shared_key;
    Bob(double g,double p) {
        this.g=g;
        this.p=p;
        Random random=new Random();
        this.y=random.nextInt(1000);    }

    public void setY(double y) {
        this.y = y;
    }
    void generatePublicKey(){
        System.out.println("Generating Private Key for Bob ......");
        double public_key=1;
        for (int i = 0; i < this.y; i++) {
            public_key=(public_key*(this.g%p))%p;
        }
        this.r2=public_key;
    }
    void generateSharedKey(double publicKey){
        System.out.println("Generating Shared Key (by Bob) ......");
        double sharedKey=1;
        for (int i = 0; i < this.y; i++) {
            sharedKey=(sharedKey*(publicKey%p))%p;
        }
        this.shared_key=sharedKey;
    }
}

public class DeffiHellman {
    public static boolean checkForPrimitiveRoot(double p,double g){
        ArrayList<Double> values=new ArrayList<Double>();
        boolean is_primitive=true;
        for (int i = 1; i < p; i++) {
            double temp=1;
            for (int j = 0; j < i; j++) {
                temp=(temp*(g%p))%p;
            }
//            System.out.println(temp);
            if (values.contains(temp)){
                is_primitive=false;
                break;
            }else{
                values.add(temp);
            }
        }
        return is_primitive;
    }
    public static boolean checkForPrime(double p){
        boolean is_prime=true;
        for (int i = 2; i < p/2; i++) {
            if (p%i==0){
                is_prime=false;
                break;
            }
        }
        return is_prime;
    }
    public static void main(String[] args) {
        System.out.println("--- Welcome to Deffi Hellman Algorithm ---");
        double g=2;
        double p=11;
        System.out.println("is p is prime : "+checkForPrime(p));
        System.out.println("is g is primitive root of p "+checkForPrimitiveRoot(p,g));
        Alice alice = new Alice(g,p);
        System.out.println("x-> "+alice.x);
        Bob bob =new Bob(g,p);
        System.out.println("y-> "+bob.y);
//        alice.setX(15);
//        bob.setY(13);
        System.out.println("y-> "+bob.y);
        System.out.println("x-> "+alice.x);
        alice.generatePublicKey();
        bob.generatePublicKey();
        System.out.println("R1-> "+alice.r1);
        System.out.println("R2-> "+bob.r2);
        bob.generateSharedKey(alice.r1);
        alice.generateSharedKey(bob.r2);
        System.out.println("shared Keys are follows : ");
        System.out.println("by Alice -> "+alice.shared_key);
        System.out.println("by Bob -> "+bob.shared_key);
    }
}
