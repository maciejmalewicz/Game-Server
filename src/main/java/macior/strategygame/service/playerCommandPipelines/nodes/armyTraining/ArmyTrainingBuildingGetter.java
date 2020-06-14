package macior.strategygame.service.playerCommandPipelines.nodes.armyTraining;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.CannonFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.DroidFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.TankFactory;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.service.playerCommandPipelines.models.ArmyTrainingModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingBuildingGetter extends ChainNode<ArmyTrainingModel> {

    @Override
    public void execute(ArmyTrainingModel model, ChainExecutor executor) {
        ArmyTrainingRequest armyTrainingRequest = (ArmyTrainingRequest) model.REQUEST;

        if (armyTrainingRequest.unitType == 1){
            model.FACTORY = getFactory(model.AREA_UNIT, DroidFactory.class);
            if (model.FACTORY == null) {
                executor.stop();
                model.RESPONSE.setStatus(GameErrors.DROIDS_FACTORY_NOT_FOUND);
            }
        }

        if (armyTrainingRequest.unitType == 2){
            model.FACTORY = getFactory(model.AREA_UNIT, TankFactory.class);
            if (model.FACTORY == null) {
                executor.stop();
                model.RESPONSE.setStatus(GameErrors.TANKS_FACTORY_NOT_FOUND);
            }
        }

        if (armyTrainingRequest.unitType == 3){
            model.FACTORY = getFactory(model.AREA_UNIT, CannonFactory.class);
            if (model.FACTORY == null) {
                executor.stop();
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
        if (east != null && east.getClass() == c){
            return east;
        }

        return null;
    }


}
