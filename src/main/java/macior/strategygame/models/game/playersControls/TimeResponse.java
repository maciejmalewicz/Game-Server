package macior.strategygame.models.game.playersControls;

import macior.strategygame.game.Utilities.Time;
import macior.strategygame.models.ResponseBase;
import macior.strategygame.models.account_management.StatusResponse;

public class TimeResponse extends StatusResponse {

    private int finishingTime;

    public int getFinishingTime() {
        return finishingTime;
    }

    public void setFinishingTime(int finishingTime) {
        this.finishingTime = finishingTime;
    }
}
