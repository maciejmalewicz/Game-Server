package macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.levelConfigs.WallsLevelAttributesConfig;

public class WallsConfig extends BuildingConfig <WallsLevelAttributesConfig> {

    public WallsLevelAttributesConfig[] LEVEL_ATTRIBUTES;

    @Override
    public WallsLevelAttributesConfig[] getLevelAttributes() {
        return LEVEL_ATTRIBUTES;
    }

}
