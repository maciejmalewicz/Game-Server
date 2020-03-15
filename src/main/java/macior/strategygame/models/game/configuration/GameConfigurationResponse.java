package macior.strategygame.models.game.configuration;

import macior.strategygame.models.ResponseBase;

public class GameConfigurationResponse extends ResponseBase {

    private GameConfiguration configuration;

    public GameConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(GameConfiguration configuration) {
        this.configuration = configuration;
    }
}
