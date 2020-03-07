package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ImprovementUpgrades;

import com.google.gson.Gson;
import macior.strategygame.game.Utilities.BeanFactory;
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
            mainMap = BeanFactory.buildMap("balance/improvementUpgrades.yaml");
            System.out.println(mainMap);
        }
        return mainMap;
    }

    @Bean
    public ProductionManagers1 productionManagersBean(){
        return new BeanFactory<ProductionManagers1>().getBean(getMainMap(), ProductionManagers1.class);
    }

    @Bean
    public ProductionManagers2 productionMangers2Bean(){
        return new BeanFactory<ProductionManagers2>().getBean(getMainMap(), ProductionManagers2.class);
    }

    @Bean
    public MiningDrill miningDrillBean(){
        return new BeanFactory<MiningDrill>().getBean(getMainMap(), MiningDrill.class);
    }

    @Bean
    public Excavator excavatorBean(){
        return new BeanFactory<Excavator>().getBean(getMainMap(), Excavator.class);
    }
    @Bean
    public AdvancedPhysics advancedPhysicsBean(){
        return new BeanFactory<AdvancedPhysics>().getBean(getMainMap(), AdvancedPhysics.class);
    }

    @Bean
    public EngineeringPatterns engineeringPatternsBean(){
        return new BeanFactory<EngineeringPatterns>().getBean(getMainMap(), EngineeringPatterns.class);
    }

    @Bean
    public SpaceManagement spaceManagementBean(){
        return new BeanFactory<SpaceManagement>().getBean(getMainMap(), SpaceManagement.class);
    }

    @Bean
    public TransportTrains transportTrainsBean(){
        return new BeanFactory<TransportTrains>().getBean(getMainMap(), TransportTrains.class);
    }

    @Bean
    public Geology geologyBean(){
        return new BeanFactory<Geology>().getBean(getMainMap(), Geology.class);
    }

    @Bean
    public Architecture architectureBean(){
        return new BeanFactory<Architecture>().getBean(getMainMap(), Architecture.class);
    }

    @Bean
    public ManagersAI managersAIBean(){
        return new BeanFactory<ManagersAI>().getBean(getMainMap(), ManagersAI.class);
    }
}
