package macior.strategygame.models.settings_management;

import macior.strategygame.models.StatusResponse;

public class SettingsResponse extends StatusResponse {

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
