package UDTF;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyUDTF extends GenericUDTF {

    public StructObjectInspector initialize(ObjectInspector[] argOIs)
            throws UDFArgumentException {
        List<String> fieldNames = new ArrayList<>();
        fieldNames.add("event_name");
        fieldNames.add("event_json");
        List<ObjectInspector> fieldOIs = new ArrayList<>();
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames,fieldOIs);
    }

    @Override
    public void process(Object[] args) throws HiveException {
        if (args[0] == null || args.length == 0){
            return;
        }
        try {
            JSONArray array = new JSONArray(args[0].toString());
            if (array == null || array.length() == 0){
                return;
            }
                for (int i=0;i<array.length();i++){
                    try {
                        String[] result = new String[2];
                        JSONObject jsonObject = array.getJSONObject(i);
                        result[0] = jsonObject.getString("en");
                        result[1] = jsonObject.toString();
                        forward(result);
                    }catch (Exception e){
                        continue;
                    }

                    }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws HiveException {

    }
}
