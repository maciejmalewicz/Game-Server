package macior.strategygame.game.BoardManagement.Buildings.buildings;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.SmallBuilding;
import macior.strategygame.game.BoardManagement.Location;

public class UnderConstructionBuilding extends Building{

    //what will be generated after building
    private Building buildingUnderConstruction;
    private AreaUnit location;
    private int place;

    public UnderConstructionBuilding(Building underConstruction,
                                     AreaUnit location, int place){
        buildingUnderConstruction = underConstruction;
        this.location = location;
        this.place = place;
    }

    public Building getBuildingUnderConstruction() {
        return buildingUnderConstruction;
    }

    public void unpackBuilding(){
        location.setBuilding(place, buildingUnderConstruction);
        System.out.println();
    }

    @Override
    protected String getLabel() {
        return "UNDER CONSTRUCTION: " + buildingUnderConstruction.getLabel();
    }
}
