package macior.strategygame.game.BoardManagement;

import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.PostponedEvents.PostponedEvent;
import macior.strategygame.game.PostponedEvents.armyConcernedEvents.ArmyTrainingEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConcernedEvent;
import macior.strategygame.models.game.messages.AreaUnitMessage;
import macior.strategygame.models.game.messages.AreaEventsMessage;
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

        out.AREA_EVENTS = new AreaEventsMessage();
        for (PostponedEvent event: unit.getEventsQueue().getEvents()){

            if (event instanceof BuildingConcernedEvent){
                BuildingConcernedEvent buildingEvent = (BuildingConcernedEvent)event;
                int place = unit.getPlace(buildingEvent.getBuilding());
                out.AREA_EVENTS.add(buildingEvent.toMessage(place));

            } else if (event instanceof ArmyTrainingEvent) {
                ArmyTrainingEvent trainingEvent = (ArmyTrainingEvent)event;
                out.AREA_EVENTS.add(trainingEvent.toMessage());
            }

        }
        Collections.sort(out.AREA_EVENTS.getEvents());

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
