package macior.strategygame.game.BoardManagement;

import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConcernedEvent;
import macior.strategygame.models.game.messages.AreaUnitMessage;
import macior.strategygame.models.game.messages.BuildingQueueMessage;
import macior.strategygame.models.game.messages.OwnedAreaUnitMessage;
import macior.strategygame.models.game.messages.WallsMessage;
import org.springframework.stereotype.Component;

import java.util.Collections;

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

        out.ARMY = unit.getArmy();

        out.BUILDING_QUEUE = new BuildingQueueMessage();
        for (BuildingConcernedEvent event: unit.getBuildingQueue().getEvents()){
            int place = unit.getPlace(event.getBuilding());
            out.BUILDING_QUEUE.add(event.toMessage(place));
        }
        Collections.sort(out.BUILDING_QUEUE.getEvents());

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
