package macior.strategygame.models.game.messages;

import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;

public class UnderConstructionBuildingMessage extends BuildingMessage {

    private BuildingMessage buildingUnderConstruction;

    public BuildingMessage getBuildingUnderConstruction() {
        return buildingUnderConstruction;
    }

    public void setBuildingUnderConstruction(BuildingMessage buildingUnderConstruction) {
        this.buildingUnderConstruction = buildingUnderConstruction;
    }
}
