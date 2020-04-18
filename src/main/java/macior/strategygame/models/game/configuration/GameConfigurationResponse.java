package macior.strategygame.models.game.configuration;

import macior.strategygame.models.StatusResponse;

public class GameConfigurationResponse extends StatusResponse {

    private GameConfiguration configuration;

    public GameConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(GameConfiguration configuration) {
        this.configuration = configuration;
    }
}
