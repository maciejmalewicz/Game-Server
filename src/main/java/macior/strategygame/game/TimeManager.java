package macior.strategygame.game;

import java.util.Date;

public class TimeManager {

    private Date initiationDate;
    private long initiationMiliseconds;
    public byte minutesPassed = 0;
    public byte secondsPassed = 0;

    public TimeManager(){
        Date now = new Date();
        long time = now.getTime();// + 60000L; todo set back
        initiationDate = new Date(time); //sets starting game time for one minute from the beginning
        initiationMiliseconds = initiationDate.getTime();
    }

    public Date getInitiationDate() {
        return initiationDate;
    }

    public void setInitiationDate(Date initiationDate) {
        this.initiationDate = initiationDate;
    }

    @Override
    public String toString(){
        return minutesPassed + ":" + secondsPassed;
    }

    public void nextSecond(){
        if (secondsPassed < 59){
            secondsPassed++;
        } else {
            secondsPassed = 0;
            minutesPassed++;
        }
    }

    private long getMiliseconds(){
        return this.minutesPassed*60000L + this.secondsPassed*1000L;
    }

    public long getTimeToWait(){
        return getMiliseconds() + initiationMiliseconds - new Date().getTime();
    }
}
