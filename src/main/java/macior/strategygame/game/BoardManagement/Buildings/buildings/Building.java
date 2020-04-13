package macior.strategygame.game.BoardManagement.Buildings.buildings;

import macior.strategygame.models.game.messages.BuildingMessage;

public abstract class Building {

    public int LEVEL;

    public Building(){
        this.LEVEL = 1;
    }

    protected abstract String getLabel();

    public void setLevel(int level){
        LEVEL = level;
    }

    public BuildingMessage toMessage(){
        BuildingMessage message = new BuildingMessage();
        message.LEVEL = LEVEL;
        message.LABEL = getLabel();
        return message;
    }
}
