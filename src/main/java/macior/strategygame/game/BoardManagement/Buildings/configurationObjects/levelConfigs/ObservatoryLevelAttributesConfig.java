package macior.strategygame.game.BoardManagement.Buildings.configurationObjects.levelConfigs;

public class ObservatoryLevelAttributesConfig extends LevelAttributesConfig{
    //how the chance decreases based on distance of spying
    public double DISTANCE_CHANCE_REDUCTION_MULTIPLIER;

    //base chance (unreduced) of spying an enemy field
    public double BASIC_CHANCE;

    //protection against enemy spying
    public double ENEMYS_CHANCE_REDUCTION;
}
