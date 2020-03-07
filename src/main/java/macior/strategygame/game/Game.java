package macior.strategygame.game;

import macior.strategygame.game.PlayersManagement.PlayersSet;

public class Game {

    private PlayersSet playersSet;
    private TimeManager timeManager;
    private GameUpdater gameUpdater;

    public PlayersSet getPlayersSet() {
        return playersSet;
    }

    public void setPlayersSet(PlayersSet playersSet) {
        this.playersSet = playersSet;
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }

    public void setTimeManager(TimeManager timeManager) {
        this.timeManager = timeManager;
    }

    public GameUpdater getGameUpdater() {
        return gameUpdater;
    }

    public void setGameUpdater(GameUpdater gameUpdater) {
        this.gameUpdater = gameUpdater;
    }
}



