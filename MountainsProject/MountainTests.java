package MountainsProject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class MountainTests {
    
    private static final Exception IOException = null;
    String goodLine = "Washington\tMountain\tRainier\t40.23947\t69.9399918\t2000 m";
    String badLatitude = "Washington\tMountain\tRainier\t400.23947\t69.9399918\t2000 m";
    String badLongitude = "Washington\tMountain\tRainier\t40.23947\t1000.32948723\t2000 m";
    String badElevation = "Washington\tMountain\tRainier\t40.23947\t69.9399918\t-40 m";
    String badUnit = "Washington\tMountain\tRainier\t40.23947\t69.9399918\t2000 dfhkjs";
    Mountain testMountain = new Mountain(goodLine);
    private static MountainsDataBaseProject http = new MountainsDataBaseProject();


    

    @Test
    public void testMountainException() {
        try {
            Mountain goodMountain = new Mountain(goodLine);
        } catch(Exception e) {
            fail("Shouldn't Fail");
        }
        try {
            Mountain badMountainLat = new Mountain(badLatitude);
            fail("Shouldn't Pass Latitude");
        } catch(Exception e) {
        }
        try {
            Mountain badMountainLong = new Mountain(badLongitude);
            fail("Shouldn't Pass Longitude");
        } catch(Exception e) {
        }
        try {
            Mountain badMountainEle = new Mountain(badElevation);
            fail("Shouldn't Pass Elevation");
        } catch(Exception e) {
        }
        try {
            Mountain badMountainUnit = new Mountain(badUnit);
            fail("Shouldn't Pass Unit");
        } catch(Exception e) {
        }
    }

    @Test
    public void testWriteFile() throws Exception {
           
        Path cPath = Paths.get("MountainsProject/Files/mountains_test.tsv");
        BufferedWriter cwb = Files.newBufferedWriter(
        cPath,
        StandardCharsets.UTF_8);

        Path testPath = Paths.get("MountainsProject/Files/mountains_test.tsv");
        BufferedReader br = Files.newBufferedReader(
        testPath,
        StandardCharsets.UTF_8);

        MountainsDataBaseProject.writeFile(IOException, goodLine, "good", cwb, cwb);
        assertEquals(goodLine, br.readLine());
    }
}
