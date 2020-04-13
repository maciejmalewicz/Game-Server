package macior.strategygame.service.game.playersControlls;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.BigBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.MainTower;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.Rocket;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.Tower;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.resourceFactories.BigBuildingMaterialsFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.resourceFactories.BigElectricityFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.resourceFactories.BigMetalFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.Observatory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.SmallBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.CannonFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.DroidFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.TankFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.resourceFactories.SmallBuildingMaterialsFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.resourceFactories.SmallElectricityFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.resourceFactories.SmallMetalFactory;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.models.game.configuration.BigBuildingsConfig;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.configuration.SmallBuildingsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingsPlacesMapperService {

    @Autowired
    private GameConfiguration configuration;

    public Building getBuilding(AreaUnit unit, int place){
        switch(place){
            case 1:
                return unit.getBigBuilding();
            case 2:
                return unit.getNorthBuilding();
            case 3:
                return unit.getSouthBuilding();
            case 4:
                return unit.getWestBuilding();
            case 5:
                return unit.getEastBuilding();
            default:
                return null;
        }
    }

    public SmallBuilding getSmallBuilding(int building){
        switch (building){
            case 1:
                return new SmallMetalFactory();
            case 2:
                return new SmallBuildingMaterialsFactory();
            case 3:
                return new SmallElectricityFactory();
            case 4:
                return new Observatory();
            case 5:
                return new DroidFactory();
            case 6:
                return new TankFactory();
            case 7:
                return new CannonFactory();
        }
        return null;
    }

    public BuildingConfig getConfiguration(int building){
        SmallBuildingsConfig config = configuration.getSmallBuildingsConfig();
        switch (building){
            case 1:
                return config.getSmallMetalFactoryConfig();
            case 2:
                return config.getSmallBuildingMaterialsFactoryConfig();
            case 3:
                return config.getSmallElectricityFactoryConfig();
            case 4:
                return config.getObservatoryConfig();
            case 5:
                return config.getDroidFactoryConfig();
            case 6:
                return config.getTankFactoryConfig();
            case 7:
                return config.getCannonFactoryConfig();
            default:
                return null;
        }
    }

    public BuildingConfig getConfiguration(Building building){
        if (building.getClass() == UnderConstructionBuilding.class){
            UnderConstructionBuilding underConstruction = (UnderConstructionBuilding)building;
            return getConfiguration(underConstruction.getBuildingUnderConstruction());
        }
        if (building instanceof SmallBuilding){
            return getSmallBuildingsConfiguration(building);
        }
        if (building instanceof BigBuilding){
            return getBigBuildingsConfiguration(building);
        }
        return null;
    }

    private BuildingConfig getSmallBuildingsConfiguration(Building building){
        SmallBuildingsConfig config = configuration.getSmallBuildingsConfig();
        if (building.getClass() == SmallMetalFactory.class){
            return config.getSmallMetalFactoryConfig();
        }
        if (building.getClass() == SmallBuildingMaterialsFactory.class){
            return config.getSmallBuildingMaterialsFactoryConfig();
        }
        if (building.getClass() == SmallElectricityFactory.class){
            return config.getSmallElectricityFactoryConfig();
        }
        if (building.getClass() == Observatory.class){
            return config.getObservatoryConfig();
        }
        if (building.getClass() == DroidFactory.class){
            return config.getDroidFactoryConfig();
        }
        if (building.getClass() == TankFactory.class){
            return config.getTankFactoryConfig();
        }
        if (building.getClass() == CannonFactory.class){
            return config.getCannonFactoryConfig();
        }
        return null;
    }

    private BuildingConfig getBigBuildingsConfiguration(Building building){
        BigBuildingsConfig config = configuration.getBigBuildingsConfig();
        if (building.getClass() == BigMetalFactory.class){
            return config.getBigMetalFactoryConfig();
        }
        if (building.getClass() == BigBuildingMaterialsFactory.class){
            return config.getBigBuildingMaterialsFactoryConfig();
        }
        if (building.getClass() == BigElectricityFactory.class){
            return config.getBigElectricityFactoryConfig();
        }
        if (building.getClass() == Rocket.class){
            return config.getRocketConfig();
        }
        if (building.getClass() == Tower.class){
            return config.getTowerConfig();
        }
        if (building.getClass() == MainTower.class){
            return config.getMainTowerConfig();
        }
        return null;
    }
}
