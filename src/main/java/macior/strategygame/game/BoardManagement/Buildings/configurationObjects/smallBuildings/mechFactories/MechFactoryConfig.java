package macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.levelConfigs.MechFactoryLevelAttributesConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.SmallBuildingConfig;

public class MechFactoryConfig extends BuildingConfig <MechFactoryLevelAttributesConfig> {

    public MechFactoryLevelAttributesConfig[] LEVEL_ATTRIBUTES;

    @Override
    public MechFactoryLevelAttributesConfig[] getLevelAttributes() {
        return LEVEL_ATTRIBUTES;
    }

    public int getQuantity(int productionType, int level){
        if (level >= MAX_LEVEL){
            return 0;
        }
        MechFactoryLevelAttributesConfig selectedLevelAttributes = LEVEL_ATTRIBUTES[level-1];
        switch (productionType){
            case 1:
                return selectedLevelAttributes.REGULAR_PRODUCTION;
            case 2:
                return selectedLevelAttributes.BIG_PRODUCTION;
            case 3:
                return selectedLevelAttributes.MASS_PRODUCTION;
            default:
                return 0;
        }
    }



}
