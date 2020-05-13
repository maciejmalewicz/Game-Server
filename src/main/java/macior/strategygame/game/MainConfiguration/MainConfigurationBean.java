package macior.strategygame.game.MainConfiguration;

import macior.strategygame.game.Utilities.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class MainConfigurationBean {

    private HashMap<String, Object> mainMap;

    private HashMap<String, Object> getMainMap(){
        if (mainMap == null){
            mainMap = BeanFactory.buildMap("balance/main.yaml");
            System.out.println(mainMap);
        }
        return mainMap;
    }

    @Bean
    public MainConfig mainConfigBean(){
        MainConfig config = new BeanFactory<MainConfig>().getBean(getMainMap(), MainConfig.class);
        return config;
    }

}
