package macior.strategygame.models.game.messages;

import macior.strategygame.models.ResponseBase;

public class GameStateResponse extends ResponseBase {

    private BoardMessage boardMessage;
    private OpponentsMessage opponentsMessage;

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
