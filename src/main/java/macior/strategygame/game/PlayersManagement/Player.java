package macior.strategygame.game.PlayersManagement;


import macior.strategygame.game.Game;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Notifications.NotificationsInbox;
import macior.strategygame.game.Utilities.ResourceSet;

public class Player {

    private String nick;
    private PlayersUpgradesSet upgradesSet = new PlayersUpgradesSet();
    private ResourceSet resources = new ResourceSet(0, 0, 0);
    private ResourceSet income = new ResourceSet(0, 0, 0); //todo maybe change
    private Game game;
    private NotificationsInbox inbox = new NotificationsInbox();

    public NotificationsInbox getInbox() {
        return inbox;
    }

    public ResourceSet getIncome() {
        return income;
    }

    public void setIncome(ResourceSet income) {
        this.income = income;
    }

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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
