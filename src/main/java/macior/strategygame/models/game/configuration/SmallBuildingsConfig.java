package macior.strategygame.models.game.configuration;

import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.TankFactory;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.ObservatoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.WallsConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.CannonFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.DroidFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.TankFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.resourceFactories.SmallBuildingMaterialsFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.resourceFactories.SmallElectricityFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.resourceFactories.SmallMetalFactoryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SmallBuildingsConfig {

    @Autowired
    @Qualifier("smallMetalFactoryBean")
    private SmallMetalFactoryConfig smallMetalFactoryConfig;

    @Autowired
    @Qualifier("smallBuildingMaterialsFactoryBean")
    private SmallBuildingMaterialsFactoryConfig smallBuildingMaterialsFactoryConfig;

    @Autowired
    @Qualifier("smallElectricityFactoryBean")
    private SmallElectricityFactoryConfig electricityFactoryConfig;

    @Autowired
    @Qualifier("wallsBean")
    private WallsConfig wallsConfig;

    @Autowired
    @Qualifier("observatoryBean")
    private ObservatoryConfig observatoryConfig;

    @Autowired
    @Qualifier("droidFactoryBean")
    private DroidFactoryConfig droidFactoryConfig;

    @Autowired
    @Qualifier("tankFactoryBean")
    private TankFactoryConfig tankFactoryConfig;

    @Autowired
    @Qualifier("cannonFactoryBean")
    private CannonFactoryConfig cannonFactoryConfig;

    public SmallMetalFactoryConfig getSmallMetalFactoryConfig() {
        return smallMetalFactoryConfig;
    }

    public SmallBuildingMaterialsFactoryConfig getSmallBuildingMaterialsFactoryConfig() {
        return smallBuildingMaterialsFactoryConfig;
    }

    public SmallElectricityFactoryConfig getElectricityFactoryConfig() {
        return electricityFactoryConfig;
    }

    public WallsConfig getWallsConfig() {
        return wallsConfig;
    }

    public ObservatoryConfig getObservatoryConfig() {
        return observatoryConfig;
    }

    public DroidFactoryConfig getDroidFactoryConfig() {
        return droidFactoryConfig;
    }

    public TankFactoryConfig getTankFactoryConfig() {
        return tankFactoryConfig;
    }

    public CannonFactoryConfig getCannonFactoryConfig() {
        return cannonFactoryConfig;
    }
}
