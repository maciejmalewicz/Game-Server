package macior.strategygame.models.settings_management;

import macior.strategygame.models.StatusResponse;

public class GetSettingsResponse extends StatusResponse {

    private Settings settings;

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }



}
