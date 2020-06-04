package macior.strategygame.game.BoardManagement.Buildings.configurationBeans;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.MainTowerConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.RocketConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.TowerConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.resourceFactories.BigBuildingMaterialsFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.resourceFactories.BigElectricityFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.resourceFactories.BigMetalFactoryConfig;
import macior.strategygame.game.Utilities.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        RocketConfig config = new BeanFactory<RocketConfig>().getBean(getMainMap(), RocketConfig.class);
        return config;
    }

    @Bean
    public TowerConfig towerBean(){
        TowerConfig config = new BeanFactory<TowerConfig>().getBean(getMainMap(), TowerConfig.class);
        return config;
    }

    @Bean
    public MainTowerConfig mainTowerBean(){
        MainTowerConfig config = new BeanFactory<MainTowerConfig>().getBean(getMainMap(), MainTowerConfig.class);
        return config;
    }

    @Bean
    public BigMetalFactoryConfig metalFactoryBean(){
        BigMetalFactoryConfig config = new BeanFactory<BigMetalFactoryConfig>().getBean(getMainMap(), BigMetalFactoryConfig.class);
        return config;
    }

    @Bean
    public BigBuildingMaterialsFactoryConfig buildingMaterialsFactoryBean(){
        BigBuildingMaterialsFactoryConfig config =  new BeanFactory<BigBuildingMaterialsFactoryConfig>().getBean(getMainMap(),
                BigBuildingMaterialsFactoryConfig.class);
        return config;
    }

    @Bean
    public BigElectricityFactoryConfig electricityFactoryBean(){
        BigElectricityFactoryConfig config =  new BeanFactory<BigElectricityFactoryConfig>().getBean(getMainMap(),
                BigElectricityFactoryConfig.class);
        return config;
    }



    //todo: this thing is done, now the same for small buildings (including walls) :D
    // and configuration will be set
    //todo: next, do resources updates and connect with angular page
}
