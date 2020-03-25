package macior.strategygame.game.BoardManagement;

import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.BigBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.SmallBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.Walls;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.models.game.AreaUnitMessage;
import macior.strategygame.models.game.OwnedAreaUnitMessage;
import macior.strategygame.models.game.WallsMessage;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AreaUnitConverter {

    public AreaUnitMessage convert(Player requestor, AreaUnit unit){

        if (requestor == unit.getOwner()){ //reveal more info if owner asks
            return convertOwnedAreaUnit(unit);
        } else {
            return convertOtherAreaUnit(unit);
        }
    }

    public AreaUnitMessage convertOwnedAreaUnit(AreaUnit unit){
        OwnedAreaUnitMessage out = new OwnedAreaUnitMessage();
        Building bigBuilding = unit.getBigBuilding();
        if (bigBuilding != null){
            out.MAIN_BUILDING = bigBuilding.toMessage();
        }

        Building northBuilding = unit.getNorthBuilding();
        if (northBuilding != null){
            out.NORTH_BUILDING = northBuilding.toMessage();
        }

        Building southBuilding = unit.getSouthBuilding();
        if (southBuilding != null){
            out.SOUTH_BUILDING = southBuilding.toMessage();
        }

        Building westBuilding = unit.getWestBuilding();
        if (westBuilding != null){
            out.WEST_BUILDING = westBuilding.toMessage();
        }

        Building eastBuilding = unit.getEastBuilding();
        if (eastBuilding != null){
            out.EAST_BUILDING = eastBuilding.toMessage();
        }

        Building walls = unit.getWalls();
        if (walls != null){
            out.WALLS = (WallsMessage) walls.toMessage();
        }

        Player owner = unit.getOwner();
        if (owner != null){
            out.OWNER = owner.getNick();
        }

        out.BUILDING_QUEUE = unit.getBuildingQueue();

        return out;
    }

    public AreaUnitMessage convertOtherAreaUnit(AreaUnit unit){
        AreaUnitMessage out = new AreaUnitMessage();
        Building bigBuilding = unit.getBigBuilding();
        if (bigBuilding != null){
            out.MAIN_BUILDING = unit.getBigBuilding().toMessage();
        }
        Player owner = unit.getOwner();
        if (owner != null){
            out.OWNER = owner.getNick();
        }
        return out;
    }
}
