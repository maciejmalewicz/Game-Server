package macior.strategygame.game.BattlesManagement.configuration;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.RocketConfig;
import macior.strategygame.game.Utilities.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ArmyBalanceConfigurationBeans {

    private HashMap<String, Object> mainMap;

    private HashMap<String, Object> getMainMap(){
        if (mainMap == null){
            mainMap = BeanFactory.buildMap("balance/armyBalance.yaml");
            System.out.println(mainMap);
        }
        return mainMap;
    }

    @Bean
    public DroidsConfig droidsConfigBean(){
        return new BeanFactory<DroidsConfig>().getBean(getMainMap(), DroidsConfig.class);
    }

    @Bean
    public TanksConfig tanksConfigBean(){
        return new BeanFactory<TanksConfig>().getBean(getMainMap(), TanksConfig.class);
    }

    @Bean
    public CannonsConfig cannonsConfigBean(){
        return new BeanFactory<CannonsConfig>().getBean(getMainMap(), CannonsConfig.class);
    }

}
