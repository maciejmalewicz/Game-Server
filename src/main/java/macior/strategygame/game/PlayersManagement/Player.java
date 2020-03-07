package macior.strategygame.game.PlayersManagement;


import macior.strategygame.game.Game;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.Utilities.ResourceSet;

public class Player {

    private PlayersUpgradesSet upgradesSet = new PlayersUpgradesSet();
    private ResourceSet resources = new ResourceSet(0, 0, 0);
    private Game game;

    public PlayersUpgradesSet getUpgradesSet() {
        return upgradesSet;
    }

    public ResourceSet getResources() {
        return resources;
    }

    public Game getGame() {
        return game;
    }

    public void setUpgradesSet(PlayersUpgradesSet upgradesSet) {
        this.upgradesSet = upgradesSet;
    }

    public void setResources(ResourceSet resources) {
        this.resources = resources;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
