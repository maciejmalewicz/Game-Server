package macior.strategygame.models.game;

import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;
import macior.strategygame.models.ResponseBase;

public class GameChanges extends ResponseBase {

    private ResourceSet resources;
    private short timeFromStart;

    public ResourceSet getResources() {
        return resources;
    }

    public void setResources(ResourceSet resources) {
        this.resources = resources;
    }

    public short getTimeFromStart() {
        return timeFromStart;
    }

    public void setTimeFromStart(short timeFromStart) {
        this.timeFromStart = timeFromStart;
    }
}
