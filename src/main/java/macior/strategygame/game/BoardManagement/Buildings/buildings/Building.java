package macior.strategygame.game.BoardManagement.Buildings.buildings;

import macior.strategygame.models.game.BuildingMessage;

public abstract class Building {

    public int LEVEL = 1;

    protected abstract String getLabel();

    public BuildingMessage toMessage(){
        BuildingMessage message = new BuildingMessage();
        message.LEVEL = this.LEVEL;
        message.LABEL = getLabel();
        return message;
    }
}
