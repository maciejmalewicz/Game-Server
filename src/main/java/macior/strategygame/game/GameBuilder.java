package macior.strategygame.game;

import macior.strategygame.game.BoardManagement.Board;
import macior.strategygame.game.BoardManagement.BoardBuilder;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.PlayersManagement.PlayersSet;
import macior.strategygame.game.PostponedEvents.EventHandler;
import macior.strategygame.game.Utilities.Time;
import macior.strategygame.models.User;
import macior.strategygame.queue.UsersSet;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameBuilder {

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    private BoardBuilder boardBuilder;

    public Game buildGame(UsersSet set){
        return buildGame(set.getUser1(), set.getUser2(), set.getUser3(), set.getUser4());
    }

    public Game buildGame(User user1, User user2, User user3, User user4){
        Game game = new Game();

        PlayersSet playersSet = buildPlayersSet(user1, user2, user3, user4, game);
        game.setPlayersSet(playersSet);

        TimeManager manager = new TimeManager();
        game.setTimeManager(manager);

        EventHandler eventHandler = new EventHandler(game);
        game.setEventHandler(eventHandler);

        Board board = boardBuilder.buildBoard(playersSet);
        game.setBoard(board);

        //test
        board.toMessage(game.getPlayersSet().getPlayer1());

        GameUpdater updater = new GameUpdater();
        updater.setGame(game);
        game.setGameUpdater(updater);

        PlayersIncomeHandler handler = new PlayersIncomeHandler();
        handler.setBoard(board);
        game.setIncomeHandler(handler);

        return game;
    }

    //i create player using user and with the help of mapper i save it
    public Player createAndMapPlayer(User user, Game game){
        Player player = new Player();
        player.setNick(user.getLogin());
        player.setGame(game);
        mapper.addPlayer(user.getId(), player);
        return player;
    }

    public PlayersSet buildPlayersSet(User user1, User user2, User user3, User user4, Game game){
        Player player1 = createAndMapPlayer(user1, game);
        Player player2 = createAndMapPlayer(user2, game);
        Player player3 = createAndMapPlayer(user3, game);
        Player player4 = createAndMapPlayer(user4, game);
        return new PlayersSet(player1, player2, player3, player4);
    }


}
