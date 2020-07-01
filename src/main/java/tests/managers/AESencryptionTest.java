package tests.managers;

import managers.AESencryption;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AESencryptionTest {

    @Test
    void encrypt() {
        AESencryption test1 = new AESencryption();
        String testString = "stufftoencrypt";

        String testString2 = test1.encrypt(testString);
        assertNotEquals(testString,testString2);
    }

    @Test
    void decrypt() {
        AESencryption test1 =new AESencryption();
        String testString = "stuff";
        String in = test1.encrypt("stuff");
        String out =test1.decrypt(in);
        assertEquals(testString,out);
    }
}