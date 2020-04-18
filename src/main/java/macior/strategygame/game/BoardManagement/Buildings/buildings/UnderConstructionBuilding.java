package macior.strategygame.game.BoardManagement.Buildings.buildings;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.models.game.messages.BuildingMessage;
import macior.strategygame.models.game.messages.UnderConstructionBuildingMessage;

public class UnderConstructionBuilding extends Building{

    //what will be generated after building
    private Building buildingUnderConstruction;
    private AreaUnit areaUnit;
    private int place;

    public UnderConstructionBuilding(Building underConstruction,
                                     AreaUnit areaUnit, int place){
        buildingUnderConstruction = underConstruction;
        this.areaUnit = areaUnit;
        this.place = place;
    }

    public Building getBuildingUnderConstruction() {
        return buildingUnderConstruction;
    }

    public void unpackBuilding(){
        areaUnit.setBuilding(place, buildingUnderConstruction);
    }

    public AreaUnit getAreaUnit() {
        return areaUnit;
    }

    public int getPlace() {
        return place;
    }

    @Override
    public BuildingMessage toMessage(){
        UnderConstructionBuildingMessage message = new UnderConstructionBuildingMessage();
        message.LABEL = getLabel();
        message.LEVEL = this.LEVEL;
        message.setBuildingUnderConstruction(buildingUnderConstruction.toMessage());
        return message;
    }

    @Override
    protected String getLabel() {
        return "UNDER_CONSTRUCTION_BUILDING";
    }
}
