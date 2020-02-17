package macior.strategygame.game.PlayersManagement;


import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.Utilities.ResourceSet;

public class Player {

    private PlayersUpgradesSet upgradesSet = new PlayersUpgradesSet();
    private ResourceSet resources = new ResourceSet(0, 0, 0);

    public PlayersUpgradesSet getUpgradesSet() {
        return upgradesSet;
    }

    public ResourceSet getResources() {
        return resources;
    }
}
