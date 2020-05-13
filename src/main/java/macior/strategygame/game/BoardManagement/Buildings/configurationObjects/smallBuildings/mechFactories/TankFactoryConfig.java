package macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories;

public class TankFactoryConfig extends MechFactoryConfig {


    public int LEVEL3_REGULAR_PRODUCTION;

    public int LEVEL3_BIG_PRODUCTION;

    public int LEVEL3_MASS_PRODUCTION;

    public int getQuantity(int productionType, int level) {
        if (productionType == 1 && level == 3){
            return LEVEL3_REGULAR_PRODUCTION;
        } else if (productionType == 2 && level == 3){
            return LEVEL3_BIG_PRODUCTION;
        } else if (productionType == 3 && level == 3){
            return LEVEL3_MASS_PRODUCTION;
        } else {
            return super.getQuantity(productionType, level);
        }
    }

}
