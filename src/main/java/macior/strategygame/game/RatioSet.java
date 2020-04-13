package macior.strategygame.game;

//for generating income bonuses ratios
public class RatioSet {

    public double metalRatio = 1;
    public double buildingMaterialsRatio = 1;
    public double electricityRatio = 1;

    public void increaseRatios(double d){
        metalRatio += d;
        buildingMaterialsRatio += d;
        electricityRatio += d;
    }
}
