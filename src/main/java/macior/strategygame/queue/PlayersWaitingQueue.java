package macior.strategygame.queue;

import macior.strategygame.game.Game;
import macior.strategygame.game.GameBuilder;
import macior.strategygame.models.User;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class PlayersWaitingQueue extends Thread {

    private GameBuilder gameBuilder;
    private GamesManager gamesManager;

    @Autowired
    public PlayersWaitingQueue(GameBuilder builder, GamesManager manager){
        gameBuilder = builder;
        gamesManager = manager;
        System.out.println("STARITNG QUEUE!!!");
        for (int i = 0; i < 10; i++){
            User user1 = new User();
            user1.setId(5);
            user1.setLogin("Andrzej");
            addToQueue(user1);

            User user2 = new User();
            user2.setId(4);
            user2.setLogin("Andrzejeniekkkkk");
            addToQueue(user2);

            User user3 = new User();
            user3.setId(7);
            user3.setLogin("Andrzejek");
            addToQueue(user3);

            User user4 = new User();
            user4.setId(9);
            user4.setLogin("Andrzejeczek");
            addToQueue(user4);
        }

        this.start();
    }

    private LinkedList<User> queue = new LinkedList<>();

    public void addToQueue(User user){
        queue.addLast(user);
    }

    public void cleanQueue(){
        int size = queue.size();
        int gamesToStart = size/4;
        for (int i = 0; i < gamesToStart; i++){
            boolean canAdd = gamesManager.hasFreeSlot();
            if (!canAdd){
                System.out.println("NO EMPTY SLOTS!");
                break;
            }
            UsersSet usersSet = removeFour();
            Game game = gameBuilder.buildGame(usersSet);
            gamesManager.addGame(game);
            game.getGameUpdater().launch();
            System.out.println("LUNCHED GAME!");
        }
    }

    public UsersSet removeFour(){
        User user1 = queue.removeFirst();
        User user2 = queue.removeFirst();
        User user3 = queue.removeFirst();
        User user4 = queue.removeFirst();
        UsersSet out = new UsersSet();
        out.setUser1(user1);
        out.setUser2(user2);
        out.setUser3(user3);
        out.setUser4(user4);
        return out;
    }

    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException exc){ }
            cleanQueue();
        }
    }
}
