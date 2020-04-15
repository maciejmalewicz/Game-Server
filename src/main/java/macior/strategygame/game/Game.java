package macior.strategygame.game;

import macior.strategygame.game.BoardManagement.Board;
import macior.strategygame.game.PlayersManagement.PlayersSet;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.PostponedEvents.EventHandler;

public class Game {

    private PlayersSet playersSet;
    private TimeManager timeManager;
    private GameUpdater gameUpdater;
    private Board board;
    private PlayersIncomeHandler incomeHandler;
    private EventHandler eventHandler;
    private EventFactory eventFactory;

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public PlayersIncomeHandler getIncomeHandler() {
        return incomeHandler;
    }

    public void setIncomeHandler(PlayersIncomeHandler incomeHandler) {
        this.incomeHandler = incomeHandler;
    }

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

    public EventFactory getEventFactory() {
        return eventFactory;
    }

    public void setEventFactory(EventFactory eventFactory) {
        this.eventFactory = eventFactory;
    }
}



