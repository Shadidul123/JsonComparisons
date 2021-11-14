import org.json.JSONObject;

import java.io.IOException;

public class Comparison {
    public static void main(String[] args) {
        JSONObject linkDeveloperJson;
        JSONObject stargateJson;
        if(Settings.USE_FILES){
            try{
                linkDeveloperJson= JsonHelper.parseFromFile(Settings.FILE_LOCATION_LINKDEVELOPER_JSON);
                stargateJson= JsonHelper.parseFromFile(Settings.FILE_LOCATION_STARGATE_JSON);
            }
            catch (IOException e){
                System.out.println("Could not load json files. Program exiting ...");
                return;
            }
        }
        else {
            linkDeveloperJson = JsonHelper.parseFromString(Settings.JSONSTRING_1);
            stargateJson = JsonHelper.parseFromString(Settings.JSONSTRING_2);
        }
        JsonHelper.compare(linkDeveloperJson,stargateJson);
    }
}
