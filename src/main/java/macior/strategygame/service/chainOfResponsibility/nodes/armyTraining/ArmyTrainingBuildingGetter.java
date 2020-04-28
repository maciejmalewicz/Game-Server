package macior.strategygame.service.chainOfResponsibility.nodes.armyTraining;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.CannonFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.DroidFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.TankFactory;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.service.chainOfResponsibility.models.ArmyTrainingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingBuildingGetter extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTrainingModel trainingModel = (ArmyTrainingModel)model;
        ArmyTrainingRequest armyTrainingRequest = (ArmyTrainingRequest) trainingModel.REQUEST;

        if (armyTrainingRequest.unitType == 1){
            trainingModel.FACTORY = getFactory(trainingModel.AREA_UNIT, DroidFactory.class);
            if (trainingModel.FACTORY == null) {
                model.RESPONSE.setStatus(GameErrors.DROIDS_FACTORY_NOT_FOUND);
            }
        }

        if (armyTrainingRequest.unitType == 2){
            trainingModel.FACTORY = getFactory(trainingModel.AREA_UNIT, TankFactory.class);
            if (trainingModel.FACTORY == null) {
                model.RESPONSE.setStatus(GameErrors.TANKS_FACTORY_NOT_FOUND);
            }
        }

        if (armyTrainingRequest.unitType == 3){
            trainingModel.FACTORY = getFactory(trainingModel.AREA_UNIT, CannonFactory.class);
            if (trainingModel.FACTORY == null) {
                model.RESPONSE.setStatus(GameErrors.CANNONS_FACTORY_NOT_FOUND);
            }
        }
    }

    private Building getFactory(AreaUnit areaUnit, Class c){
        Building north = areaUnit.getNorthBuilding();
        if (north != null && north.getClass() == c){
            return north;
        }

        Building south = areaUnit.getSouthBuilding();
        if (south != null && south.getClass() == c){
            return south;
        }

        Building west = areaUnit.getWestBuilding();
        if (west != null && west.getClass() == c){
            return west;
        }

        Building east = areaUnit.getEastBuilding();
        if (west != null && east.getClass() == c){
            return east;
        }

        return null;
    }
}
