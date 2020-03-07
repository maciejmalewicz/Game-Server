package macior.strategygame.game.Utilities;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.Map;

public class BeanFactory <T> {

    public T getBean(Map<String, Object> mainMap, Class toFind){
        String target = toFind.getSimpleName();
        target = convertToCamel(target);
        Map<String, Object> inside = (Map)mainMap.get(target);
        JSONObject jsonObject = new JSONObject(inside);
        Gson gson = new Gson();
        Object out = gson.fromJson(jsonObject.toString(), toFind);
        return (T)out;
    }

    public static String convertToCamel(String toConvert){
        return toConvert.substring(0, 1).toLowerCase() + toConvert.substring(1);
    }

}
