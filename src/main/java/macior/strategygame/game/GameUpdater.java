package macior.strategygame.game;

import java.util.Date;

public class GameUpdater extends Thread {

    private Game game;
    private boolean isLaunched;

    private long toWait;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void update(){
        //todo all the mechanics that happen every second
        System.out.println("Updating");
        System.out.println(new Date().getTime());
    }

    public void launch(){
        isLaunched = true;
        this.start();
    }

    @Override
    public void run(){
        while (isLaunched){
            update();
            game.getTimeManager().nextSecond();
            toWait = game.getTimeManager().getTimeToWait();
            System.out.println("to wait: " + toWait);
            try {
                Thread.sleep(toWait);
            } catch (InterruptedException exc){
                //ignore
            }
        }
    }

    public void stopUpdating(){
        isLaunched = false;
    }
}
