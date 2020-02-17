package macior.strategygame.game.Utilities;

public class Time {

    private int minutes;
    private int seconds;

    public Time (int minutes, int seconds){
        if (seconds > 59){
            seconds = 59;
        }
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public long toMiliseconds(){
        return (long)((minutes*60+seconds)*1000);
    }

    public void addSecond(){
        if (seconds < 59){
            seconds++;
        } else {
            seconds = 0;
            minutes++;
        }
    }

    public void removeSecond(){
        if (seconds > 0){
            seconds--;
        } else {
            seconds = 59;
            minutes--;
        }
    }

    @Override
    public String toString(){
        return minutes + ":" + seconds;
    }
}
