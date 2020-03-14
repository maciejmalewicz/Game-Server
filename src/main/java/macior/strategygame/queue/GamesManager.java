package macior.strategygame.queue;

import macior.strategygame.game.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class GamesManager {

    private final static int MAX_GAME_SLOTS = 1; // todo set it well
    private List<Game> startedGames = new ArrayList<>();

    public boolean hasFreeSlot(){
        return startedGames.size() < MAX_GAME_SLOTS;
    }

    public void addGame(Game toAdd){
        startedGames.add(toAdd);
    }
}
