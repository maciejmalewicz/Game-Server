package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades;


import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;

public class Upgrade {


    protected double value;
    protected double secondaryValue;

    //upgrades that must be done before doing this particular one
    public int[] REQUIREMENTS = new int[1];

    //COST of an upgrade
    public ResourceSet COST;

    //time needed to finish an upgrade
    public Time RESEARCH_TIME;

    public double getValue() {
        return value;
    }

    public double getSecondaryValue() {
        return secondaryValue;
    }


    public boolean canBeUpgraded(boolean[] upgradesBranch){
        for (int requirement : REQUIREMENTS) {
            if (!upgradesBranch[requirement]) {
                return false;
            }
        }
        return true;
    }
}
