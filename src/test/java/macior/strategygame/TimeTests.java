package macior.strategygame;

import macior.strategygame.game.Game;
import macior.strategygame.game.GameUpdater;
import macior.strategygame.game.TimeManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TimeTests {

    @Test
    public void testTimer(){
        TimeManager manager = new TimeManager();
        Game game = new Game();
        GameUpdater updater = new GameUpdater();
        game.setTimeManager(manager);
        updater.setGame(game);
        updater.launch();
    }

    @Test
    public void ramTest(){
        List<Long> longs = new ArrayList<>();
        while (true){
            longs.add(9000000L);
        }
    }
}
