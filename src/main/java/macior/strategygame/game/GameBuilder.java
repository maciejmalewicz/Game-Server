package macior.strategygame.game;

import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.PlayersManagement.PlayersSet;
import macior.strategygame.game.Utilities.Time;
import macior.strategygame.models.User;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameBuilder {

    @Autowired
    private PlayerGameMapperService mapper;

    public Game buildGame(User user1, User user2, User user3, User user4){
        Game game = new Game();
        PlayersSet playersSet = buildPlayersSet(user1, user2, user3, user4);
        game.setPlayersSet(playersSet);
        TimeManager manager = new TimeManager();
        game.setTimeManager(manager);
        return game;
    }

    //i create player using user and with the help of mapper i save it
    public Player createAndMapPlayer(User user){
        Player player = new Player();
        mapper.addPlayer(user.getId(), player);
        return player;
    }

    public PlayersSet buildPlayersSet(User user1, User user2, User user3, User user4){
        Player player1 = createAndMapPlayer(user1);
        Player player2 = createAndMapPlayer(user2);
        Player player3 = createAndMapPlayer(user3);
        Player player4 = createAndMapPlayer(user4);
        return new PlayersSet(player1, player2, player3, player4);
    }


}
