package macior.strategygame.service.chainOfResponsibility.models;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.models.game.playersControls.BuildingRequest;

public class BuildNewBuildingModel extends AreaUnitChangingModel {

    public UnderConstructionBuilding UNDER_CONSTRUCTION_BUILDING;
}
