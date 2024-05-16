package MountainsProject;



public class Mountain {
    private String country;
    private String type;
    private String name;
    private Double latitude;
    private Double longitude;
    private int elevation;
    private String unit;
    
    //Mountain contructor that throws an error if a value is wrong
    public Mountain(String line) {
        //split line into array
        String[] lineArray = line.split("\t");

        //sets variables with array
        country = lineArray[0];
        type = lineArray[1];
        name = lineArray[2];
        latitude = Double.parseDouble(lineArray[3]);
        longitude = Double.parseDouble(lineArray[4]);


        //Removes whitespace to find the suffix of the elevation
        //Sets unit to that suffix or m if none
        lineArray[5] = lineArray[5].replaceAll("\\s","");
        if (lineArray[5].endsWith("ft")){
            unit = "ft";
        } else if (lineArray[5].endsWith("m") || lineArray[5].endsWith("0") || lineArray[5].endsWith("1") || 
                    lineArray[5].endsWith("2") || lineArray[5].endsWith("3") || lineArray[5].endsWith("4") || 
                    lineArray[5].endsWith("5") || lineArray[5].endsWith("6") || lineArray[5].endsWith("7") || lineArray[5].endsWith("8") || lineArray[5].endsWith("9")){
            unit = "m";
        } else {
            unit = "wrong";
        }
        elevation = Integer.parseInt(lineArray[5].substring(0, lineArray[5].length() - 1));

        //Checks if values are in the correct ranges, throws exception if not
        if (latitude > 90 || latitude < -90){
            throw new IllegalArgumentException("Latitude is Bad");
        }
        if (longitude > 180 || longitude < -180){
            throw new IllegalArgumentException("Longitude is bad");
        }
        if (elevation < 0) {
            throw new IllegalArgumentException("elevation negative");
        }
        if (!unit.equals("m") && !unit.equals("ft") && !unit.isEmpty()) {
            throw new IllegalArgumentException("bad unit");
        }
    }

    //Getters
    public int GetElevation() {
        return elevation;
    }
    public String GetName() {
        return name;
    }
    public String GetUnit() {
        return unit;
    }

    
}
    
