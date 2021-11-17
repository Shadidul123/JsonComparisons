import enums.InputMethod;
import objects.JsonCompareObject;
import org.json.JSONObject;
import utility.Comparer;
import utility.Settings;

import java.io.IOException;
import java.util.Arrays;

public class Comparison {
    public static void main(String[] args) {
        JsonCompareObject jsonCompareObject1;
        JsonCompareObject jsonCompareObject2;
        if(Settings.USE_FILES){
            try{
                String[] pathSplit = Settings.FILE_LOCATION_1.split("/");
                String fileName1 = pathSplit[pathSplit.length-1];
                pathSplit = Settings.FILE_LOCATION_2.split("/");
                String fileName2 = pathSplit[pathSplit.length-1];
                jsonCompareObject1 = new JsonCompareObject(fileName1,Settings.FILE_LOCATION_1, InputMethod.FILE);
                jsonCompareObject2 = new JsonCompareObject(fileName2,Settings.FILE_LOCATION_2, InputMethod.FILE);
            }
            catch (IOException e){
                System.out.println("Could not load json files. Program exiting ...");
                return;
            }
        }
        else {
            try {
                jsonCompareObject1 = new JsonCompareObject("Json 1", Settings.JSONSTRING_1, InputMethod.STRING);
                jsonCompareObject2 = new JsonCompareObject("Json 2", Settings.JSONSTRING_2, InputMethod.STRING);
            }
            catch (IOException e) {
                System.out.println("Could not load json data from string. Program exiting ...");
                return;
            }
        }
        Comparer.compare(jsonCompareObject1,jsonCompareObject2);
    }
}
