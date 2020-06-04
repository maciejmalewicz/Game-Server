package macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.resourceFactories;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.levelConfigs.LevelAttributesConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.levelConfigs.ResourceFactoryLevelAttributesConfig;

public class SmallFactoryConfig extends BuildingConfig <ResourceFactoryLevelAttributesConfig>{

    public ResourceFactoryLevelAttributesConfig[] LEVEL_ATTRIBUTES;

    @Override
    public ResourceFactoryLevelAttributesConfig[] getLevelAttributes() {
        return LEVEL_ATTRIBUTES;
    }

    public int getProduction(int level){
        return LEVEL_ATTRIBUTES[level-1].PRODUCTION;
    }
}
