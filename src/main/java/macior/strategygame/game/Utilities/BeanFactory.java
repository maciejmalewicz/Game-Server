package macior.strategygame.game.Utilities;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BeanFactory<T> {

    public T getBean(Map<String, Object> mainMap, Class toFind) {
        String target = toFind.getSimpleName();
        target = convertToCamel(target);
        Map<String, Object> inside = (Map) mainMap.get(target);
        JSONObject jsonObject = new JSONObject(inside);
        Gson gson = new Gson();
        Object out = gson.fromJson(jsonObject.toString(), toFind);
        return (T) out;
    }

    public static String convertToCamel(String toConvert) {
        return toConvert.substring(0, 1).toLowerCase() + toConvert.substring(1);
    }

    public static HashMap<String, Object> buildMap(String sourceYAML) {
        Yaml yaml = new Yaml();
        try {
            InputStream in = BeanFactory.class.getClassLoader()
                    .getResourceAsStream(sourceYAML);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String s;
            StringBuilder builder = new StringBuilder();
            while ((s = reader.readLine()) != null) {
                builder.append(s);
                builder.append('\n');
            }

            String yamlString = builder.toString();
            return yaml.load(yamlString);
        } catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }
    }
}


