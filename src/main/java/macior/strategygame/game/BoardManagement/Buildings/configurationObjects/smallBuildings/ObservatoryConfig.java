package macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings;

public class ObservatoryConfig extends SmallBuildingConfig {

    //how the chance decreases based on distance of spying
    public double LEVEL1_DISTANCE_CHANCE_REDUCTION_MULTIPLIER;
    public double LEVEL2_DISTANCE_CHANCE_REDUCTION_MULTIPLIER;
    public double LEVEL3_DISTANCE_CHANCE_REDUCTION_MULTIPLIER;
    public double LEVEL4_DISTANCE_CHANCE_REDUCTION_MULTIPLIER;

    //base chance (unreduced) of spying an enemy field
    public double LEVEL1_BASIC_CHANCE;
    public double LEVEL2_BASIC_CHANCE;
    public double LEVEL3_BASIC_CHANCE;
    public double LEVEL4_BASIC_CHANCE;

    //protection against enemy spying
    public double LEVEL1_ENEMYS_CHANCE_REDUCTION;
    public double LEVEL2_ENEMYS_CHANCE_REDUCTION;
    public double LEVEL3_ENEMYS_CHANCE_REDUCTION;
    public double LEVEL4_ENEMYS_CHANCE_REDUCTION;
}
