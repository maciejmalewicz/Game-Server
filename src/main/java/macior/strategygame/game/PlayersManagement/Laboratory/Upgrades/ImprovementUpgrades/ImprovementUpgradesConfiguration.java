package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ImprovementUpgradesConfiguration {

    private HashMap<String, Object> mainMap;


    //lazy evaluation
    private HashMap<String, Object> getMainMap(){
        if (mainMap == null){
            Yaml yaml = new Yaml();
            try {
                InputStream in = getClass().getClassLoader().getResourceAsStream("balance/improvementUpgrades.yaml");
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
    public ProductionManagers1 productionManagersBean(){
        Map<String, Object> inside = (Map) getMainMap().get("productionManagers1");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        ProductionManagers1 out = gson.fromJson(jobject.toString(), ProductionManagers1.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public ProductionManagers2 productionMangers2Bean(){
        Map<String, Object> inside = (Map) getMainMap().get("productionManagers2");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        ProductionManagers2 out = gson.fromJson(jobject.toString(), ProductionManagers2.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public MiningDrill miningDrillBean(){
        Map<String, Object> inside = (Map) getMainMap().get("miningDrill");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        MiningDrill out = gson.fromJson(jobject.toString(), MiningDrill.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public Excavator excavatorBean(){
        Map<String, Object> inside = (Map) getMainMap().get("excavator");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        Excavator out = gson.fromJson(jobject.toString(), Excavator.class);
        System.out.println(out);
        return out;
    }
    @Bean
    public AdvancedPhysics advancedPhysicsBean(){
        Map<String, Object> inside = (Map) getMainMap().get("advancedPhysics");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        AdvancedPhysics out = gson.fromJson(jobject.toString(), AdvancedPhysics.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public EngineeringPatterns engineeringPatternsBean(){
        Map<String, Object> inside = (Map) getMainMap().get("engineeringPatterns");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        EngineeringPatterns out = gson.fromJson(jobject.toString(), EngineeringPatterns.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public SpaceManagement spaceManagementBean(){
        Map<String, Object> inside = (Map) getMainMap().get("spaceManagement");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        SpaceManagement out = gson.fromJson(jobject.toString(), SpaceManagement.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public TransportTrains transportTrainsBean(){
        Map<String, Object> inside = (Map) getMainMap().get("transportTrains");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        TransportTrains out = gson.fromJson(jobject.toString(), TransportTrains.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public Geology geologyBean(){
        Map<String, Object> inside = (Map) getMainMap().get("geology");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        Geology out = gson.fromJson(jobject.toString(), Geology.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public Architecture architectureBean(){
        Map<String, Object> inside = (Map) getMainMap().get("architecture");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        Architecture out = gson.fromJson(jobject.toString(), Architecture.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public ManagersAI managersAIBean(){
        Map<String, Object> inside = (Map) getMainMap().get("managersAI");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        ManagersAI out = gson.fromJson(jobject.toString(), ManagersAI.class);
        System.out.println(out);
        return out;
    }
}
