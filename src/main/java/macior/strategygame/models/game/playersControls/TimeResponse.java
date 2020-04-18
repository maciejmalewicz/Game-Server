package macior.strategygame.models.game.playersControls;

import macior.strategygame.models.StatusResponse;

public class TimeResponse extends StatusResponse {

    private int finishingTime;

    public int getFinishingTime() {
        return finishingTime;
    }

    public void setFinishingTime(int finishingTime) {
        this.finishingTime = finishingTime;
    }
}
