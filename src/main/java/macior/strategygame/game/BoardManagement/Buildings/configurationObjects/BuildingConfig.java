package macior.strategygame.game.BoardManagement.Buildings.configurationObjects;


import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;

public class BuildingConfig {

    public int MIN_LEVEL;
    public int MAX_LEVEL;

    public ResourceSet LEVEL1_COST;
    public ResourceSet LEVEL2_COST;
    public ResourceSet LEVEL3_COST;
    public ResourceSet LEVEL4_COST;

    public Time LEVEL1_BUILDING_TIME;
    public Time LEVEL2_BUILDING_TIME;
    public Time LEVEL3_BUILDING_TIME;
    public Time LEVEL4_BUILDING_TIME;

    public int level = MIN_LEVEL;
}
