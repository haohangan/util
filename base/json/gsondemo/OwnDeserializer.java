package demo;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnDeserializer implements JsonDeserializer<MyJson> {

    @Override
    public MyJson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if(json.isJsonNull()){
            return null;
        }
        JsonObject jsonObject = json.getAsJsonObject();
        /*jsonObject.entrySet().forEach(c->{
            System.out.println(c.getKey()+" "+c.getValue());
        });*/
        String session = get(jsonObject,"session");
        String result = get(jsonObject,"result");
        Integer code = getInt(jsonObject,"code");
        List<HashMap<String,Object>> alerts  = getArr(jsonObject,"alerts");
        MyJson mj = new MyJson.MyJsonBuilder().code(code).result(result).session(session).alerts(alerts).build();
        return mj;
    }

    String get(JsonObject jsonObject,String key){
        JsonElement je = jsonObject.get(key);
        if(je!=null){
            return je.getAsString();
        }
        return null;
    }

    Integer getInt(JsonObject jsonObject,String key){
        JsonElement je = jsonObject.get(key);
        if(je!=null){
            return je.getAsInt();
        }
        return null;
    }


    Object getObj(JsonObject jsonObject,String key){
        JsonElement je = jsonObject.get(key);
        if(je!=null){
            return je.getAsInt();
        }
        return null;
    }

//    <T> T getT(JsonObject jsonObject,String key,Class<T> clazz) throws IllegalAccessException, InstantiationException {
//        JsonElement je = jsonObject.get(key);
//        if(je!=null){
//            if(clazz==Integer.class || clazz == int.class){
////                return (T)je.getAsInt();
//            }
//        }
//        return null;
//    }

    List<HashMap<String,Object>> getArr(JsonObject jsonObject,String key){
        List<HashMap<String,Object>> list = new ArrayList<>();
        JsonArray ja = jsonObject.getAsJsonArray(key);
        ja.forEach(c->{
            HashMap<String,Object> map = new HashMap<>();
            c.getAsJsonObject().entrySet().forEach(c1->{
                map.put(c1.getKey(), c1.getValue());
            });
            list.add(map);
        });
        return list;
    }

}
