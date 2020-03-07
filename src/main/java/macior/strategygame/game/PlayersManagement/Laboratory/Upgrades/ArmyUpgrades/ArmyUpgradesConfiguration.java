package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades;

import com.google.gson.Gson;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ControlUpgrades.Minesweepers;
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
public class ArmyUpgradesConfiguration {

    private HashMap<String, Object> mainMap;


    //lazy evaluation
    private HashMap<String, Object> getMainMap(){
        if (mainMap == null){
            mainMap = BeanFactory.buildMap("balance/armyUpgrades.yaml");
            System.out.println(mainMap);
        }
        return mainMap;
    }

    @Bean
    public PowerInSimplicity powerInSimplicityBean(){
        return new BeanFactory<PowerInSimplicity>().getBean(getMainMap(), PowerInSimplicity.class);
    }

    @Bean
    public AdvancedDroids advancedDroidsBean(){
        return new BeanFactory<AdvancedDroids>().getBean(getMainMap(), AdvancedDroids.class);
    }

    @Bean
    public AssemblyLines assemblyLinesBean(){
        return new BeanFactory<AssemblyLines>().getBean(getMainMap(), AssemblyLines.class);
    }

    @Bean
    public MassProduction massProductionBean(){
        return new BeanFactory<MassProduction>().getBean(getMainMap(), MassProduction.class);
    }

    @Bean
    public Tanks tanksBean(){
        return new BeanFactory<Tanks>().getBean(getMainMap(), Tanks.class);
    }

    @Bean
    public Mobility mobilityBean(){
        return new BeanFactory<Mobility>().getBean(getMainMap(), Mobility.class);
    }

    @Bean
    public ShootingWindow shootingWindowBean(){
        return new BeanFactory<ShootingWindow>().getBean(getMainMap(), ShootingWindow.class);
    }

    @Bean
    public Cannons cannonsBean(){
        return new BeanFactory<Cannons>().getBean(getMainMap(), Cannons.class);
    }

    @Bean
    public CannonPlatform cannonPlatformBean(){
        return new BeanFactory<CannonPlatform>().getBean(getMainMap(), CannonPlatform.class);
    }

    @Bean
    public BattleFormation battleFormationBean(){
        return new BeanFactory<BattleFormation>().getBean(getMainMap(), BattleFormation.class);
    }

    @Bean
    public SolarPanels solarPanelsBean(){
        return new BeanFactory<SolarPanels>().getBean(getMainMap(), SolarPanels.class);
    }
}
