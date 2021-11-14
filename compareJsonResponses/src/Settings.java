public class Settings {
    public final static boolean USE_FILES= true; //true to use files, false to use strings for comparison.


    //Only used when USE_FILES is set to false.
    public final static String JSONSTRING_1 = "{\"name\": \"Shad\", \"technology\": \"Python\"}";
    public final static String JSONSTRING_2 = "{\"name\": \"Shad\", \"technology\": \"Java\"}";

    //Only used when USE_FILES is set to true.
    //Location of the first json file to compare.
    public final static String FILE_LOCATION_LINKDEVELOPER_JSON = "/Users/Shad/Downloads/LegacyResponse.json";
    //Location of the second json file to compare.
    public final static String FILE_LOCATION_STARGATE_JSON= "/Users/Shad/Downloads/StargateResponse.json";
}
