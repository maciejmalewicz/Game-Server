package macior.strategygame.models.settings_management;

import macior.strategygame.models.ResponseBase;

public class GetSettingsResponse extends ResponseBase {

    private Settings settings;

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }



}
