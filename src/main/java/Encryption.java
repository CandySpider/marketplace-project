import org.json.JSONObject;

import java.security.*;
import javax.crypto.*;
public class Encryption  {
    private Signature sign;
    private KeyPairGenerator keyPairGen;
    private KeyPair pair;
    private Cipher cipher;
    public Encryption () throws  Exception
    {
        //Creating a Signature object
         this.sign = Signature.getInstance("SHA256withRSA");

        //Creating KeyPair generator object
        this.keyPairGen = KeyPairGenerator.getInstance("RSA");

        //Initializing the key pair generator
        this.keyPairGen.initialize(1024);

        //Generating the pair of keys
         this.pair = keyPairGen.generateKeyPair();

        //Creating a Cipher object
         this.cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        //Initializing a Cipher object
        this.cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
    }

    public Encryption(Signature sign, KeyPairGenerator keyPairGen, KeyPair pair, Cipher cipher) {
        this.sign = sign;
        this.keyPairGen = keyPairGen;
        this.pair = pair;
        this.cipher = cipher;
    }

    public Signature getSign() {
        return sign;
    }

    public KeyPairGenerator getKeyPairGen() {
        return keyPairGen;
    }

    public KeyPair getPair() {
        return pair;
    }

    public Cipher getCipher() {
        return cipher;
    }

    public byte[] doIT (String input0) throws Exception
    {
        //
        this.cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");   // resetam cipher ul pentru ca asa vor zeii ,altfel vom muri de mania acestora si anume EROAREA!!
        this.cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
        //

        //Adding data to the cipher
        byte[] input = input0.getBytes();
        this.cipher.update(input);

        //encrypting the data

        return cipher.doFinal();
    }
    public String undoIT (byte[] cipherText) throws Exception
    {
        //
        this.cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");   // resetam cipher ul pentru ca asa vor zeii ,altfel vom muri de mania acestora si anume EROAREA!!
        this.cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
        //
        //Initializing the same cipher for decryption
        this.cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());

        //Decrypting the text
        byte[] decipheredText = cipher.doFinal(cipherText);
        return  new String(decipheredText);
    }
    public static void main (String[] args) throws  Exception
    {


        String unu ="unu";
        String doi ="doi";
        Encryption encryptStuff = new Encryption();
        //System.out.println("cipher" + cipher.toString());
        //unu = encryptStuff.doIT(unu,cipher);
        //System.out.println("cipher" + cipher.toString());
        //doi = encryptStuff.doIT(doi,cipher);


        System.out.println((encryptStuff.doIT(doi)));

    }
}
