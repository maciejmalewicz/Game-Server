package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;

public class AdvancedDroids extends Upgrade {

    public AdvancedDroids(){
        //decrease production speed
        value = 0.5;
        //decrease COST
        secondaryValue = 0.3;

        //shooting windows
        REQUIREMENTS[0] = 6;

        COST = new ResourceSet(100, 100, 100);

        RESEARCH_TIME = new Time(1, 0);
    }
}
