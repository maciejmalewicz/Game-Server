package macior.strategygame.models.game.configuration;

import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.resourceFactories.BigMetalFactory;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.MainTowerConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.RocketConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.TowerConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.resourceFactories.BigBuildingMaterialsFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.resourceFactories.BigElectricityFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.resourceFactories.BigMetalFactoryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BigBuildingsConfig {

    @Autowired
    @Qualifier("towerBean")
    private TowerConfig towerConfig;

    @Autowired
    @Qualifier("mainTowerBean")
    private MainTowerConfig mainTowerConfig;

    @Autowired
    @Qualifier("rocketBean")
    private RocketConfig rocketConfig;

    @Autowired
    @Qualifier("metalFactoryBean")
    private BigMetalFactoryConfig bigMetalFactoryConfig;

    @Autowired
    @Qualifier("buildingMaterialsFactoryBean")
    private BigBuildingMaterialsFactoryConfig bigBuildingMaterialsFactoryConfig;

    @Autowired
    @Qualifier("electricityFactoryBean")
    private BigElectricityFactoryConfig bigElectricityFactoryConfig;

    public TowerConfig getTowerConfig() {
        return towerConfig;
    }

    public MainTowerConfig getMainTowerConfig() {
        return mainTowerConfig;
    }

    public RocketConfig getRocketConfig() {
        return rocketConfig;
    }

    public BigMetalFactoryConfig getBigMetalFactoryConfig() {
        return bigMetalFactoryConfig;
    }

    public BigBuildingMaterialsFactoryConfig getBigBuildingMaterialsFactoryConfig() {
        return bigBuildingMaterialsFactoryConfig;
    }

    public BigElectricityFactoryConfig getBigElectricityFactoryConfig() {
        return bigElectricityFactoryConfig;
    }
}
