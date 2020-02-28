package macior.strategygame.models.account_management;

import macior.strategygame.models.ResponseBase;

public class StatusResponse extends ResponseBase {

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
