package macior.strategygame.game;

import macior.strategygame.game.Utilities.Time;

import java.util.Date;

public class TimeManager {

    private static int GAME_DURATION = 3600; //in seconds

    private Date initiationDate;
    private long initiationMiliseconds;
    private short secondsFromStart = 0;
    //public byte minutesPassed = 0;
    //public byte secondsPassed = 0;

    public TimeManager(){
        Date now = new Date();
        long time = now.getTime();// + 60000L; todo set back
        initiationDate = new Date(time); //sets starting game time for one minute from the beginning
        initiationMiliseconds = initiationDate.getTime();
    }

    public short getSecondsFromStart(){
        return secondsFromStart;

    }

    public Date getInitiationDate() {
        return initiationDate;
    }

    public void setInitiationDate(Date initiationDate) {
        this.initiationDate = initiationDate;
    }

    @Override
    public String toString(){
        //return minutesPassed + ":" + secondsPassed;
        return secondsFromStart/60 + ":" + secondsFromStart%60;
    }

    public void nextSecond(){
//        if (secondsPassed < 59){
//            secondsPassed++;
//        } else {
//            secondsPassed = 0;
//            minutesPassed++;
//        }
        secondsFromStart++;
    }

    private long getMiliseconds(){
        return this.secondsFromStart*1000L;
        //return this.minutesPassed*60000L + this.secondsPassed*1000L;
    }

    public long getTimeToWait(){
        long toWait =  getMiliseconds() + initiationMiliseconds - new Date().getTime();
        if (toWait < 0){
            return 0;
        }
        return toWait;
    }

    public int getPostponedEventTime(int postponingDuration){
        int time = this.secondsFromStart + postponingDuration;
        if (time > GAME_DURATION){
            return -1;
        }
        return this.secondsFromStart + postponingDuration;
    }

    //if player will make it before game ends
    public boolean canStillBuild(int time){
        return time <= GAME_DURATION;
    }

}
