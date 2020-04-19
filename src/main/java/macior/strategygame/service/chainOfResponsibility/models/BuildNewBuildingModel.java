package macior.strategygame.service.chainOfResponsibility.models;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.models.game.playersControls.BuildingRequest;

public class BuildNewBuildingModel extends PlayerChangesModel {

    public BuildingRequest REQUEST;
    public AreaUnit AREA_UNIT;
    public UnderConstructionBuilding UNDER_CONSTRUCTION_BUILDING;
}
