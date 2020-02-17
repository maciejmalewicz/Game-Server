package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ControlUpgrades;

import com.google.gson.Gson;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades.ProductionManagers1;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ControlUpgradesConfiguration {

    private HashMap<String, Object> mainMap;


    //lazy evaluation
    private HashMap<String, Object> getMainMap(){
        if (mainMap == null){
            Yaml yaml = new Yaml();
            try {
                InputStream in = getClass().getClassLoader().getResourceAsStream("balance/controlUpgrades.yaml");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String s;
                StringBuilder builder = new StringBuilder();
                while ((s = reader.readLine()) != null) {
                    builder.append(s);
                    builder.append('\n');
                }

                String yamlString = builder.toString();
                mainMap = yaml.load(yamlString);

                System.out.println(mainMap);
            } catch (Exception exc){
                exc.printStackTrace();
            }
        }

        return mainMap;
    }

    @Bean
    public Minesweepers minesweepersBean(){
        Map<String, Object> inside = (Map) getMainMap().get("minesweepers");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        Minesweepers out = gson.fromJson(jobject.toString(), Minesweepers.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public ConquerorsLand conquerorsLandBean(){
        Map<String, Object> inside = (Map) getMainMap().get("conquerorsLand");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        ConquerorsLand out = gson.fromJson(jobject.toString(), ConquerorsLand.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public TreasureHaunters treasureHauntersBean(){
        Map<String, Object> inside = (Map) getMainMap().get("treasureHaunters");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        TreasureHaunters out = gson.fromJson(jobject.toString(), TreasureHaunters.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public BuildingEngineers buildingEngineersBean(){
        Map<String, Object> inside = (Map) getMainMap().get("buildingEngineers");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        BuildingEngineers out = gson.fromJson(jobject.toString(), BuildingEngineers.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public ScrapDrones scrapDronesBean(){
        Map<String, Object> inside = (Map) getMainMap().get("scrapDrones");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        ScrapDrones out = gson.fromJson(jobject.toString(), ScrapDrones.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public Fortress fortressBean(){
        Map<String, Object> inside = (Map) getMainMap().get("fortress");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        Fortress out = gson.fromJson(jobject.toString(), Fortress.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public Pyrotechnics pyrotechnicsBean(){
        Map<String, Object> inside = (Map) getMainMap().get("pyrotechnics");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        Pyrotechnics out = gson.fromJson(jobject.toString(), Pyrotechnics.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public Accuracy accuracyBean(){
        Map<String, Object> inside = (Map) getMainMap().get("accuracy");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        Accuracy out = gson.fromJson(jobject.toString(), Accuracy.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public Infrastructure infrastructureBean(){
        Map<String, Object> inside = (Map) getMainMap().get("infrastructure");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        Infrastructure out = gson.fromJson(jobject.toString(), Infrastructure.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public ConquerAndProtect1 conquerAndProtect1Bean(){
        Map<String, Object> inside = (Map) getMainMap().get("conquerAndProtect1");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        ConquerAndProtect1 out = gson.fromJson(jobject.toString(), ConquerAndProtect1.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public ConquerAndProtect2 conquerAndProtect2Bean(){
        Map<String, Object> inside = (Map) getMainMap().get("conquerAndProtect2");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        ConquerAndProtect2 out = gson.fromJson(jobject.toString(), ConquerAndProtect2.class);
        System.out.println(out);
        return out;
    }
}
