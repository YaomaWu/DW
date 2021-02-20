package UDF;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.json.JSONException;
import org.json.JSONObject;

public class MyUDF extends UDF {
    public String evaluate(String source,String param) throws JSONException {
        if (!source.contains(param) && !param.equals("ts")) {
            return "";
        }
        String[] words = source.split("\\|");
        JSONObject root = new JSONObject(words[1]);
        if (param.equals("ts")) {
            return words[0].trim();
        }else if (param.equals("ap")) {
            return root.getString("ap");
        }else if(param.equals("et")){
            return root.getString("et");
        }else {
            return root.getJSONObject("cm").getString(param);
        }
    }

}
