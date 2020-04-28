package macior.strategygame.game;

//for generating income bonuses ratios
public class RatioSet {

    public double firstRatio = 1;
    public double secondRatio = 1;
    public double thirdRatio = 1;

    public RatioSet(){

    }

    public RatioSet(double d1, double d2, double d3){
        firstRatio = d1;
        secondRatio = d2;
        thirdRatio = d3;
    }

    public RatioSet clone(){
        RatioSet ratioSet = new RatioSet();
        ratioSet.firstRatio = firstRatio;
        ratioSet.secondRatio = secondRatio;
        ratioSet.thirdRatio = thirdRatio;
        return ratioSet;
    }

    public void increaseRatios(double d){
        firstRatio += d;
        secondRatio += d;
        thirdRatio += d;
    }

    public void multiplyRatios(double d){
        firstRatio *= d;
        secondRatio *= d;
        thirdRatio *= d;
    }

    public RatioSet getMultiplyBy(double d){
        RatioSet ratios = clone();
        ratios.multiplyRatios(d);
        return ratios;
    }
}
