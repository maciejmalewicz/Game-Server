package macior.strategygame.game.BoardManagement.Buildings.configurationBeans;

import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.resourceFactories.SmallElectricityFactory;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.ObservatoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.WallsConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.CannonFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.DroidFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.TankFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.resourceFactories.SmallBuildingMaterialsFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.resourceFactories.SmallElectricityFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.resourceFactories.SmallMetalFactoryConfig;
import macior.strategygame.game.Utilities.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class SmallBuildingsConfiguration {

    private HashMap<String, Object> mainMap;

    private HashMap<String, Object> getMainMap(){
        if (mainMap == null){
            mainMap = BeanFactory.buildMap("balance/smallBuildings.yaml");
            System.out.println(mainMap);
        }
        return mainMap;
    }

    @Bean
    public SmallMetalFactoryConfig smallMetalFactoryBean(){
        SmallMetalFactoryConfig config = new BeanFactory<SmallMetalFactoryConfig>().getBean(getMainMap(), SmallMetalFactoryConfig.class);
        return config;
    }

    @Bean
    public SmallBuildingMaterialsFactoryConfig smallBuildingMaterialsFactoryBean(){
        SmallBuildingMaterialsFactoryConfig config = new BeanFactory<SmallBuildingMaterialsFactoryConfig>().getBean(getMainMap(), SmallBuildingMaterialsFactoryConfig.class);
        return config;
    }

    @Bean
    public SmallElectricityFactoryConfig smallElectricityFactoryBean(){
        SmallElectricityFactoryConfig config = new BeanFactory<SmallElectricityFactoryConfig>().getBean(getMainMap(), SmallElectricityFactoryConfig.class);
        return config;
    }

    @Bean
    public WallsConfig wallsBean(){
        WallsConfig config = new BeanFactory<WallsConfig>().getBean(getMainMap(), WallsConfig.class);
        return config;
    }

    @Bean
    public ObservatoryConfig observatoryBean(){
        ObservatoryConfig config = new BeanFactory<ObservatoryConfig>().getBean(getMainMap(), ObservatoryConfig.class);
        return config;
    }

    @Bean
    public DroidFactoryConfig droidFactoryBean(){
        DroidFactoryConfig config = new BeanFactory<DroidFactoryConfig>().getBean(getMainMap(), DroidFactoryConfig.class);
        return config;
    }


    @Bean
    public TankFactoryConfig tankFactoryBean(){
        TankFactoryConfig config = new BeanFactory<TankFactoryConfig>().getBean(getMainMap(), TankFactoryConfig.class);
        return config;
    }

    @Bean
    public CannonFactoryConfig cannonFactoryBean(){
        CannonFactoryConfig c = new BeanFactory<CannonFactoryConfig>().getBean(getMainMap(), CannonFactoryConfig.class);
        return c;
    }



}
