package tests.managers;

import managers.AESencryption;
import managers.StaffManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class StaffManagerTest {
    private JSONObject saverUnit;
    @BeforeEach
    void setUp() throws IOException {
        StaffManager saver = new StaffManager();
        String contents ;

        contents = new String((Files.readAllBytes(Paths.get(saver.getFilePath()))));


        saverUnit = new JSONObject(contents);
    }

    @Test
    void AdderRemover() {
        AESencryption encrypt = new AESencryption();
        StaffManager.Staff experimentalStaff = new StaffManager.Staff("Marin","Costea","interzis@yahoo.com","mar23",encrypt.encrypt("nice"),2500);
        StaffManager.Staff experimentalStaff2 = new StaffManager.Staff("Ioana","Gheorghe","rockit@gmai.com","nope12",encrypt.encrypt("super"),4000);
        StaffManager.Staff experimentalStaff3 =new StaffManager.Staff("Jane","Daria","lol@gmail.com","Dar12",encrypt.encrypt("parolamea24"),3000);
        StaffManager staffManagerTest = new StaffManager();
        staffManagerTest.init();
        staffManagerTest.addJsonObj(experimentalStaff);
        staffManagerTest.addJsonObj(experimentalStaff2);
        staffManagerTest.addJsonObj(experimentalStaff3);
        int []exp={1};
        staffManagerTest.removeJsonArray(exp);
        JSONArray first = staffManagerTest.showAll();

        staffManagerTest.init();
        staffManagerTest.addJsonObj(experimentalStaff);
        staffManagerTest.addJsonObj(experimentalStaff3);
        JSONArray second = staffManagerTest.showAll();

        assertEquals(first.toString(),second.toString());

    }

    @AfterEach
    void tearDown() throws IOException {
        StaffManager saver = new StaffManager();
        PrintWriter writer ;

        writer = new PrintWriter(saver.getFilePath());

        writer.print(saverUnit);
        writer.close();
    }
}