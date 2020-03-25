package macior.strategygame.game.BoardManagement.Buildings.buildings;

import macior.strategygame.game.BoardManagement.AreaUnit;

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
        System.out.println();
    }

    public AreaUnit getAreaUnit() {
        return areaUnit;
    }

    public int getPlace() {
        return place;
    }

    @Override
    protected String getLabel() {
        return "UNDER CONSTRUCTION: " + buildingUnderConstruction.getLabel();
    }
}
