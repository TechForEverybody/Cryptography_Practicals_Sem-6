import java.util.Scanner;

class Encryption {
    double publicKey;
    double plainText;
    double cipherText;
    double n;
    Encryption(double publicKey, double plainText, double n) {
        System.out.println("\nPlain Text to be Encrypted : "+(long)plainText);
        this.publicKey = publicKey;
        this.plainText = plainText;
        this.n = n;
    }

    double encrypt() {
        System.out.println("Encrypting ......");
        this.cipherText = 1;
        for (int i = 1; i <= this.publicKey; i++) {
            this.cipherText = (this.cipherText * (this.plainText % this.n)) % this.n;
        }
        return this.cipherText;
    }
}

class Decryption {
    double privateKey;
    double cipherText;
    double plainText;
    double n;
    Decryption(double privateKey, double cipherText, double n) {
        System.out.println("\nCipher Text to be decrypted : "+ (long)cipherText);
        this.privateKey = privateKey;
        this.cipherText = cipherText;
        this.n = n;
    }
    double decrypt() {
        System.out.println("Decrypting ......");
        this.plainText = 1;
        for (int i = 1; i <= this.privateKey; i++) {
            this.plainText = (this.plainText * (this.cipherText % this.n)) % this.n;
        }
        return this.plainText;
    }
}

public class RSA_Algorithm {
    double public_key;
    private double private_key;
    double n;
    double phi_of_n;
    public double getN() {
        return this.n;
    }
    public double getPrivate_key() {
        return this.private_key;
    }
    public double getPublic_key() {
        return this.public_key;
    }
    RSA_Algorithm() {
        System.out.println("-------- Welcome To RSA Algorithm -------\n");
    }
    double generatePrivateKey(double public_key){
        double counter = 1;
        while (counter < phi_of_n) {
            if ((counter * public_key)%phi_of_n == (1 % phi_of_n)) {
                break;
            }
            counter++;
        }
        return counter;
    }
    public void Generate_Keys(double p, double q) {
        this.n = p * q;
        this.phi_of_n = (p - 1) * (q - 1);
        double CoPrimeNumber = 1;
        double gcd;
        for (int i = 2; i < this.phi_of_n; i++) {
            gcd = GCD(i, this.phi_of_n);
            if (gcd == 1) {
                CoPrimeNumber = i;
                break;
            }
        }
        this.public_key = CoPrimeNumber;
        double counter = 1;
        while (counter < phi_of_n) {
            if ((counter * CoPrimeNumber)%phi_of_n == (1 % phi_of_n)) {
                break;
            }
            counter++;
        }
        this.private_key = counter;
    }
    public static double GCD(double num1, double num2) {
        double minimal_value = Math.min(num1, num2);
        double gcd = 1;
        for (int i = 1; i <= minimal_value; i++) {
            if (num1 % i == 0 && num2 % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        RSA_Algorithm rsa_algorithm = new RSA_Algorithm();
        double p,q,massage;
        String query;
        System.out.print("Enter Value of p : ");
        p=scanner.nextInt();
        System.out.print("Enter Value of q : ");
        q=scanner.nextInt();
        rsa_algorithm.Generate_Keys(p, q);
        double publicKey = rsa_algorithm.getPublic_key();
        System.out.print("Enter Value of massage : ");
        massage=scanner.nextInt();
        double n = rsa_algorithm.getN();
        double privateKey = rsa_algorithm.getPrivate_key();
        System.out.print("is You have public key (YES/NO) : ");
        query=scanner.next();
        if (query.trim().equalsIgnoreCase("yes")){
            System.out.print("Enter Public Key : ");
            publicKey=scanner.nextInt();
            privateKey=rsa_algorithm.generatePrivateKey(publicKey);
        }
        scanner.close();
        System.out.println("\np -> " + (long)p);
        System.out.println("p -> " + (long)q);
        System.out.println("PUBLIC KEY => "+(long)publicKey);
        System.out.println("PRIVATE KEY => "+(long)privateKey);

        Encryption encryption = new Encryption(publicKey, massage, n);
        double cipherText = encryption.encrypt();
        System.out.println("Cipher Text : " + (long)cipherText);

        Decryption decryption = new Decryption(privateKey, cipherText, n);
        double plainText = decryption.decrypt();
        System.out.println("Plain Text : " + (long)plainText);
    }
}
