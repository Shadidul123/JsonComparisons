package objects;

import com.github.wnameless.json.flattener.JsonFlattener;
import enums.InputMethod;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class JsonCompareObject {
    private String jsonName;
    private JSONObject jsonObject;

    public JsonCompareObject (String jsonName, String json, InputMethod inputMethod) throws IOException {
        this.jsonName=jsonName;
        switch (inputMethod){
            case STRING -> this.jsonObject= parseFromString(json);
            case FILE -> this.jsonObject=parseFromFile(json);
        }
    }

    /***
     * Extracts JSON object from string.
     * @param s String containing the contents of json.
     * @return The flattened json object extracted from string.
     */
    public static JSONObject parseFromString(String s){
        return new JSONObject(JsonFlattener.flatten(s));
    }
    /***
     * Extracts JSON object from file.
     * @param path Path of the json file to load.
     * @return The flattened json object extracted from file.
     */
    public static JSONObject parseFromFile(String path) throws IOException {
        return new JSONObject(JsonFlattener.flatten(Files.readString(Path.of(path), StandardCharsets.US_ASCII)));
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

    public String getJsonName() {
        return jsonName;
    }

    public void setJsonName(String jsonName) {
        this.jsonName = jsonName;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
