import java.util.ArrayList;
import java.util.Scanner;

class AdditiveAlgorithm{
    int key;
    AdditiveAlgorithm(int key){
        this.key=key%26;
    }
    String encryptText(String plain_text){
        ArrayList<String> plain_words=new ArrayList<>();
        for (String element:plain_text.split(" ")){
            StringBuilder cipher_word=new StringBuilder(element);
            int character_value;
            for (int i = 0; i < element.length(); i++) {
                character_value=element.toUpperCase().charAt(i);
                if (character_value>64 && character_value<=90){
                    character_value=(character_value-65+this.key)%26;
                    cipher_word.setCharAt(i,(char)(character_value+65));
                }
            }
            plain_words.add(cipher_word.toString());
        }
        return String.join(" ",plain_words);
    }
    String decryptText(String cipher_text){
        ArrayList<String> plain_words=new ArrayList<>();
        for (String element:cipher_text.split(" ")){
            StringBuilder plain_word=new StringBuilder(element);
            int character_value;
            for (int i = 0; i < element.length(); i++) {
                character_value=element.toUpperCase().charAt(i);
                if (character_value>64 && character_value<=90){
                    if ((character_value-65-this.key)<0){
                        character_value=(character_value-65+26-this.key)%26;
                    }else{
                        character_value=(character_value-65-this.key)%26;
                    }
                    plain_word.setCharAt(i,(char)(character_value+65));
                }
            }
            plain_words.add(plain_word.toString());
        }
        return String.join(" ",plain_words);
    }
}
class RailFenceTranspositionAlgorithm {
    String encryptText(String plain_text) {
        ArrayList<String> plain_words=new ArrayList<>();
        for (String element:plain_text.split(" ")) {
            StringBuilder row1= new StringBuilder();
            StringBuilder row2= new StringBuilder();
            for (int i = 0; i < element.length(); i++) {
                if (i%2==0){
                 row1.append(element.charAt(i));
                }else{
                    row2.append(element.charAt(i));
                }
            }
            plain_words.add(row1+ row2.toString());
        }
        return String.join(" ",plain_words);
    }
    String decryptText(String cipher_text) {
        ArrayList<String> plain_words=new ArrayList<>();
        for (String element:cipher_text.split(" ")) {
            int divider_index=element.length()/2;
            if (element.length()%2!=0){
                divider_index+=1;
            }
            StringBuilder row1 = new StringBuilder(element.substring(0, divider_index));
            StringBuilder row2 = new StringBuilder(element.substring(divider_index));
            StringBuilder plain_word=new StringBuilder();
            int odd_positions=0,even_position=0;
            for (int i = 0; i < element.length(); i++) {
                if (i%2==0){
                    plain_word.append(row1.charAt(even_position++));
                }else{
                    plain_word.append(row2.charAt(odd_positions++));
                }
            }
            plain_words.add(plain_word.toString());
        }
        return String.join(" ",plain_words);
    }
}

public class Product_Cipher {
    public static void main(String[] args) {
        System.out.println("---- Welcome To Product Cipher ----");
        System.out.println("Here Additive method and Rail-fence method for Encryption is used\n");
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter The key value for Encryption : ");
        int key=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the Message To be Encrypted : ");
        String plain_text=scanner.nextLine();
        scanner.close();

        AdditiveAlgorithm additiveAlgorithm=new AdditiveAlgorithm(key);
        RailFenceTranspositionAlgorithm railFenceTranspositionAlgorithm=new RailFenceTranspositionAlgorithm();

        System.out.println("\nEncrypting..........");
        String encrypted_additive_cipher_text=additiveAlgorithm.encryptText(plain_text);
        String encrypted_railFenceTransposition_text=railFenceTranspositionAlgorithm.encryptText(encrypted_additive_cipher_text);
        System.out.println("Final Cipher text After Encryption : "+encrypted_railFenceTransposition_text);

        System.out.println("\nDecrypting..........");
        String decrypted_railFenceTransposition_text=railFenceTranspositionAlgorithm.decryptText(encrypted_railFenceTransposition_text);
        String decrypted_additive_plain_text=additiveAlgorithm.decryptText(decrypted_railFenceTransposition_text);
        System.out.println("Plain Text After Decryption : "+decrypted_additive_plain_text);
    }
}
