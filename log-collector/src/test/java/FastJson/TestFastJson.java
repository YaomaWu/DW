package FastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class TestFastJson {
    @Test
    public void testFastJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","yaoMa");
        jsonObject.put("age","18");
        jsonObject.put("gender","male");
        System.out.println(jsonObject);

    }

    @Test
    public void testFastJson1(){
        String str = "{\"gender\":\"男\",\"name\":\"吴晓东\",\"age\":\"18\"}";
        Student student = new JSON(){}.parseObject(str,Student.class);
        System.out.println(student);
    }

    @Test
    public void testJsonArray(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","yao");
        jsonObject.put("age","18");
        jsonObject.put("gender","male");

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name","Ma");
        jsonObject1.put("age","19");
        jsonObject1.put("gender","female");

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject1);

        System.out.println(jsonArray);
    }
}
