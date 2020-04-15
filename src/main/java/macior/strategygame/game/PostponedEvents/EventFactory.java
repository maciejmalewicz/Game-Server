package macior.strategygame.game.PostponedEvents;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;

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


}
