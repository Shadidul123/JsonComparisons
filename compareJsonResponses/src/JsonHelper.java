import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonHelper {
    /***
     * Extracts JSON object from string.
     * @param s String containing the contents of json.
     * @return The flattened json object extracted from string.
     */
    public static JSONObject parseFromString(String s){
        return flatten(new JSONObject(s),null);
    }
    /***
     * Extracts JSON object from file.
     * @param path Path of the json file to load.
     * @return The flattened json object extracted from file.
     */
    public static JSONObject parseFromFile(String path) throws IOException{
        return flatten( new JSONObject(Files.readString(Path.of(path), StandardCharsets.US_ASCII)),null);
    }
    /***
     * Return true if json values are equal, false if not.
     * @param linkDevJsonResponse First Json object.
     * @param starGateJsonResponse Second Json object.
     */
    public static void compare(JSONObject linkDevJsonResponse, JSONObject starGateJsonResponse){
        //Check if starGate response has the same keys as the link developer response.
        for(String s : linkDevJsonResponse.keySet()){
            if(!starGateJsonResponse.keySet().contains(s)){
                ShowMessage(ComparisonResult.ERROR,null);
                return;
            }
        }
        //Check if link developer response has the same keys as the stargate response
        for(String s : starGateJsonResponse.keySet()){
            if(!linkDevJsonResponse.keySet().contains(s)){
                ShowMessage(ComparisonResult.ERROR,null);
                return;
            }
        }
        //Find the differences (if any) and store them ignores Transaction ID differences
        Map<String, String[]> differences= new HashMap<>();
        for (String s: linkDevJsonResponse.keySet()){
            if(!starGateJsonResponse.get(s).toString().equals(linkDevJsonResponse.get(s).toString())){
                differences.put(s,new String[]{starGateJsonResponse.get(s).toString(), linkDevJsonResponse.get(s).toString()});
            }
        }
        if(differences.size()>0){
            if(differences.size()==1&&differences.get("transactionId")!=null){
                ShowMessage(ComparisonResult.ONLY_TRANSACTION_ID_DIFFERENT,differences);
            }
            else{
                ShowMessage(ComparisonResult.DIFFERENT,differences);
            }
        }
        else{
            ShowMessage(ComparisonResult.SAME,null);
        }
    }

    /***
     * Flattens the JSON to compare inner fields too.
     */
    public static JSONObject flatten(JSONObject object, JSONObject flattened){
        if(flattened == null){
            flattened = new JSONObject();
        }
        Iterator<?> keys = object.keys();
        while(keys.hasNext()){
            String key = (String)keys.next();
            try {
                if(object.get(key) instanceof JSONObject){
                    flatten(object.getJSONObject(key), flattened);
                } else {
                    flattened.put(key, object.get(key));
                }
            } catch(JSONException ignored){
            }
        }
        return flattened;
    }

    /***
     * Shows the appropriate message in the console after the comparison.
     * @param comparisonResult The result received from comparison.
     * @param differences The differences (if any) between the two json objects.
     */
    private static void ShowMessage(ComparisonResult comparisonResult,Map<String,String[]> differences){
        switch (comparisonResult){
            case ERROR -> System.out.println("Json objects are not identical, can't compare!");
            case SAME ->  System.out.println("The 2 json are the same.");
            case ONLY_TRANSACTION_ID_DIFFERENT -> System.out.println("The stargate response and link developer response only differ in Transaction ID values therefore both JSON values are equal.");
            case DIFFERENT -> {
                System.out.println("The 2 json objects are different and the differences are: ");
                for (String key : differences.keySet()){
                    System.out.printf("Key '%s' ",key);
                    System.out.printf("Stargate value: '%s' ",differences.get(key)[0]);
                    System.out.printf("Link Developer value: '%s'%n",differences.get(key)[1]);
                }
            }
        }

    }
}
