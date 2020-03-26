package macior.strategygame.models.game.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameConfiguration {

    @Autowired
    private BigBuildingsConfig bigBuildingsConfig;

    @Autowired
    private SmallBuildingsConfig smallBuildingsConfig;

    @Autowired
    private UpgradesConfig upgradesConfig;

    public UpgradesConfig getUpgradesConfig() {
        return upgradesConfig;
    }

    public void setUpgradesConfig(UpgradesConfig upgradesConfig) {
        this.upgradesConfig = upgradesConfig;
    }

    public BigBuildingsConfig getBigBuildingsConfig() {
        return bigBuildingsConfig;
    }

    public void setBigBuildingsConfig(BigBuildingsConfig bigBuildingsConfig) {
        this.bigBuildingsConfig = bigBuildingsConfig;
    }

    public SmallBuildingsConfig getSmallBuildingsConfig() {
        return smallBuildingsConfig;
    }

    public void setSmallBuildingsConfig(SmallBuildingsConfig smallBuildingsConfig) {
        this.smallBuildingsConfig = smallBuildingsConfig;
    }
}