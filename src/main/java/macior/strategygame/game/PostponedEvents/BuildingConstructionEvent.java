package macior.strategygame.game.PostponedEvents;

import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;

public class BuildingConstructionEvent extends PostponedEvent {

    private UnderConstructionBuilding building;

    public BuildingConstructionEvent(int time, UnderConstructionBuilding building){
        super(time);
        this.building = building;
    }

    @Override
    public void happen() {
        building.unpackBuilding();
    }
}
