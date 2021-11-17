package objects;

import enums.ComparisonResult;

import java.util.*;

public class ComparisonObject {
    private final JsonCompareObject jsonCompareObject1;
    private final JsonCompareObject jsonCompareObject2;
    private ComparisonResult comparisonResult;

    public ComparisonObject(JsonCompareObject jsonCompareObject1,JsonCompareObject jsonCompareObject2){
        this.jsonCompareObject1=jsonCompareObject1;
        this.jsonCompareObject2=jsonCompareObject2;
    }

    /***
     *
     * @return true if first json has extra keys, false if not.
     */
    private boolean firstJsonHasExtraKeys(){
        for(String s : jsonCompareObject1.getJsonObject().keySet()){
            if(!jsonCompareObject2.getJsonObject().keySet().contains(s)){
                return true;
            }
        }
        return false;
    }

    /***
     *
     * @return true if second json has extra keys, false if not.
     */
    private boolean secondJsonHasExtraKeys(){
        for(String s : jsonCompareObject2.getJsonObject().keySet()){
            if(!jsonCompareObject1.getJsonObject().keySet().contains(s)){
                return true;
            }
        }
        return false;
    }

    /***
     * Get the extra keys and values (if any) from both json (json1 might have values that json2 doesn't contain and the opposite).
     * @return Map containing the extra keys and the values.
     */
    public Map<String, Map<String,String>> getExtraKeyValues(){
        Map<String, Map<String,String>> extraKeys = new HashMap<>();
        if(firstJsonHasExtraKeys()){
            Map<String,String> keyValues = new HashMap<>();
            for(String s : jsonCompareObject1.getJsonObject().keySet()){
                if(!jsonCompareObject2.getJsonObject().keySet().contains(s)){
                    keyValues.put(s,jsonCompareObject1.getJsonObject().get(s).toString());
                }
            }
            extraKeys.put("first",keyValues);
        }
        if(secondJsonHasExtraKeys()){
            Map<String,String> keyValues = new HashMap<>();
            for(String s : jsonCompareObject2.getJsonObject().keySet()){
                if(!jsonCompareObject1.getJsonObject().keySet().contains(s)){
                    keyValues.put(s,jsonCompareObject2.getJsonObject().get(s).toString());
                }
            }
            extraKeys.put("second",keyValues);
        }
        return extraKeys;
    }
    /***
     * Get the differences between keys existing in both json.
     * @return Map containing the keys and the values of json differences.
     */
    public Map<String,String[]> getDifferences(){
        Map<String,String[]> differences = new HashMap<>();
        jsonCompareObject1.getJsonObject().keySet().forEach(key->{
            if(jsonCompareObject2.getJsonObject().has(key)){
                String json1Value = jsonCompareObject1.getJsonObject().get(key).toString();
                String json2Value = jsonCompareObject2.getJsonObject().get(key).toString();
                if(!Objects.equals(json1Value,json2Value)){
                    differences.put(key,new String[]{json1Value,json2Value});
                }
            }
        });
        return differences;
    }

    public ComparisonResult getComparisonResult() {
        return comparisonResult;
    }

    public void setComparisonResult(ComparisonResult comparisonResult) {
        this.comparisonResult = comparisonResult;
    }
}
