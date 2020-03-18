package macior.strategygame.service.game.playersControlls;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
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
}
