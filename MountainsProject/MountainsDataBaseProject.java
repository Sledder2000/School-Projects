package MountainsProject;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



/**
 * Mountains Project
 * @author Benji Lelivelt
 * 9/20/23
 */
public class MountainsDataBaseProject {

    
    public static void main(String[] args) throws Exception {
        
        //Create max and min elevation variables
        int maxEL = 0;
        String maxELName = "";
        String maxELUnit = "";
        int minEL = 100000;
        String minELName = "";
        String minELUnit = "";

        //Create bufferedWriter and readers connected to thier respective files
        Path ePath = Paths.get("MountainsProject/Files/mountains_err.tsv");
        BufferedWriter ewb = Files.newBufferedWriter(
            ePath,
            StandardCharsets.UTF_8);

        Path cPath = Paths.get("MountainsProject/Files/mountains_clean.tsv");
        BufferedWriter cwb = Files.newBufferedWriter(
            cPath,
            StandardCharsets.UTF_8);

        Path dbPath = Paths.get("MountainsProject/Files/mountains_db.tsv");
        System.out.println(dbPath.toAbsolutePath());
        BufferedReader br = Files.newBufferedReader(
            dbPath,
            StandardCharsets.UTF_8);

            //Writes first line
            ewb.write("Country    Type    Name    Latitude    Longitude    Elevation    Error");
            ewb.newLine();
            cwb.write("Country    Type    Name    Latitude    Longitude    Elevation");
            cwb.newLine();

        //while there is a next line, takes that line and try's to create a mountain with it
        //if the mountain throws an exception, writes to the error file with the exception
        //if there is no exception, writes to the good file and updates max and min elevation variables 
        while(br.ready()) {;
            String line = br.readLine();
            if (!line.substring(1, 8).equals("Country")) {
                try {
                    Exception e = null;
                    Mountain m = new Mountain(line);
                    if (m.GetElevation() > maxEL) {
                        maxEL = m.GetElevation();
                        maxELName = m.GetName();
                        maxELUnit = m.GetUnit();
                    }
                    if (m.GetElevation() < minEL) {
                        minEL = m.GetElevation();
                        minELName = m.GetName();
                        minELUnit = m.GetUnit();
                    }

                    writeFile(e, line, "good", cwb, ewb);
                } catch (Exception e) {
                    writeFile(e, line,"bad",  cwb, ewb);
                }
            }
        }

        //gets number of lines in each file
        Path cleanPath = Paths.get("MountainsProject/Files/mountains_clean.tsv");
        long cleanCount = Files.lines(cleanPath).count();

        Path errorPath = Paths.get("MountainsProject/Files/mountains_err.tsv");
        long errorCount = Files.lines(errorPath).count();

        //Print statements
        System.out.println("Clean: " + cleanCount);
        System.out.println("---------");
        System.out.println("Errors: " + errorCount);
        System.out.println(maxELName + ": " + maxEL + " " + maxELUnit);
        System.out.println(minELName + ": " + minEL + " " + minELUnit);

        br.close();
        cwb.close();
        ewb.close();
    }    


    
    /**
     * Writes to clean file if except is "good" and error file if anything else
     * @param e exception
     * @param line line in the original file
     * @param except is an exception
     * @param cwb clean bufferedwriter
     * @param ewb error bufferedwriter
     * @throws Exception
     */
    public static void writeFile(Exception e, String line, String except, BufferedWriter cwb, BufferedWriter ewb) throws Exception {
        
        if (except.equals("good")) {
            System.out.println(line);
            cwb.write(line +"\n");
            
        } else {
            ewb.write(line + "\t" + e.toString() + "\n");
        }
        
    }
}