package macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.resourceFactories;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.bigBuildings.BigBuildingConfig;

public class BigFactoryConfig extends BigBuildingConfig {

    public int LEVEL1_PRODUCTION;
    public int LEVEL2_PRODUCTION;
    public int LEVEL3_PRODUCTION;
    public int LEVEL4_PRODUCTION;

    public int getProduction(int level){
        switch (level){
            case 1:
                return LEVEL1_PRODUCTION;
            case 2:
                return LEVEL2_PRODUCTION;
            case 3:
                return LEVEL3_PRODUCTION;
            case 4:
                return LEVEL4_PRODUCTION;
        }
        return 0;
    }

}
