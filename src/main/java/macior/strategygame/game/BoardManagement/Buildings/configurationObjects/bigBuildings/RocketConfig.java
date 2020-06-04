package macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.levelConfigs.LevelAttributesConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.levelConfigs.RocketLevelAttributesConfig;
import macior.strategygame.game.Utilities.Time;
import org.springframework.stereotype.Component;

@Component
public class RocketConfig extends BuildingConfig<RocketLevelAttributesConfig> {

    public RocketLevelAttributesConfig[] LEVEL_ATTRIBUTES;

    @Override
    public RocketLevelAttributesConfig[] getLevelAttributes() {
        return LEVEL_ATTRIBUTES;
    }
}
