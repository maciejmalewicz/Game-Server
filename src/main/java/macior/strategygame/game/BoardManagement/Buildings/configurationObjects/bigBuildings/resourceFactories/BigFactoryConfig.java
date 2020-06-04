package macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.resourceFactories;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.BigBuildingConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.levelConfigs.LevelAttributesConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.levelConfigs.ResourceFactoryLevelAttributesConfig;

public class BigFactoryConfig extends BuildingConfig<ResourceFactoryLevelAttributesConfig> {

    public ResourceFactoryLevelAttributesConfig[] LEVEL_ATTRIBUTES;

    public int getProduction(int level){
        return LEVEL_ATTRIBUTES[level-1].PRODUCTION;
    }

    @Override
    public ResourceFactoryLevelAttributesConfig[] getLevelAttributes() {
        return LEVEL_ATTRIBUTES;
    }
}
