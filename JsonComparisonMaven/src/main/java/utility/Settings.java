package utility;

public class Settings {
    public final static boolean USE_FILES= true; //true to use files, false to use strings for comparison.


    //Only used when USE_FILES is set to false.
    public final static String JSONSTRING_1 = "{\"name\": \"Sam Smith\", \"technology\": \"Python\"}";
    public final static String JSONSTRING_2 = "{\"name\": \"Sam Smith1\", \"technology\": \"Python\"}";

    //Only used when USE_FILES is set to true.
    //Location of the first json file to compare.
    public final static String FILE_LOCATION_1 = "/Users/shad/Downloads/updatedLegacy.json";
    //Location of the second json file to compare.
    public final static String FILE_LOCATION_2 = "/Users/shad/Downloads/updatedStargate.json";
}
