package macior.strategygame.game.BoardManagement;

import macior.strategygame.game.Game;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.models.game.messages.GameStateResponse;
import macior.strategygame.models.game.messages.OpponentsMessage;
import org.springframework.stereotype.Component;

@Component
public class GameConverter { //mother converter UPDATING response

    public GameStateResponse convert(Game game, Player caller, GameStateResponse target){
        target.setOpponentsMessage(getOpponents(game.getBoard(), caller));
        target.setBoardMessage(game.getBoard().toMessage(caller));
        return target;
    }

    public OpponentsMessage getOpponents(Board board, Player caller){
        OpponentsMessage opponents = new OpponentsMessage();
        Player opp1 = board.getPlayer1();
        if (!caller.getNick().equals(opp1.getNick())){
            opponents.setOpponent1(opp1.getNick());
        }
        Player opp2 = board.getPlayer2();
        if (!caller.getNick().equals(opp2.getNick())){
            opponents.setOpponent2(opp2.getNick());
        }
        Player opp3 = board.getPlayer3();
        if (!caller.getNick().equals(opp3.getNick())){
            opponents.setOpponent3(opp3.getNick());
        }
        Player opp4 = board.getPlayer4();
        if (!caller.getNick().equals(opp4.getNick())){
            opponents.setOpponent4(opp4.getNick());
        }
        return opponents;
    }
}
