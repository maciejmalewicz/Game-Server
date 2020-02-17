package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades;

import com.google.gson.Gson;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ControlUpgrades.Minesweepers;
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
public class ArmyUpgradesConfiguration {

    private HashMap<String, Object> mainMap;


    //lazy evaluation
    private HashMap<String, Object> getMainMap(){
        if (mainMap == null){
            Yaml yaml = new Yaml();
            try {
                InputStream in = getClass().getClassLoader().getResourceAsStream("balance/armyUpgrades.yaml");
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
    public PowerInSimplicity powerInSimplicityBean(){
        Map<String, Object> inside = (Map) getMainMap().get("powerInSimplicity");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        PowerInSimplicity out = gson.fromJson(jobject.toString(), PowerInSimplicity.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public AdvancedDroids advancedDroidsBean(){
        Map<String, Object> inside = (Map) getMainMap().get("advancedDroids");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        AdvancedDroids out = gson.fromJson(jobject.toString(), AdvancedDroids.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public AssemblyLines assemblyLinesBean(){
        Map<String, Object> inside = (Map) getMainMap().get("assemblyLines");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        AssemblyLines out = gson.fromJson(jobject.toString(), AssemblyLines.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public MassProduction massProductionBean(){
        Map<String, Object> inside = (Map) getMainMap().get("massProduction");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        MassProduction out = gson.fromJson(jobject.toString(), MassProduction.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public Tanks tanksBean(){
        Map<String, Object> inside = (Map) getMainMap().get("tanks");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        Tanks out = gson.fromJson(jobject.toString(), Tanks.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public Mobility mobilityBean(){
        Map<String, Object> inside = (Map) getMainMap().get("mobility");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        Mobility out = gson.fromJson(jobject.toString(), Mobility.class);
        System.out.println(out);
        return out;
    }

    @Bean
    public ShootingWindow shootingWindowBean(){
        Map<String, Object> inside = (Map) getMainMap().get("shootingWindow");
        JSONObject jobject = new JSONObject(inside);

        Gson gson = new Gson();
        ShootingWindow out = gson.fromJson(jobject.toString(), ShootingWindow.class);
        System.out.println(out);
        return out;
    }
}
