import java.security.*;
import javax.crypto.*;
public class Encryption  {
    public byte[] doIT (String input0,Cipher cipher) throws Exception
    {


        //Adding data to the cipher
        byte[] input = input0.getBytes();
        cipher.update(input);

        //encrypting the data

        return cipher.doFinal();
    }
    public String undoIT (byte[] cipherText,KeyPair pair,Cipher cipher) throws Exception
    {
        //Initializing the same cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());

        //Decrypting the text
        byte[] decipheredText = cipher.doFinal(cipherText);
        return  new String(decipheredText);
    }
    public static void main (String[] args) throws  Exception
    {
        //Creating a Signature object
        Signature sign = Signature.getInstance("SHA256withRSA");

        //Creating KeyPair generator object
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

        //Initializing the key pair generator
        keyPairGen.initialize(1024);

        //Generating the pair of keys
        KeyPair pair = keyPairGen.generateKeyPair();

        //Creating a Cipher object
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        //Initializing a Cipher object
        cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());

        String unu ="unu";
        String doi ="doi";
        Encryption encryptStuff = new Encryption();
        System.out.println("cipher" + cipher.toString());
       // unu = encryptStuff.doIT(unu,cipher);
        System.out.println("cipher" + cipher.toString());
       // doi = encryptStuff.doIT(doi,cipher);

//        System.out.println(unu);
//        System.out.println();
//        System.out.println(doi);
//        System.out.println();
        System.out.println(encryptStuff.undoIT(encryptStuff.doIT(unu,cipher),pair,cipher));
        //
        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");   // resetam cipher ul pentru ca asa vor zeii ,altfel vom muri de mania acestora si anume EROAREA!!
        cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
        //
        System.out.println(encryptStuff.undoIT(encryptStuff.doIT(doi,cipher),pair,cipher));
    }
}
