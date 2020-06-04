package macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.levelConfigs.ObservatoryLevelAttributesConfig;

public class ObservatoryConfig extends BuildingConfig <ObservatoryLevelAttributesConfig> {

    public ObservatoryLevelAttributesConfig[] LEVEL_ATTRIBUTES;

    @Override
    public ObservatoryLevelAttributesConfig[] getLevelAttributes() {
        return LEVEL_ATTRIBUTES;
    }
}
