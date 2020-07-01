package tests.managers;

import managers.AESencryption;
import managers.ClientManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

 public class ClientManagerTest {
    private JSONObject saverUnit;
    @BeforeEach
    void setUp() throws IOException {
        ClientManager saver = new ClientManager();
        String contents;

            contents = new String((Files.readAllBytes(Paths.get(saver.getFilePath()))));


        saverUnit = new JSONObject(contents);
    }

    @Test
    void AdderRemover()
    {   AESencryption encrypt = new AESencryption();
        ClientManager.Client experimentalClient = new ClientManager.Client("Ion","Castan","0756444890","Jud. PH,Oras. Bacanesti","youexp@gmail.com","xxDemonSlayerxx",encrypt.encrypt("gigel"),encrypt.encrypt("4485790854113695"));
        ClientManager.Client experimentalClient2 = new ClientManager.Client("Mihai","Corneliu","0782443890","Jud. Timis,Oras. Timisoara","youexp2@gmail.com","Bravo",encrypt.encrypt("idk12"),encrypt.encrypt("4539535904808240"));


        ClientManager manageStuff = new ClientManager();
        manageStuff.init();
        manageStuff.addJsonObj(experimentalClient);
        manageStuff.addJsonObj(experimentalClient);
        manageStuff.addJsonObj(experimentalClient2);
        int []exp={0};
        manageStuff.removeJsonArray(exp);
        JSONArray first = manageStuff.showAll();

        manageStuff.init();
        manageStuff.addJsonObj(experimentalClient);
        manageStuff.addJsonObj(experimentalClient2);

        JSONArray second = manageStuff.showAll();

       assertEquals(first.toString(),second.toString());



    }

    @Test
    void SearcherRemover ()
    {
        AESencryption encrypt = new AESencryption();
        ClientManager.Client experimentalClient = new ClientManager.Client("Ion","Castan","0756444890","Jud. PH,Oras. Bacanesti","youexp@gmail.com","xxDemonSlayerxx",encrypt.encrypt("gigel"),encrypt.encrypt("4485790854113695"));
        ClientManager.Client experimentalClient2 = new ClientManager.Client("Mihai","Corneliu","0782443890","Jud. Timis,Oras. Timisoara","youexp2@gmail.com","Bravo",encrypt.encrypt("idk12"),encrypt.encrypt("4539535904808240"));


        ClientManager manageStuff = new ClientManager();
        manageStuff.init();
        manageStuff.addJsonObj(experimentalClient);
        manageStuff.addJsonObj(experimentalClient);
        manageStuff.addJsonObj(experimentalClient2);

        JSONArray memory = manageStuff.searchJsonObj("Ion",1);
        JSONObject memory2 = memory.getJSONObject(0);
        int a = Integer.parseInt((String) memory2.get("clientId"));
        int []exp = {a};
        manageStuff.removeJsonArray(exp);
        manageStuff.removeJsonArray(exp);

        JSONArray first = manageStuff.showAll();

        manageStuff.init();
        manageStuff.addJsonObj(experimentalClient2);

        JSONArray second = manageStuff.showAll();

        assertEquals(first.toString(),second.toString());
    }

    @AfterEach
    void tearDown() throws FileNotFoundException {
        ClientManager saver = new ClientManager();
        PrintWriter writer;

            writer = new PrintWriter(saver.getFilePath());

        writer.print(saverUnit);
        writer.close();
    }
}