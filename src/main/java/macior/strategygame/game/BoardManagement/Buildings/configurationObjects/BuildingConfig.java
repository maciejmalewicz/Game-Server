package macior.strategygame.game.BoardManagement.Buildings.configurationObjects;


import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.levelConfigs.LevelAttributesConfig;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;



public abstract class BuildingConfig <LevelAttributeSet extends LevelAttributesConfig> {

    public int MAX_LEVEL;

    public abstract LevelAttributeSet[] getLevelAttributes();

//    public ResourceSet LEVEL1_COST;
//    public ResourceSet LEVEL2_COST;
//    public ResourceSet LEVEL3_COST;
//    public ResourceSet LEVEL4_COST;
//
//    public Time LEVEL1_BUILDING_TIME;
//    public Time LEVEL2_BUILDING_TIME;
//    public Time LEVEL3_BUILDING_TIME;
//    public Time LEVEL4_BUILDING_TIME;

//    public ResourceSet getCost(int level){
//
//        switch (level){
//            case 1:
//                return LEVEL1_COST;
//            case 2:
//                return LEVEL2_COST;
//            case 3:
//                return LEVEL3_COST;
//            case 4:
//                return LEVEL4_COST;
//        }
//        return null;
//    }

    public ResourceSet getCost(int level){
        //level enumeration starts from 1 so -1 must be added
        LevelAttributesConfig levelAttributes = getLevelAttributes()[level-1];
        return levelAttributes.COST;
    }

    public Time getTime(int level){
        //same as above
        LevelAttributesConfig levelAttributes = getLevelAttributes()[level-1];
        return levelAttributes.BUILDING_TIME;
    }

//    public Time getTime(int level){
//        switch (level){
//            case 1:
//                return LEVEL1_BUILDING_TIME;
//            case 2:
//                return LEVEL2_BUILDING_TIME;
//            case 3:
//                return LEVEL3_BUILDING_TIME;
//            case 4:
//                return LEVEL4_BUILDING_TIME;
//        }
//        return null;
//    }
}
