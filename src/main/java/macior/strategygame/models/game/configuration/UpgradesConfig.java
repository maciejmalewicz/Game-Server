package macior.strategygame.models.game.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradesConfig {

    @Autowired
    private ArmyUpgradesConfig armyUpgradesConfig;

    @Autowired
    private ControlUpgradesConfig controlUpgradesConfig;

    @Autowired
    private ImprovementUpgradesConfig improvementUpgradesConfig;

    public ImprovementUpgradesConfig getImprovementUpgradesConfig() {
        return improvementUpgradesConfig;
    }

    public void setImprovementUpgradesConfig(ImprovementUpgradesConfig improvementUpgradesConfig) {
        this.improvementUpgradesConfig = improvementUpgradesConfig;
    }

    public ArmyUpgradesConfig getArmyUpgradesConfig() {
        return armyUpgradesConfig;
    }

    public void setArmyUpgradesConfig(ArmyUpgradesConfig armyUpgradesConfig) {
        this.armyUpgradesConfig = armyUpgradesConfig;
    }

    public ControlUpgradesConfig getControlUpgradesConfig() {
        return controlUpgradesConfig;
    }

    public void setControlUpgradesConfig(ControlUpgradesConfig controlUpgradesConfig) {
        this.controlUpgradesConfig = controlUpgradesConfig;
    }
}
