package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ControlUpgrades;

import com.google.gson.Gson;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades.ProductionManagers1;
import macior.strategygame.game.Utilities.BeanFactory;
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
            mainMap = BeanFactory.buildMap("balance/controlUpgrades.yaml");
            System.out.println(mainMap);
        }
        return mainMap;
    }

    @Bean
    public Minesweepers minesweepersBean(){
        return new BeanFactory<Minesweepers>().getBean(getMainMap(), Minesweepers.class);
    }

    @Bean
    public ConquerorsLand conquerorsLandBean(){
        return new BeanFactory<ConquerorsLand>().getBean(getMainMap(), ConquerorsLand.class);
    }

    @Bean
    public TreasureHaunters treasureHauntersBean(){
        return new BeanFactory<TreasureHaunters>().getBean(getMainMap(), TreasureHaunters.class);
    }

    @Bean
    public BuildingEngineers buildingEngineersBean(){
        return new BeanFactory<BuildingEngineers>().getBean(getMainMap(), BuildingEngineers.class);
    }

    @Bean
    public ScrapDrones scrapDronesBean(){
        return new BeanFactory<ScrapDrones>().getBean(getMainMap(), ScrapDrones.class);
    }

    @Bean
    public Fortress fortressBean(){
        return new BeanFactory<Fortress>().getBean(getMainMap(), Fortress.class);
    }

    @Bean
    public Pyrotechnics pyrotechnicsBean(){
        return new BeanFactory<Pyrotechnics>().getBean(getMainMap(), Pyrotechnics.class);
    }

    @Bean
    public Accuracy accuracyBean(){
        return new BeanFactory<Accuracy>().getBean(getMainMap(), Accuracy.class);
    }

    @Bean
    public Infrastructure infrastructureBean(){
        return new BeanFactory<Infrastructure>().getBean(getMainMap(), Infrastructure.class);
    }

    @Bean
    public ConquerAndProtect1 conquerAndProtect1Bean(){
        return new BeanFactory<ConquerAndProtect1>().getBean(getMainMap(), ConquerAndProtect1.class);
    }

    @Bean
    public ConquerAndProtect2 conquerAndProtect2Bean(){
        return new BeanFactory<ConquerAndProtect2>().getBean(getMainMap(), ConquerAndProtect2.class);
    }
}
