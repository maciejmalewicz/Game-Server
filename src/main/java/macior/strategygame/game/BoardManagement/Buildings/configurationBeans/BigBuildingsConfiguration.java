package macior.strategygame.game.BoardManagement.Buildings.configurationBeans;

import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.Rocket;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.resourceFactories.BigElectricityFactory;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.MainTowerConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.RocketConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.TowerConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.resourceFactories.BigBuildingMaterialsFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.resourceFactories.BigElectricityFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.resourceFactories.BigMetalFactoryConfig;
import macior.strategygame.game.Utilities.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

@Configuration
public class BigBuildingsConfiguration {

    private HashMap<String, Object> mainMap;

    private HashMap<String, Object> getMainMap(){
        if (mainMap == null){
            mainMap = BeanFactory.buildMap("balance/bigBuildings.yaml");
            System.out.println(mainMap);
        }
        return mainMap;
    }

    @Bean
    public RocketConfig rocketBean(){
        return new BeanFactory<RocketConfig>().getBean(getMainMap(), RocketConfig.class);
    }

    @Bean
    public TowerConfig towerBean(){
        return new BeanFactory<TowerConfig>().getBean(getMainMap(), TowerConfig.class);
    }

    @Bean
    public MainTowerConfig mainTowerBean(){
        return new BeanFactory<MainTowerConfig>().getBean(getMainMap(), MainTowerConfig.class);
    }

    @Bean
    public BigMetalFactoryConfig metalFactoryBean(){
        return new BeanFactory<BigMetalFactoryConfig>().getBean(getMainMap(), BigMetalFactoryConfig.class);
    }

    @Bean
    public BigBuildingMaterialsFactoryConfig buildingMaterialsFactoryBean(){
        return new BeanFactory<BigBuildingMaterialsFactoryConfig>().getBean(getMainMap(),
                BigBuildingMaterialsFactoryConfig.class);
    }

    @Bean
    public BigElectricityFactoryConfig electricityFactoryBean(){
        return new BeanFactory<BigElectricityFactoryConfig>().getBean(getMainMap(),
                BigElectricityFactoryConfig.class);
    }

    //todo: this thing is done, now the same for small buildings :D and configuration will be set
    //todo: next, do resources updates and connect with angular page
}
