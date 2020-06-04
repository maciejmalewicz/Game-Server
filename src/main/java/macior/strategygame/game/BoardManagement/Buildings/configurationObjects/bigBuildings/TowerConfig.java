package macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.levelConfigs.TowerLevelAttributesConfig;

public class TowerConfig extends BuildingConfig <TowerLevelAttributesConfig> {

    public TowerLevelAttributesConfig[] LEVEL_ATTRIBUTES;

    @Override
    public TowerLevelAttributesConfig[] getLevelAttributes() {
        return LEVEL_ATTRIBUTES;
    }
}
