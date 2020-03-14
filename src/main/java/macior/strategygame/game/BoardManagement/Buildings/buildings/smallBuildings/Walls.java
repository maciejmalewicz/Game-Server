package macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings;

import macior.strategygame.models.game.BuildingMessage;
import macior.strategygame.models.game.WallsMessage;

public class Walls extends SmallBuilding {

    private int hitpointsLeft = 10;

    public int getHitpointsLeft() {
        return hitpointsLeft;
    }

    public void setHitpointsLeft(int hitpointsLeft) {
        this.hitpointsLeft = hitpointsLeft;
    }

    @Override
    public String getLabel(){
        return "WALLS";
    }

    @Override
    public BuildingMessage toMessage(){
        WallsMessage out = new WallsMessage();
        out.LABEL = this.getLabel();
        out.LEVEL = this.LEVEL;
        out.HITPOINTS_LEFT = this.hitpointsLeft;
        return out;
    }
}
