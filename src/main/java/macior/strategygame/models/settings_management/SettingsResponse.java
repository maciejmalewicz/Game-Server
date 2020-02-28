package macior.strategygame.models.settings_management;

import macior.strategygame.models.ResponseBase;

public class SettingsResponse extends ResponseBase {

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
