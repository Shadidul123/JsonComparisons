package utility;

import enums.ComparisonResult;

import java.util.Map;

//Class for displaying messages.
public class MessageDisplayer {
    /***
     * Shows the extra keys and values that a json might have.
     * @param jsonName The json that has extra values.
     * @param extraKeyValues The extra keys and values that it has.
     */
    public static void ShowExtraKeys(String jsonName, Map<String,String> extraKeyValues){
        System.out.printf("%s has some extra keys: \n",jsonName);
        extraKeyValues.keySet().forEach(key-> {
            System.out.printf("%s : %s \n",key,extraKeyValues.get(key));
        });
        System.out.println();
    }

    /***
     * Shows the differences between existing key-values in both json.
     * @param json1Name The first json name.
     * @param json2Name The seconds json name.
     * @param differences The differences that they have.
     */
    public static void ShowDifferences(String json1Name,String json2Name, Map<String,String[]> differences){
        differences.keySet().forEach(key->{
            System.out.printf("key: %s \n",key);
            System.out.printf("%s value: %s \n",json1Name,differences.get(key)[0]);
            System.out.printf("%s value: %s \n\n",json2Name,differences.get(key)[1]);
        });
    }

    /***
     * Shows the final result of the comparison depending on param comparisonResult.
     * @param comparisonResult The result of the comparisons (See enums.ComparisonResult).
     */
    public static void ShowFinalResult(ComparisonResult comparisonResult){
        switch (comparisonResult){
            case SAME -> System.out.println("The json objects are the same.");
            case DIFFERENT -> System.out.println("The 2 json objects are different");
            case ONLY_TRANSACTION_ID -> System.out.println("The 2 json objects only differ in Transaction ID values therefore both json objects are equal.");
        }
    }
}
