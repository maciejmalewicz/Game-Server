package macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades;


import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.game.Utilities.Time;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AdvancedDroids extends Upgrade {

    public double PRODUCTION_TIME_INCREASE;
    public double PRODUCTION_COST_REDUCTION;

    public AdvancedDroids(){
//        //decrease production speed
//        value = 0.5;
//        //decrease COST
//        secondaryValue = 0.3;
//
//        //shooting windows
//        REQUIREMENTS[0] = 6;
//
//        COST = new ResourceSet(100, 100, 100);
//
//        RESEARCH_TIME = new Time(1, 0);
    }

    @Override
    public String toString(){
        return COST + " " + Arrays.toString(REQUIREMENTS) + " " + RESEARCH_TIME + " " + PRODUCTION_TIME_INCREASE + " "
                + PRODUCTION_COST_REDUCTION;
    }
}
