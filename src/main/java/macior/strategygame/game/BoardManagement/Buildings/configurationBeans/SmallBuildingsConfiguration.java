package macior.strategygame.game.BoardManagement.Buildings.configurationBeans;

import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.resourceFactories.SmallElectricityFactory;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.ObservatoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.WallsConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.DroidFactoryConfig;
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
        return new BeanFactory<SmallMetalFactoryConfig>().getBean(getMainMap(), SmallMetalFactoryConfig.class);
    }

    @Bean
    public SmallBuildingMaterialsFactoryConfig smallBuildingMaterialsFactoryBean(){
        return new BeanFactory<SmallBuildingMaterialsFactoryConfig>().getBean(getMainMap(), SmallBuildingMaterialsFactoryConfig.class);
    }

    @Bean
    public SmallElectricityFactoryConfig smallElectricityFactoryBean(){
        return new BeanFactory<SmallElectricityFactoryConfig>().getBean(getMainMap(), SmallElectricityFactoryConfig.class);
    }

    @Bean
    public WallsConfig wallsBean(){
        return new BeanFactory<WallsConfig>().getBean(getMainMap(), WallsConfig.class);
    }

    @Bean
    public ObservatoryConfig observatoryBean(){
        return new BeanFactory<ObservatoryConfig>().getBean(getMainMap(), ObservatoryConfig.class);
    }

    @Bean
    public DroidFactoryConfig droidFactoryBean(){
        DroidFactoryConfig c = new BeanFactory<DroidFactoryConfig>().getBean(getMainMap(), DroidFactoryConfig.class);
        return c;
    } //todo 2 other factories

}
