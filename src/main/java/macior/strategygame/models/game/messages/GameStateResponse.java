package macior.strategygame.models.game.messages;

import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.models.ResponseBase;

public class GameStateResponse extends ResponseBase {

    private BoardMessage boardMessage;
    private OpponentsMessage opponentsMessage;
    private PlayersUpgradesSet upgradesMessage;

    public PlayersUpgradesSet getUpgradesMessage() {
        return upgradesMessage;
    }

    public void setUpgradesMessage(PlayersUpgradesSet upgradesMessage) {
        this.upgradesMessage = upgradesMessage;
    }

    public OpponentsMessage getOpponentsMessage() {
        return opponentsMessage;
    }

    public void setOpponentsMessage(OpponentsMessage opponentsMessage) {
        this.opponentsMessage = opponentsMessage;
    }

    public BoardMessage getBoardMessage() {
        return boardMessage;
    }

    public void setBoardMessage(BoardMessage boardMessage) {
        this.boardMessage = boardMessage;
    }
}
