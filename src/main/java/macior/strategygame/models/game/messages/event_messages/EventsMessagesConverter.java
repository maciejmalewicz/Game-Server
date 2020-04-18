package macior.strategygame.models.game.messages.event_messages;

import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConstructionEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingUpgradeEvent;
import macior.strategygame.models.game.messages.BuildingMessage;

public class EventsMessagesConverter {

    //need info about what and where
    public BuildingConcernedEventMessage convert(BuildingConstructionEvent event, int place){
        BuildingConcernedEventMessage message = new BuildingConcernedEventMessage();
        message.setFinishingTime(event.getFinishingTime());
        message.setPlace(place);
        BuildingMessage buildingMessage = event.getBuilding().toMessage();
        message.setBuilding(buildingMessage);
        return message;
    }

    public BuildingUpgradeEventMessage convert(BuildingUpgradeEvent event, int place){
        BuildingUpgradeEventMessage message = new BuildingUpgradeEventMessage();
        message.setFinishingTime(place);
        message.setPlace(place);
        message.setLEVEL(event.getLevel());
        BuildingMessage buildingMessage = event.getBuilding().toMessage();
        message.setBuilding(buildingMessage);
        return message;
    }
}
