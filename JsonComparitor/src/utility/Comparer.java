package utility;

import enums.ComparisonResult;
import objects.ComparisonObject;
import objects.JsonCompareObject;

import java.util.Map;

public class Comparer {
    /***
     * The basic comparison function of the program.
     * @param jsonCompareObject1 First Json object.
     * @param jsonCompareObject2 Second Json object.
     */
    public static void compare(JsonCompareObject jsonCompareObject1, JsonCompareObject jsonCompareObject2){
        ComparisonObject comparisonObject = new ComparisonObject(jsonCompareObject1,jsonCompareObject2);
        //Check for extra key-values.
        Map<String, Map<String,String>> extraKeyValues = comparisonObject.getExtraKeyValues();
        //Check for differences.
        Map<String,String[]> differences = comparisonObject.getDifferences();
        //If only difference is transactionId.
        if(differences.keySet().size()==1&&differences.containsKey("memberDetails.transactionId")){
            MessageDisplayer.ShowDifferences(jsonCompareObject1.getJsonName(), jsonCompareObject2.getJsonName(),differences);
            comparisonObject.setComparisonResult(ComparisonResult.ONLY_TRANSACTION_ID);
        }
        //If there are differences.
        else if (differences.keySet().size()>0){
            MessageDisplayer.ShowDifferences(jsonCompareObject1.getJsonName(), jsonCompareObject2.getJsonName(),differences);
            comparisonObject.setComparisonResult(ComparisonResult.DIFFERENT);
        }
        if(extraKeyValues.containsKey("first")){
            MessageDisplayer.ShowExtraKeys(jsonCompareObject1.getJsonName(),extraKeyValues.get("first"));
            comparisonObject.setComparisonResult(ComparisonResult.DIFFERENT);
        }
        if(extraKeyValues.containsKey("second")){
            MessageDisplayer.ShowExtraKeys(jsonCompareObject2.getJsonName(),extraKeyValues.get("second"));
            comparisonObject.setComparisonResult(ComparisonResult.DIFFERENT);
        }
        if(comparisonObject.getComparisonResult()==null){
            comparisonObject.setComparisonResult(ComparisonResult.SAME);
        }
        MessageDisplayer.ShowFinalResult(comparisonObject.getComparisonResult());
    }

}
