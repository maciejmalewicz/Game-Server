package macior.strategygame.models.game.playersControls;

import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.game.BoardManagement.Location;

public class ArmyTransferRequest extends AreaConcernedRequest {
    protected Location targetLocation;
    protected Army army;
    protected Location[] path;

    public Location getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(Location targetLocation) {
        this.targetLocation = targetLocation;
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public Location[] getPath() {
        return path;
    }

    public void setPath(Location[] path) {
        this.path = path;
    }
}