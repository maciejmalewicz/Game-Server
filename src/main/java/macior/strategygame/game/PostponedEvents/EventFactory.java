package macior.strategygame.game.PostponedEvents;

import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.PostponedEvents.armyConcernedEvents.ArmyTrainingEvent;
import macior.strategygame.game.PostponedEvents.armyConcernedEvents.ArmyTransferEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConstructionEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingUpgradeEvent;

public class EventFactory {

    private byte currentId = 0;

    private byte generateId(){
        return currentId++;
    }

    public BuildingConstructionEvent generateBuildingConstructionEvent(int time, UnderConstructionBuilding building){
        BuildingConstructionEvent event = new BuildingConstructionEvent(time, building);
        event.setId(generateId());
        return event;
    }

    public BuildingUpgradeEvent generateBuildingUpgradeEvent(int time, Building upgraded, int level, AreaUnit unit, int place){
        BuildingUpgradeEvent event = new BuildingUpgradeEvent(time, upgraded, level, unit, place);
        event.setId(generateId());
        return event;
    }



    public ArmyTrainingEvent generateArmyTrainingEvent(int time, AreaUnit areaUnit, int unitType, int quantity){
        ArmyTrainingEvent event = new ArmyTrainingEvent(time, areaUnit, unitType, quantity);
        event.setId(generateId());
        return event;
    }

    public ArmyTransferEvent generateArmyTransferEvent(int time, AreaUnit sendingAreaUnit, AreaUnit targetAreaUnit, Player sender, Army army){
        ArmyTransferEvent event = new ArmyTransferEvent(time, sendingAreaUnit, targetAreaUnit, sender, army);
        event.setId(generateId());
        return event;
    }


}
