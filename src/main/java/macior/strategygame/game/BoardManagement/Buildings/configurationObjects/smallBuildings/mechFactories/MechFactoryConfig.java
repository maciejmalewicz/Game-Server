package macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.SmallBuildingConfig;

public class MechFactoryConfig extends SmallBuildingConfig {

    public int LEVEL1_REGULAR_PRODUCTION;
    public int LEVEL2_REGULAR_PRODUCTION;

    public int LEVEL1_BIG_PRODUCTION;
    public int LEVEL2_BIG_PRODUCTION;

    public int LEVEL1_MASS_PRODUCTION;
    public int LEVEL2_MASS_PRODUCTION;

    public int getQuantity(int productionType, int level){
        if (productionType == 1 && level == 1){
            return LEVEL1_REGULAR_PRODUCTION;
        } else if (productionType == 1 && level == 2){
            return LEVEL2_REGULAR_PRODUCTION;
        } else if (productionType == 2 && level == 1){
            return LEVEL1_BIG_PRODUCTION;
        } else if (productionType == 2 && level == 2){
            return LEVEL2_BIG_PRODUCTION;
        } else if (productionType == 3 && level == 1){
            return LEVEL1_MASS_PRODUCTION;
        } else if (productionType == 3 && level == 2){
            return LEVEL2_MASS_PRODUCTION;
        }
        return 0;
    }



}
